package io.github.srdjanv.tweakedlib.api.waila;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;

public final class IEWallaOverwriteManager {
    private static IEWallaOverwriteManager instance;

    public static IEWallaOverwriteManager getInstance() {
        if (instance == null) {
            instance = new IEWallaOverwriteManager();
        }

        return instance;
    }

    private IEWallaOverwriteManager() {
    }

    private final Map<Class<?>, IWailaIEBodyOverwrite> wailaIEBodyRegistry = new Object2ObjectOpenHashMap<>();

    public void registerIEBodyOverwrite(Class<?> clazz, IWailaIEBodyOverwrite overwrite) {
        wailaIEBodyRegistry.put(clazz, overwrite);
    }

    public IWailaIEBodyOverwrite getIEBodyOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, IWailaIEBodyOverwrite> entry : wailaIEBodyRegistry.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private final Map<Class<?>, IWailaIENBTDataOverwrite> wailaIENBTRegistry = new Object2ObjectOpenHashMap<>();

    public void registerIENBTDataOverwrite(Class<?> clazz, IWailaIENBTDataOverwrite overwrite) {
        wailaIENBTRegistry.put(clazz, overwrite);
    }

    public IWailaIENBTDataOverwrite getIENBTDataOverwrite(TileEntity entity) {
        for (Map.Entry<Class<?>, IWailaIENBTDataOverwrite> entry : wailaIENBTRegistry.entrySet()) {
            if (entry.getKey().isInstance(entity)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
