package srki2k.tweakedlib;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TweakedLib.MODID,
        version = TweakedLib.VERSION,
        name = "Tweaked Lib")
public class TweakedLib {
    public static final String MODID = "tweakedlib";
    public static final String VERSION = "@VERSION@";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

}
