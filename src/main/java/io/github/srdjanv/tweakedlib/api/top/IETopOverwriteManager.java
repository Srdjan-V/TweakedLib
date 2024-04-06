package io.github.srdjanv.tweakedlib.api.top;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;

public final class IETopOverwriteManager {
    private static IETopOverwriteManager instance;

    public static IETopOverwriteManager getInstance() {
        if (instance == null) instance = new IETopOverwriteManager();
        return instance;
    }

    private IETopOverwriteManager() {
    }

    private final Map<Class<?>, ITopProbeInfoOverwrite> energyInfoOverwrites = new Object2ObjectOpenHashMap<>();

    public void registerEnergyInfoOverwrite(Class<?> clazz, ITopProbeInfoOverwrite overwrite) {
        energyInfoOverwrites.put(clazz, overwrite);
    }

    public ITopProbeInfoOverwrite getEnergyInfoOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, ITopProbeInfoOverwrite> entry : energyInfoOverwrites.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }

}
