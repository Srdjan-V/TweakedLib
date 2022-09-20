package srki2k.tweakedlib.common;

import net.minecraftforge.common.config.Config;
import srki2k.tweakedlib.TweakedLib;

public class Configs {

    @Config(modid = TweakedLib.MODID)
    public static class TLConfigs {

        @Config.Name("Logging")
        public static Logging logging;

        public static class Logging {

            @Config.Comment({"This will disable all Logging, default=false"})
            @Config.Name("Disable Logging")
            @Config.RequiresMcRestart
            public static boolean disableLogging = false;

            @Config.Comment({"This will check if you have 0 Power Tiers, default=true"})
            @Config.Name("Log missing Power Tiers")
            @Config.RequiresMcRestart
            public static boolean logMissingContent = true;

            @Config.Comment({"Log errors to the player once he joins the game, default=true"})
            @Config.Name("Log errors to players")
            @Config.RequiresMcRestart
            public static boolean logToPlayers = true;

        }

    }
}
