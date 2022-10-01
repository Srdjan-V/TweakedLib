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

        @Config.Name("Default Machine Power Tiers")
        public static DefaultMachinePowerTiers defaultMachinePowerTiers;

        public static class DefaultMachinePowerTiers {

            @Config.Comment({"If TweakedPetroleum is loaded these configs might load"})
            @Config.Name("Default Pumpjack Power Tiers")
            public static DefaultPumpjackPowerTiers defaultPumpjackPowerTiers;

            public static class DefaultPumpjackPowerTiers {

                @Config.Comment({"Setting this to false will make this power tier not register, default=true"})
                @Config.Name("Load these configs")
                @Config.RequiresMcRestart
                public static boolean load = true;

                @Config.Comment({"This will set the power tier of the pumpjack, default=0"})
                @Config.Name("Power Tier ID")
                @Config.RangeInt(min = 0)
                @Config.RequiresMcRestart
                public static int powerTier = 0;

                @Config.Comment({"This will set the capacity of the pumpjack, default=16000"})
                @Config.Name("Default capacity")
                @Config.RangeInt(min = 1)
                @Config.RequiresMcRestart
                public static int capacity = 16000;

                @Config.Comment({"This will set the power consumption of the pumpjack, default=1024"})
                @Config.Name("Default consumption")
                @Config.RangeInt(min = 1)
                @Config.RequiresMcRestart
                public static int rft = 1024;

            }

            @Config.Comment({"If TweakedExcavation is loaded these configs might load"})
            @Config.Name("Default Excavator Power Tiers")
            public static DefaultExcavatorPowerTiers defaultExcavatorPowerTiers;

            public static class DefaultExcavatorPowerTiers {

                @Config.Comment({"Setting this to false will make this power tier not register, default=true"})
                @Config.Name("Load these configs")
                @Config.RequiresMcRestart
                public static boolean load = true;

                @Config.Comment({"This will set the power tier of the excavator, default=1"})
                @Config.Name("Power Tier ID")
                @Config.RangeInt(min = 0)
                @Config.RequiresMcRestart
                public static int powerTier = 1;

                @Config.Comment({"This will set the capacity of the excavator, default=64000"})
                @Config.Name("Default capacity")
                @Config.RangeInt(min = 1)
                @Config.RequiresMcRestart
                public static int capacity = 64000;

                @Config.Comment({"This will set the power consumption of the excavator, default=4096"})
                @Config.Name("Default consumption")
                @Config.RangeInt(min = 1)
                @Config.RequiresMcRestart
                public static int rft = 4096;

            }

        }

    }

}
