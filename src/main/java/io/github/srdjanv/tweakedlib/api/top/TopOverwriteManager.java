package io.github.srdjanv.tweakedlib.api.top;

import net.minecraft.tileentity.TileEntity;

import java.util.HashMap;
import java.util.Map;

public final class TopOverwriteManager {
    private static TopOverwriteManager instance;

    public static TopOverwriteManager getInstance() {
        if (instance == null) {
            instance = new TopOverwriteManager();
        }

        return instance;
    }

    private TopOverwriteManager() {
    }

    private final Map<Class<?>, ITopProbeInfoOverwrite> registry = new HashMap<>();

    public void registerOverwrite(Class<?> clazz, ITopProbeInfoOverwrite overwrite) {
        registry.put(clazz, overwrite);
    }

    public ITopProbeInfoOverwrite getOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, ITopProbeInfoOverwrite> entry : registry.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
