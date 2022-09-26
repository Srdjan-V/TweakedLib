package srki2k.tweakedlib.common.powertierlogging;

import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import srki2k.tweakedlib.api.logging.errorlogginglib.ICustomLogger;

import java.util.ArrayList;
import java.util.List;

import static srki2k.tweakedlib.common.Configs.TLConfigs.DefaultMachinePowerTiers.*;
import static srki2k.tweakedlib.common.Configs.TLConfigs.Logging.*;

public class PowerTierLogging implements ICustomLogger {

    public static void RegisterLogger() {
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
        String[] strings = new String[11];

        strings[0] = "Logging:";
        strings[1] = "Log missing Power Tiers" + logMissingContent;
        strings[2] = "Log errors to players" + logToPlayers;

        strings[3] = "Default Pumpjack Power Tiers:";
        strings[4] = "Load these configs" + DefaultPumpjackPowerTiers.load;
        strings[5] = "Power Tier ID" + DefaultPumpjackPowerTiers.capacity;
        strings[6] = "Default consumption" + DefaultPumpjackPowerTiers.rft;

        strings[7] = "Default Excavator Power Tiers:";
        strings[8] = "Load these configs" + DefaultExcavatorPowerTiers.load;
        strings[9] = "Power Tier ID" + DefaultExcavatorPowerTiers.capacity;
        strings[10] = "Default consumption" + DefaultExcavatorPowerTiers.rft;

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
