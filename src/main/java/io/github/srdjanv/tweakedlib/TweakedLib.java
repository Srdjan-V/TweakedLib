package io.github.srdjanv.tweakedlib;

import com.cleanroommc.groovyscript.GroovyScript;
import com.cleanroommc.groovyscript.api.GroovyLog;
import crafttweaker.CraftTweakerAPI;
import io.github.srdjanv.tweakedlib.api.integration.DiscoveryHandler;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import io.github.srdjanv.tweakedlib.common.Configs;
import io.github.srdjanv.tweakedlib.common.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TweakedLib.MODID,
        version = TweakedLib.VERSION,
        name = TweakedLib.NAME,
        dependencies =
                "after:crafttweaker@[4.1.20,); after:groovyscript@[" + Tags.GROOVY_SCRIPT_VERSION + ",)")
public class TweakedLib {
    public static final String NAME = "Tweaked Lib";
    public static final String MODID = "tweakedlib";
    public static final String VERSION = Tags.VERSION;
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
        }
    }

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        Constants.init();
        DiscoveryHandler.getInstance().buildASMData(event.getASMHarvestedData());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (Constants.isCraftTweakerLoaded() && Configs.logging.shouldPassthroughErrorsToCT) {
            ErrorLoggingLib.registerCustomLoggerListener(iCustomLogger -> {
                for (String error : iCustomLogger.getErrors()) CraftTweakerAPI.logError(error);
            });
        }

        if (Constants.isGroovyScriptLoaded() && Configs.logging.shouldPassthroughErrorsToGS) {
            ErrorLoggingLib.registerCustomLoggerListener(iCustomLogger -> {
                var log = GroovyLog.msg(String.format("Tweaked Lib: Logger: %s", iCustomLogger.getModLogger().getName())).error();
                for (String error : iCustomLogger.getErrors()) log.add(error);
                log.post();
            });
        }

        MinecraftForge.EVENT_BUS.register(this);
        DiscoveryHandler.getInstance().preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        DiscoveryHandler.getInstance().init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DiscoveryHandler.getInstance().postInit(event);
        ErrorLoggingLib.validateState();
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        DiscoveryHandler.invalidate();
    }

}
