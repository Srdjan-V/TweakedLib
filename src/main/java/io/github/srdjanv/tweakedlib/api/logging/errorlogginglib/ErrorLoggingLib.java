package io.github.srdjanv.tweakedlib.api.logging.errorlogginglib;

import crafttweaker.CraftTweakerAPI;
import io.github.srdjanv.tweakedlib.TweakedLib;
import org.apache.logging.log4j.Logger;
import io.github.srdjanv.tweakedlib.common.Configs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ErrorLoggingLib {

    private ErrorLoggingLib() {
    }

    private static final Set<ICustomLogger> iCustomLoggerPool = new HashSet<>();

    public static void addCustomLogger(ICustomLogger customLogger) {
        iCustomLoggerPool.add(customLogger);
    }

    public static void validateState() {
        List<ICustomLogger> loggers = new ArrayList<>();

        iCustomLoggerPool.forEach(customLogger -> {
            if (customLogger.startupChecks()) {
                loggers.add(customLogger);
            }
        });

        if (Configs.TLConfigs.Logging.logMissingPowerTier) {
            commonRuntimeCheck(loggers);
        }

        commonLog(loggers, false);

        //Remove startup error loggers, and keep only runtime
        iCustomLoggerPool.removeIf(ICustomLogger::discardLoggerAfterStartup);

    }

    public static void runtimeErrorLogging() throws PowerTierNotFound {
        List<ICustomLogger> loggers = new ArrayList<>();
        commonRuntimeCheck(loggers);

        if (!commonLog(loggers, true)) {
            TweakedLib.LOGGER.warn("Runtime error reporting was called, but no errors where found");
            return;
        }

        List<String> errors = new ArrayList<>();

        for (ICustomLogger logger : loggers) {
            errors.addAll(logger.getErrors());
            logger.clean();//prevent memory leak with loliasm/vanilafix installed
        }

        throw new PowerTierNotFound(errors);
    }

    private static boolean commonLog(List<ICustomLogger> loggers, boolean isRuntime) {
        if (loggers.isEmpty()) {
            return false;
        }

        if (isRuntime) {
            for (ICustomLogger c : loggers) {
                logSetting(c);
            }
            return true;
        }

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
        logSetting(logger);
        logErrors(logger);
        logErrorsToUserWithCT(logger);
    }

    private static void logSetting(ICustomLogger logger) {
        String[] strings = logger.getConfigs();
        if (strings.length == 0) {
            return;
        }

        Logger modLogger = logger.getModLogger();
        modLogger.info("Configs (" + modLogger.getName() + "):");
        for (String s : strings) {
            modLogger.info(s);
        }

    }

    private static void logErrors(ICustomLogger logger) {
        Logger modLogger = logger.getModLogger();
        for (String s : logger.getErrors()) {
            modLogger.error(s);
        }
    }

    private static void logErrorsToUserWithCT(ICustomLogger logger) {
        CraftTweakerAPI.logError(logger.getModLogger().getName());
        for (String s : logger.getErrors()) {
            CraftTweakerAPI.logError(s);
        }
    }

}
