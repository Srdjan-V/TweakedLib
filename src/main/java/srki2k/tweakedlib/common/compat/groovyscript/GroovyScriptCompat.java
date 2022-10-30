package srki2k.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.GroovyScript;
import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.common.Constants;

public class GroovyScriptCompat extends ModPropertyContainer {

    private static ModSupport.Container<GroovyScriptCompat> modSupportContainer;

    public static void init() {
        if (!Constants.isGroovyScriptLoaded()) {
            return;
        }
        modSupportContainer = new ModSupport.Container<>(TweakedLib.MODID, "TweakedMods", GroovyScriptCompat::new);
    }

    @Override
    protected void addRegistry(VirtualizedRegistry<?> registry) {
        super.addRegistry(registry);
    }

    public static boolean isLoaded() {
        return Constants.isGroovyScriptLoaded();
    }

    public static boolean isCurrentlyRunning() {
        return Constants.isGroovyScriptLoaded() && GroovyScript.getSandbox().isRunning();
    }

    public static GroovyScriptCompat getInstance() {
        return Constants.isGroovyScriptLoaded() ? modSupportContainer.get() : null;
    }

}
