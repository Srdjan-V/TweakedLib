package srki2k.tweakedlib.common;

import net.minecraftforge.fml.common.Loader;

public class Constants {

    private static boolean AdvancedRocketryLoaded;
    private static boolean TweakedExcavationLoaded;
    private static boolean TweakedPetroleumLoaded;
    private static boolean TweakedPetroleumGasLoaded;

    private static boolean GroovyScriptLoaded;

    public static void init() {
        AdvancedRocketryLoaded = Loader.isModLoaded("advancedrocketry");
        TweakedExcavationLoaded = Loader.isModLoaded("tweakedexcavation");
        TweakedPetroleumLoaded = Loader.isModLoaded("tweakedpetroleum");
        TweakedPetroleumGasLoaded = Loader.isModLoaded("tweakedpetroleumgas");
        GroovyScriptLoaded = Loader.isModLoaded("groovyscript");
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

    public static boolean isGroovyScriptLoaded() {
        return GroovyScriptLoaded;
    }
}
