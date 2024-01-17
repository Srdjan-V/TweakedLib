package io.github.srdjanv.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModSupport;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.common.Constants;
import io.github.srdjanv.tweakedlib.integration.IInitializer;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class GroovyScriptRegistry extends ModPropertyContainer implements GroovyPlugin {

    private static GroovyScriptRegistry registry;

    public static GroovyScriptRegistry getRegistry() {
        return Objects.requireNonNull(registry);
    }

    public GroovyScriptRegistry() {
        registry = this;
        addRegistry(new GroovyPowerTier());
    }

    @Override @Nullable public ModPropertyContainer createModPropertyContainer() {
        return new GroovyScriptRegistry();
    }

    @Override public void addRegistry(VirtualizedRegistry<?> registry) {
        super.addRegistry(registry);
    }

    @Override public @NotNull Collection<String> getAliases() {
        return Arrays.asList("TweakedMods", "tweakedMods", "tweakedmods");
    }

    @Override public void onCompatLoaded(GroovyContainer<?> container) {
    }

    @Override public @NotNull String getModId() {
        return TweakedLib.MODID;
    }
}
