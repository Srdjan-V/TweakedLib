package io.github.srdjanv.tweakedlib.common.powertierlogging;

import io.github.srdjanv.tweakedlib.TweakedLib;
import io.github.srdjanv.tweakedlib.api.logging.errorlogginglib.ICustomLogger;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.apache.logging.log4j.Logger;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTierHandler;

import java.util.List;

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
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void clean() {
        errors = null;
    }
}
