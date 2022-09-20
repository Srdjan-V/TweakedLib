package srki2k.tweakedlib.common.powertierlogging;

import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import srki2k.tweakedlib.api.logging.errorlogginglib.ICustomLogger;

import java.util.ArrayList;
import java.util.List;

import static srki2k.tweakedlib.common.Configs.TLConfigs.Logging.*;

public class PowerTierLogging implements ICustomLogger {

    public static void RegisterLogger() {
        if (!disableLogging) {
            return;
        }
        ErrorLoggingLib.addCustomLogger(new PowerTierLogging());
    }

    List<String> errors = new ArrayList<>();

    @Override
    public boolean doCustomCheck() {
        if (PowerTierHandler.getSize() == 1) {
            errors.add("No power tiers are registered");
            return true;
        }
        return false;
    }

    @Override
    public boolean handleRuntimeErrors() {
        return false;
    }

    @Override
    public boolean discardLoggerAfterStartup() {
        return true;
    }

    @Override
    public boolean logErrorToUsersInGameWithCT() {
        return logToPlayers;
    }

    @Override
    public String getMODID() {
        return TweakedLib.MODID;
    }

    @Override
    public String[] getConfigs() {
        String[] strings = new String[2];
        strings[0] = "Log missing Power Tiers" + logMissingContent;
        strings[1] = "Log errors to players" + logToPlayers;

        return strings;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void clean() {
        errors = null;
    }
}
