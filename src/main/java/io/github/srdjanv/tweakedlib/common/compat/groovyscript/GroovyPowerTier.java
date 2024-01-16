package io.github.srdjanv.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.helper.recipe.IRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTier;
import io.github.srdjanv.tweakedlib.api.powertier.PowerTierHandler;

public class GroovyPowerTier extends VirtualizedRegistry<PowerTier> {

    @GroovyBlacklist
    GroovyPowerTier() {
        super("PowerTier", "powerTier");
    }

    @Override
    @GroovyBlacklist
    public void onReload() {
        removeScripted().forEach((powerTier) -> PowerTierHandler.unRegisterPowerTier(powerTier.hashCode()));
        restoreFromBackup().forEach(powerTier -> PowerTierHandler.registerPowerTier(powerTier.getCapacity(), powerTier.getRft()));
    }

    public void removeAll() {
        for (PowerTier powerTier : PowerTierHandler.getAllPowerTiers()) {
            addBackup(powerTier);
        }
        PowerTierHandler.clearPowerTiers();
    }

    public void remove(int id) {
        if (PowerTierHandler.unRegisterPowerTier(id)) {
            addBackup(PowerTierHandler.getPowerTier(id));
        }
    }

    public void remove(PowerTier id) {
        if (PowerTierHandler.unRegisterPowerTier(id.hashCode())) {
            addBackup(id);
        }
    }

    public PowerTierBuilder recipeBuilder() {
        return new PowerTierBuilder();
    }

    public class PowerTierBuilder implements IRecipeBuilder<PowerTier> {

        private int capacity;
        private int rft;

        public PowerTierBuilder() {
        }

        public PowerTierBuilder capacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public PowerTierBuilder rtf(int rft) {
            this.rft = rft;
            return this;
        }

        @Override
        public boolean validate() {
            GroovyLog.Msg msg = GroovyLog.msg("Error adding custom Power Tier", "").error();

            msg.add(capacity == Integer.MAX_VALUE,
                    () -> "PowerTier capacity should not be MAX_INT!");

            msg.add(capacity < rft,
                    () -> "PowerTier capacity can not be smaller than rft!");

            msg.add(rft <= 0,
                    () -> "PowerTier rf/t can not be smaller than 1!");

            return !msg.postIfNotEmpty();
        }

        @Override
        public PowerTier register() {
            if (validate()) {
                PowerTier powerTier = PowerTierHandler.registerPowerTierAndReturnPowerTierObject(capacity, rft);
                addScripted(powerTier);
                return powerTier;
            }

            return null;
        }
    }
}