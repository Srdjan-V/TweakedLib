package srki2k.tweakedlib.common.powertierlogging;

import org.apache.logging.log4j.Logger;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import srki2k.tweakedlib.api.logging.errorlogginglib.ICustomLogger;

import java.util.ArrayList;
import java.util.List;

import static srki2k.tweakedlib.common.Configs.TLConfigs.DefaultMachinePowerTiers.*;

public class PowerTierLogging implements ICustomLogger {

    public static void RegisterLogger() {
        ErrorLoggingLib.addCustomLogger(new PowerTierLogging());
    }

    List<String> errors = new ArrayList<>();

    @Override
    public boolean startupChecks() {
        if (PowerTierHandler.getSize() == 1) {
            errors.add("No power tiers are registered");
            return true;
        }
        return false;
    }

    @Override
    public boolean runtimeChecks() {
        return false;
    }

    @Override
    public boolean discardLoggerAfterStartup() {
        return true;
    }

    @Override
    public Logger getModLogger() {
        return TweakedLib.LOGGER;
    }

    @Override
    public String[] getConfigs() {
        String[] strings = new String[8];

        strings[0] = "Default Pumpjack Power Tiers:";
        strings[1] = "Load these configs" + DefaultPumpjackPowerTiers.load;
        strings[2] = "Power Tier ID" + DefaultPumpjackPowerTiers.capacity;
        strings[3] = "Default consumption" + DefaultPumpjackPowerTiers.rft;

        strings[4] = "Default Excavator Power Tiers:";
        strings[5] = "Load these configs" + DefaultExcavatorPowerTiers.load;
        strings[6] = "Power Tier ID" + DefaultExcavatorPowerTiers.capacity;
        strings[7] = "Default consumption" + DefaultExcavatorPowerTiers.rft;

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
