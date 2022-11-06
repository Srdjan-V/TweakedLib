package srki2k.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.util.groovyscript.GroovyScriptModSupportContainerWrapper;
import srki2k.tweakedlib.util.Constants;

public class GroovyScriptCompat extends ModPropertyContainer {

    private static ModSupport.Container<GroovyScriptCompat> modSupportContainer;

    public static void init() {
        if (Constants.isGroovyScriptLoaded()) {
            modSupportContainer = GroovyScriptModSupportContainerWrapper.registerGroovyContainer(TweakedLib.MODID, "TweakedMods", GroovyScriptCompat::new);
        }
    }

    private GroovyScriptCompat() {
        addRegistry(TweakedGroovyPowerTier.init());
    }

}
