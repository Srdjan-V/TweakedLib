package srki2k.tweakedlib.common;

import net.minecraftforge.common.config.Config;
import srki2k.tweakedlib.TweakedLib;

public class Configs {

    @Config(modid = TweakedLib.MODID)
    public static class TLConfigs {

        @Config.Name("Logging")
        public static Logging logging;

        public static class Logging {

            @Config.Comment({"This will log missing power tiers on startup",
                    "it will still crash if you try to use a non existent power tier and generate a report, even if this setting is enabled",
                    "recommend while developing a pack but not in production, default=false"})
            @Config.Name("Log Missing PowerTiers on startup")
            @Config.RequiresMcRestart
            public static boolean logMissingPowerTier = false;
        }

    }

}
