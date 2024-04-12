package io.github.srdjanv.tweakedlib.common;

import io.github.srdjanv.tweakedlib.TweakedLib;
import net.minecraftforge.common.config.Config;

@Config(modid = TweakedLib.MODID)
public class Configs {

    @Config.Name("Logging")
    public static Logging logging = new Logging();

    public static class Logging {

        @Config.Name("Passthrough errors to Craft Tweaker")
        @Config.RequiresMcRestart
        public boolean shouldPassthroughErrorsToCT = true;

        @Config.Name("Passthrough errors to Groovy Script")
        @Config.RequiresMcRestart
        public boolean shouldPassthroughErrorsToGS = true;


        @Config.Comment({"This will run checks for passable errors on startup. If they exit it will crash the game, instead if loading the game",
                "recommend while developing a pack but not in production, default=false"})
        @Config.Name("Log Missing PowerTiers on startup")
        @Config.RequiresMcRestart
        public boolean runRuntimeChecksOnStartup = false;
    }

}
