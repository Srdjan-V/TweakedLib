package srki2k.tweakedlib.api.logging.errorlogginglib;

import crafttweaker.CraftTweakerAPI;
import org.apache.logging.log4j.Logger;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.common.Configs;

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

        commonLog(loggers);

        //Remove startup error loggers, and keep only runtime
        iCustomLoggerPool.removeIf(ICustomLogger::discardLoggerAfterStartup);

    }

    public static void runtimeErrorLogging() {
        List<ICustomLogger> loggers = new ArrayList<>();
        commonRuntimeCheck(loggers);

        if (!commonLog(loggers)) {
            TweakedLib.LOGGER.warn("Runtime error reporting was called, but no errors where found");
            return;
        }

        throw new RuntimeException("Check the logs");
    }

    private static boolean commonLog(List<ICustomLogger> loggers) {
        if (loggers.isEmpty()) {
            return false;
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
