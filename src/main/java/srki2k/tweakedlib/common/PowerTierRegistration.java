package srki2k.tweakedlib.common;

import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.common.Configs.TLConfigs.DefaultMachinePowerTiers;

public class PowerTierRegistration {

    public static void init() {
        if (Constants.isTweakedExcavationLoaded()) {
            if (DefaultMachinePowerTiers.DefaultExcavatorPowerTiers.load) {
                PowerTierHandler.registerPowerUsage(DefaultMachinePowerTiers.DefaultExcavatorPowerTiers.powerTier,
                        DefaultMachinePowerTiers.DefaultExcavatorPowerTiers.capacity,
                        DefaultMachinePowerTiers.DefaultExcavatorPowerTiers.rft);
            }
        }

        if (Constants.isTweakedPetroleumLoaded()) {
            if (DefaultMachinePowerTiers.DefaultPumpjackPowerTiers.load) {
                PowerTierHandler.registerPowerUsage(DefaultMachinePowerTiers.DefaultPumpjackPowerTiers.powerTier,
                        DefaultMachinePowerTiers.DefaultPumpjackPowerTiers.capacity,
                        DefaultMachinePowerTiers.DefaultPumpjackPowerTiers.rft);
            }
        }

    }
}
