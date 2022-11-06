package srki2k.tweakedlib.util.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import com.google.common.base.Supplier;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.util.Constants;

@SuppressWarnings("unused")
public class GroovyScriptModSupportContainerWrapper {
    private GroovyScriptModSupportContainerWrapper() {
    }

    static {
        if (Constants.isGroovyScriptLoaded()) {
            TweakedLib.LOGGER.info("Initializing GroovyScript Mod Support Container");
        }
    }

    public static <T extends ModPropertyContainer> ModSupport.Container<T> registerGroovyContainer(String modId, String modName, Supplier<T> modProperty) {
        return registerCommonGroovyContainer(modId, modName, modProperty);
    }

    public static <T extends ModPropertyContainer> ModSupport.Container<T> registerGroovyContainer(String modId, String modName, Supplier<T> modProperty, String... aliases) {
        return registerCommonGroovyContainer(modId, modName, modProperty, aliases);
    }

    private static <T extends ModPropertyContainer> ModSupport.Container<T> registerCommonGroovyContainer(String modId, String modName, Supplier<T> modProperty, String... aliases) {
        TweakedLib.LOGGER.info("Registered {} Container with mod name {}", modId, modName);
        return new ModSupport.Container<>(modId, modName, modProperty, aliases);
    }

}
