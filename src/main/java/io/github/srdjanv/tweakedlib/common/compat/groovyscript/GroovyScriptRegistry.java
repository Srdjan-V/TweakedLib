package io.github.srdjanv.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyPlugin;
import com.cleanroommc.groovyscript.api.IGameObjectParser;
import com.cleanroommc.groovyscript.api.INamed;
import com.cleanroommc.groovyscript.api.Result;
import com.cleanroommc.groovyscript.compat.mods.GroovyContainer;
import com.cleanroommc.groovyscript.compat.mods.ModPropertyContainer;
import com.cleanroommc.groovyscript.gameobjects.GameObjectHandler;
import com.cleanroommc.groovyscript.gameobjects.GameObjectHandlerManager;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTier;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTierHandler;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class GroovyScriptRegistry extends ModPropertyContainer implements GroovyPlugin {
    private static GroovyScriptRegistry registry;
    public static GroovyScriptRegistry getRegistry() {
        return Objects.requireNonNull(registry);
    }

    private final List<Consumer<GroovyContainer<GroovyScriptRegistry>>> onCompatLoadedListener = new ObjectArrayList<>();

    public GroovyScriptRegistry() {
        registry = this;
        addRegistry(new GroovyPowerTier());
    }

    @Override @Nullable public ModPropertyContainer createModPropertyContainer() {
        return this;
    }

    @Override public void addRegistry(INamed named) {
        super.addRegistry(named);
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
