package io.github.srdjanv.tweakedlib.api.logging.errorlogginglib;

import io.github.srdjanv.tweakedlib.TweakedLib;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.apache.logging.log4j.Logger;
import io.github.srdjanv.tweakedlib.common.Configs;

import java.util.List;
import java.util.function.Consumer;

public final class ErrorLoggingLib {

    private ErrorLoggingLib() {
    }

    private static final List<ICustomLogger> iCustomLoggerPool = new ObjectArrayList<>();

    public static void addCustomLogger(ICustomLogger customLogger) {
        iCustomLoggerPool.add(customLogger);
        TweakedLib.LOGGER.info("Registered:'{}', class impl:'{}'",
                ICustomLogger.class.getSimpleName(), customLogger.getClass().getName());
    }

    private static final List<Consumer<ICustomLogger>> customLoggersListener = new ObjectArrayList<>();

    public static void registerCustomLoggerListener(Consumer<ICustomLogger> listener) {
        customLoggersListener.add(listener);
    }

    public static void validateState() {
        List<ICustomLogger> loggers = new ObjectArrayList<>();

        iCustomLoggerPool.forEach(customLogger -> {
            if (customLogger.startupChecks()) {
                loggers.add(customLogger);
            }
        });

        if (Configs.logging.runRuntimeChecksOnStartup) {
            commonRuntimeCheck(loggers);
        }

        commonLog(loggers, false);

        //Remove startup error loggers, and keep only runtime
        iCustomLoggerPool.removeIf(ICustomLogger::discardLoggerAfterStartup);
    }

    public static void runtimeErrorLogging() throws PowerTierNotFound {
        List<ICustomLogger> loggers = new ObjectArrayList<>();
        commonRuntimeCheck(loggers);

        if (!commonLog(loggers, true)) {
            TweakedLib.LOGGER.warn("Runtime error reporting was called, but no errors where found");
            return;
        }

        List<String> errors = new ObjectArrayList<>();

        for (ICustomLogger logger : loggers) {
            errors.addAll(logger.getErrors());
            logger.clean();//prevent memory leak with loliasm/vanilafix installed
        }

        throw new PowerTierNotFound(errors);
    }

    private static boolean commonLog(List<ICustomLogger> loggers, boolean isRuntime) {
        if (loggers.isEmpty()) return false;
        if (isRuntime) return true;
        for (ICustomLogger c : loggers) {
            loggAll(c);
            c.clean();
        }
        return true;
    }

    private static void commonRuntimeCheck(List<ICustomLogger> loggers) {
        iCustomLoggerPool.forEach(customLogger -> {
            if (customLogger.runtimeChecks()) {
                loggers.add(customLogger);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void loggAll(ICustomLogger logger) {
        logErrors(logger);
        for (Consumer<ICustomLogger> iCustomLoggerConsumer : customLoggersListener)
            iCustomLoggerConsumer.accept(logger);
    }

    private static void logErrors(ICustomLogger logger) {
        Logger modLogger = logger.getModLogger();
        for (String s : logger.getErrors()) {
            modLogger.error(s);
        }
    }

}
