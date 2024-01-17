package io.github.srdjanv.tweakedlib.api.integration;

import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ICustomLogger;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class DiscoveryHandler {
    private static DiscoveryHandler instance;

    public static DiscoveryHandler getInstance() {
        if (Objects.isNull(instance)) instance = new DiscoveryHandler();
        return instance;
    }

    public static void invalidate() {
        instance = null;
    }

    private final Map<String, List<IInitializer>> iInitializers = new Object2ObjectOpenHashMap<>();

    public void buildASMData(ASMDataTable asmDataTable) {
        handleModule(asmDataTable, IInitializer.class, iInitializer -> {
            String modId = iInitializer.getModID();
            List<IInitializer> modInits = iInitializers.get(modId);
            if (Objects.isNull(modInits)) {
                modInits = new ObjectArrayList<>();
                iInitializers.put(modId, modInits);
            }
            modInits.add(iInitializer);
        });
        handleModule(asmDataTable, ICustomLogger.class, ErrorLoggingLib::addCustomLogger);
    }

    private static <T> void handleModule(ASMDataTable asmDataTable, Class<T> match, Consumer<T> action) {
        for (ASMDataTable.ASMData data : asmDataTable.getAll(match.getName().replace('.', '/'))) {
            try {
                Class<?> clazz = Class.forName(data.getClassName().replace('/', '.'));
                //noinspection unchecked
                Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                action.accept(constructor.newInstance());
            } catch (ClassNotFoundException | InstantiationException e) {
                TweakedLib.LOGGER.error("Could not initialize class '{}'", data.getClassName());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void preInit(String modContainer, final FMLPreInitializationEvent event) {
        List<IInitializer> initializers = iInitializers.get(modContainer);
        if (Objects.nonNull(initializers)) initializers.forEach(iInitializer -> {
            if (iInitializer.shouldRun()) iInitializer.preInit(event);
        });
    }

    public void init(String modContainer, final FMLInitializationEvent event) {
        List<IInitializer> initializers = iInitializers.get(modContainer);
        if (Objects.nonNull(initializers)) initializers.forEach(iInitializer -> {
            if (iInitializer.shouldRun()) iInitializer.init(event);
        });
    }

    public void postInit(String modContainer, final FMLPostInitializationEvent event) {
        List<IInitializer> initializers = iInitializers.get(modContainer);
        if (Objects.nonNull(initializers)) initializers.forEach(iInitializer -> {
            if (iInitializer.shouldRun()) iInitializer.postInit(event);
        });
    }

}
