package srki2k.tweakedlib.util.errorlogging;

import srki2k.tweakedlib.TweakedLib;

import java.util.ArrayList;
import java.util.List;

public final class ErrorLoggingUtil {

    private ErrorLoggingUtil() {
    }

    private static final List<ICustomLogger> customLoggers = new ArrayList<>();

    public static void addCustomLogger(ICustomLogger customLogger) {
        customLoggers.add(customLogger);
    }


    public static void validateState() {
        List<ICustomLogger> errorLoggers = new ArrayList<>();
        customLoggers.forEach(customLogger -> {
            if (customLogger.doCustomCheck()) {
                errorLoggers.add(customLogger);
            }
        });

        commonLog(errorLoggers);
    }

    public static void runtimeErrorLogging() {
        List<ICustomLogger> errorLoggers = new ArrayList<>();
        customLoggers.forEach(customLogger -> {
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
    }

    private static void logSetting(ICustomLogger customLogger) {
        TweakedLib.LOGGER.info("Configs (" + customLogger.modid() + "):");
        for (String s : customLogger.getConfigs()) {
            TweakedLib.LOGGER.info(s);
        }

    }

    private static void logErrors(ICustomLogger customLogger) {
        TweakedLib.LOGGER.info("Errors (" + customLogger.modid() + "):");
        for (String s : customLogger.getErrors()) {
            TweakedLib.LOGGER.fatal(s);
        }
    }

}


