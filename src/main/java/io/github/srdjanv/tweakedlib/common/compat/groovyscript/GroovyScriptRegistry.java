package io.github.srdjanv.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.common.Constants;

public class GroovyScriptRegistry extends ModPropertyContainer {
    private static GroovyScriptRegistry registry;

    public static void init() {
        if (Constants.isGroovyScriptLoaded()) {
            registry = new GroovyScriptRegistry();
            new ModSupport.Container<>(TweakedLib.MODID, TweakedLib.NAME, () -> registry, "TweakedMods", "tweakedMods", "tweakedmods");
        }
    }

    @Override
    public void addRegistry(VirtualizedRegistry<?> registry) {
        super.addRegistry(registry);
    }

    public static GroovyScriptRegistry getRegistry() {
        return registry;
    }
}
