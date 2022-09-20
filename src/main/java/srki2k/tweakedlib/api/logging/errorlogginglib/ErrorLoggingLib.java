package srki2k.tweakedlib.api.logging.errorlogginglib;

import crafttweaker.CraftTweakerAPI;
import srki2k.tweakedlib.TweakedLib;

import java.util.ArrayList;
import java.util.List;

public final class ErrorLoggingLib {

    private ErrorLoggingLib() {
    }

    private static final List<ICustomLogger> iCustomLoggerPool = new ArrayList<>();

    public static void addCustomLogger(ICustomLogger customLogger) {
        iCustomLoggerPool.add(customLogger);
    }

    public static void validateState() {
        List<ICustomLogger> errorLoggers = new ArrayList<>();
        List<ICustomLogger> discardedErrorLoggers = new ArrayList<>();

        iCustomLoggerPool.forEach(customLogger -> {
            if (customLogger.doCustomCheck()) {
                errorLoggers.add(customLogger);
            }
            if (customLogger.discardLoggerAfterStartup()) {
                discardedErrorLoggers.add(customLogger);
            }
        });

        commonLog(errorLoggers);

        //Remove startup error loggers, and keep only runtime
        for (ICustomLogger i : discardedErrorLoggers) {
            iCustomLoggerPool.remove(i);
        }
    }

    public static void runtimeErrorLogging() {
        List<ICustomLogger> errorLoggers = new ArrayList<>();
        iCustomLoggerPool.forEach(customLogger -> {
            if (customLogger.handleRuntimeErrors()) {
                errorLoggers.add(customLogger);
            }
        });

        if (commonLog(errorLoggers)) {
            TweakedLib.LOGGER.warn("Runtime error reporting was called, but no errors where found");
            return;
        }

        throw new Error("Check the logs for Tweaked Lib errors");
    }

    private static boolean commonLog(List<ICustomLogger> errorLoggers) {
        if (errorLoggers.isEmpty()) {
            return true;
        }

        for (ICustomLogger c : errorLoggers) {
            loggAll(c);
            c.clean();
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void loggAll(ICustomLogger customLogger) {
        logSetting(customLogger);
        logErrors(customLogger);
        logErrorsToUserWithCT(customLogger);
    }

    private static void logSetting(ICustomLogger customLogger) {
        String[] strings = customLogger.getConfigs();
        if (strings.length == 0) {
            return;
        }

        TweakedLib.LOGGER.info("Configs (" + customLogger.getMODID() + "):");
        for (String s : strings) {
            TweakedLib.LOGGER.info(s);
        }

    }

    private static void logErrors(ICustomLogger customLogger) {
        TweakedLib.LOGGER.info("Errors (" + customLogger.getMODID() + "):");
        for (String s : customLogger.getErrors()) {
            TweakedLib.LOGGER.fatal(s);
        }
    }

    private static void logErrorsToUserWithCT(ICustomLogger customLogger) {
        if (!customLogger.logErrorToUsersInGameWithCT()) {
            return;
        }

        CraftTweakerAPI.logError("Errors (" + customLogger.getMODID() + "):");
        for (String s : customLogger.getErrors()) {
            CraftTweakerAPI.logError(s);
        }
    }

}
