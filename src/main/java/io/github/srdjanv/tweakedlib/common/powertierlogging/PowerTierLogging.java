package io.github.srdjanv.tweakedlib.common.powertierlogging;

import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ICustomLogger;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.apache.logging.log4j.Logger;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTierHandler;

import java.util.ArrayList;
import java.util.List;

import static io.github.srdjanv.tweakedlib.common.Configs.TLConfigs.Logging.logMissingPowerTier;

public class PowerTierLogging implements ICustomLogger {

    private List<String> errors = new ObjectArrayList<>();

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
        String[] strings = new String[1];

        strings[0] = "Logging: " + logMissingPowerTier;

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
