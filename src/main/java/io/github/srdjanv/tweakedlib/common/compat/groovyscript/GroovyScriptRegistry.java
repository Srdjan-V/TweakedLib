package io.github.srdjanv.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.api.INamed;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.compat.mods.GroovyPropertyContainer;
import io.github.srdjanv.tweakedlib.TweakedLib;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class GroovyScriptRegistry extends GroovyPropertyContainer implements GroovyPlugin {
    private static GroovyScriptRegistry registry;
    public static GroovyScriptRegistry getRegistry() {
        return Objects.requireNonNull(registry);
    }

    private final List<Consumer<GroovyContainer<GroovyScriptRegistry>>> onCompatLoadedListener = new ObjectArrayList<>();

    public GroovyScriptRegistry() {
        registry = this;
        addProperty(new GroovyPowerTier());
    }

    @Override public @Nullable GroovyPropertyContainer createGroovyPropertyContainer() {
        return this;
    }

    @Override public void addProperty(INamed property) {
        super.addProperty(property);
    }

    @Deprecated public void addRegistry(INamed named) {
        super.addProperty(named);
    }

    @Override public @NotNull Collection<String> getAliases() {
        return Arrays.asList("TweakedMods", "tweakedMods", "tweakedmods");
    }

    public void addOnCompatLoadedListeners(Consumer<GroovyContainer<GroovyScriptRegistry>> onCompatLoaded) {
        this.onCompatLoadedListener.add(onCompatLoaded);
    }

    @Override public void onCompatLoaded(GroovyContainer<?> container) {
        onCompatLoadedListener.forEach(c -> c.accept((GroovyContainer<GroovyScriptRegistry>) container));
        onCompatLoadedListener.clear();
    }

    @Override public @NotNull String getModId() {
        return TweakedLib.MODID;
    }

    @Override public @NotNull String getContainerName() {
        return "TweakedMods-Containers";
    }
}
