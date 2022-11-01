package srki2k.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import srki2k.tweakedlib.TweakedLib;

public class GroovyScriptCompat extends ModPropertyContainer {

    private static ModSupport.Container<GroovyScriptCompat> modSupportContainer;

    public static GroovyScriptCompat getInstance() {
        if (modSupportContainer == null) {
            TweakedLib.LOGGER.info("Initializing GroovyScript Mod Support Container");
            modSupportContainer = new ModSupport.Container<>(TweakedLib.MODID, "TweakedMods", GroovyScriptCompat::new);
        }
        return modSupportContainer.get();
    }

    @Override
    public void addRegistry(VirtualizedRegistry<?> registry) {
        super.addRegistry(registry);
    }

}
