package io.github.srdjanv.tweakedlib.common;

import net.minecraftforge.fml.common.Loader;

public class Constants {

    private static boolean AdvancedRocketryLoaded;
    private static boolean TweakedExcavationLoaded;
    private static boolean TweakedPetroleumLoaded;
    private static boolean TweakedPetroleumGasLoaded;
    private static boolean TheOneProbeLoaded;
    private static boolean WailaLoaded;
    private static boolean GroovyScriptLoaded;
    private static boolean CraftTweakerLoaded;

    public static void init() {
        AdvancedRocketryLoaded = Loader.isModLoaded("advancedrocketry");
        TweakedExcavationLoaded = Loader.isModLoaded("tweakedexcavation");
        TweakedPetroleumLoaded = Loader.isModLoaded("tweakedpetroleum");
        TweakedPetroleumGasLoaded = Loader.isModLoaded("tweakedpetroleumgas");
        TheOneProbeLoaded = Loader.isModLoaded("theoneprobe");
        WailaLoaded = Loader.isModLoaded("waila");
        GroovyScriptLoaded = Loader.isModLoaded("groovyscript");
        CraftTweakerLoaded = Loader.isModLoaded("crafttweaker");
    }

    public static boolean isAdvancedRocketryLoaded() {
        return AdvancedRocketryLoaded;
    }

    public static boolean isTweakedExcavationLoaded() {
        return TweakedExcavationLoaded;
    }

    public static boolean isTweakedPetroleumLoaded() {
        return TweakedPetroleumLoaded;
    }

    public static boolean isTweakedPetroleumGasLoaded() {
        return TweakedPetroleumGasLoaded;
    }

    public static boolean isTheOneProbeLoaded() {
        return TheOneProbeLoaded;
    }

    public static boolean isWailaLoaded() {
        return WailaLoaded;
    }

    public static boolean isGroovyScriptLoaded() {
        return GroovyScriptLoaded;
    }

    public static boolean isCraftTweakerLoaded() {return CraftTweakerLoaded;}
}
