package srki2k.tweakedlib;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import srki2k.tweakedlib.util.errorlogging.ErrorLoggingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import srki2k.tweakedlib.util.Constants;

@Mod(modid = TweakedLib.MODID,
        version = TweakedLib.VERSION,
        name = "Tweaked Lib")
public class TweakedLib {
    public static final String MODID = "tweakedlib";
    public static final String VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Constants.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ErrorLoggingUtil.validateState();
    }

}
