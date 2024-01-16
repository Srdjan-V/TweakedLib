package io.github.srdjanv.tweakedlib.api.waila;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;

public final class WallaOverwriteManager {
    private static WallaOverwriteManager instance;

    public static WallaOverwriteManager getInstance() {
        if (instance == null) {
            instance = new WallaOverwriteManager();
        }

        return instance;
    }

    private WallaOverwriteManager() {
    }

    private final Map<Class<?>, IWailaIEBodyOverwrite> wailaBodyRegistry = new Object2ObjectOpenHashMap<>();

    public void registerBodyOverwrite(Class<?> clazz, IWailaIEBodyOverwrite overwrite) {
        wailaBodyRegistry.put(clazz, overwrite);
    }

    public IWailaIEBodyOverwrite getBodyOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, IWailaIEBodyOverwrite> entry : wailaBodyRegistry.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private final Map<Class<?>, IWailaIENBTDataOverwrite> wailaNBTRegistry = new Object2ObjectOpenHashMap<>();

    public void registerNBTDataOverwrite(Class<?> clazz, IWailaIENBTDataOverwrite overwrite) {
        wailaNBTRegistry.put(clazz, overwrite);
    }

    public IWailaIENBTDataOverwrite getNBTDataOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, IWailaIENBTDataOverwrite> entry : wailaNBTRegistry.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
