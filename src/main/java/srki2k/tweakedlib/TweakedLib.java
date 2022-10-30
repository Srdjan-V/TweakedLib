package srki2k.tweakedlib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.common.Constants;
import srki2k.tweakedlib.common.powertierlogging.PowerTierLogging;

@Mod(modid = TweakedLib.MODID,
        version = TweakedLib.VERSION,
        name = "Tweaked Lib")
public class TweakedLib {
    public static final String MODID = "tweakedlib";
    public static final String VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, net.minecraftforge.common.config.Config.Type.INSTANCE);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        Constants.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PowerTierLogging.RegisterLogger();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PowerTierHandler.recalculateTiers();
        ErrorLoggingLib.validateState();
    }

}
