package srki2k.tweakedlib.powertierlogging;

import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.util.errorlogging.ICustomLogger;

import java.util.ArrayList;
import java.util.List;

public class PowerTierLogging implements ICustomLogger {
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
        // TODO: 20/09/2022 Add Config
        return true;
    }

    @Override
    public String modid() {
        return TweakedLib.MODID;
    }

    @Override
    public String[] getConfigs() {
        return new String[0];
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
