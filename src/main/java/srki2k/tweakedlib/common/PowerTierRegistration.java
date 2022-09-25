package srki2k.tweakedlib.common;

import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.common.Configs.TLConfigs.DefaultPowerTiers;

public class PowerTierRegistration {

    public static void init() {
        if (Constants.isTweakedExcavationLoaded()) {
            if (DefaultPowerTiers.DefaultExcavatorPowerTiers.load) {
                PowerTierHandler.registerPowerUsage(DefaultPowerTiers.DefaultExcavatorPowerTiers.powerTier,
                        DefaultPowerTiers.DefaultExcavatorPowerTiers.capacity,
                        DefaultPowerTiers.DefaultExcavatorPowerTiers.rft);
            }
        }

        if (Constants.isTweakedPetroleumLoaded()) {
            if (DefaultPowerTiers.DefaultPumpjackPowerTiers.load) {
                PowerTierHandler.registerPowerUsage(DefaultPowerTiers.DefaultPumpjackPowerTiers.powerTier,
                        DefaultPowerTiers.DefaultPumpjackPowerTiers.capacity,
                        DefaultPowerTiers.DefaultPumpjackPowerTiers.rft);
            }
        }

    }
}
