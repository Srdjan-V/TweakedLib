package io.github.srdjanv.tweakedlib;

import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import io.github.srdjanv.tweakedlib.common.Constants;
import io.github.srdjanv.tweakedlib.common.compat.groovyscript.GroovyScriptRegistry;
import io.github.srdjanv.tweakedlib.common.powertierlogging.PowerTierLogging;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.HTML;

@Mod(modid = TweakedLib.MODID,
        version = TweakedLib.VERSION,
        name = TweakedLib.NAME,
        dependencies = "after:groovyscript@[" + Tags.GROOVY_SCRIPT_VERSION + ",)")
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
        GroovyScriptRegistry.init();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PowerTierLogging.RegisterLogger();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ErrorLoggingLib.validateState();
    }

}
