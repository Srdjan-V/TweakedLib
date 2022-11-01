package srki2k.tweakedlib.common.compat.groovyscript;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.cleanroommc.groovyscript.api.GroovyLog;
import com.cleanroommc.groovyscript.helper.recipe.IRecipeBuilder;
import com.cleanroommc.groovyscript.registry.VirtualizedRegistry;
import srki2k.tweakedlib.api.powertier.PowerTier;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.common.Constants;

@SuppressWarnings("unused")
public final class TweakedGroovyPowerTier extends VirtualizedRegistry<PowerTier> {
    private static TweakedGroovyPowerTier instance;

    @GroovyBlacklist
    public static void init() {
        if (Constants.isGroovyScriptLoaded() && instance == null) {
            instance = new TweakedGroovyPowerTier();
            GroovyScriptCompat.getInstance().addRegistry(instance);
        }
    }

    private TweakedGroovyPowerTier() {
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
        PowerTierHandler.clearPowerTierList();
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

    public static class PowerTierBuilder implements IRecipeBuilder<PowerTier> {

        protected int capacity;
        protected int rft;
        protected boolean fallback;

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

            if (msg.hasSubMessages()) {
                msg.add("Returning Fallback Power Tier");
                msg.post();
                return true;
            }
            return false;
        }


        @Override
        public PowerTier register() {
            if (!validate()) {
                PowerTier powerTier = PowerTierHandler.registerPowerTierAndReturnPowerTierObject(capacity, rft);
                instance.addScripted(powerTier);
                return powerTier;
            }

            return PowerTierHandler.getFallbackPowerTier();
        }
    }

}