package srki2k.tweakedlib.api.powertier;

import blusunrize.immersiveengineering.api.energy.immersiveflux.FluxStorageAdvanced;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;

import java.util.HashMap;

@SuppressWarnings("unused")
public final class PowerTierHandler {

    private static final HashMap<Integer, PowerTier> powerTierMap = new HashMap<>();

    static {
        //A fallback power tier instead of returning null or power tier 0
        powerTierMap.put(-1, new PowerTier(Integer.MAX_VALUE, 0));
    }

    /**
     * Registering a PowerTier object
     *
     * @param tier     The tier of the power, must start from 0
     * @param capacity The capacity
     * @param rft      The RF/t
     * @return true if the power tier has been registered false if not
     */
    public static boolean registerPowerTier(int tier, int capacity, int rft) {
        if (powerTierMap.get(tier) != null) {
            return false;
        }

        powerTierMap.put(tier, new PowerTier(capacity, rft));
        return true;
    }

    /**
     * Gets the PowerTier object associated with the id
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     * @throws RuntimeException Might throw if you try to get a non-existing power tier
     */
    public static PowerTier getPowerTier(int id) {
        PowerTier powerTier = powerTierMap.get(id);
        if (powerTier == null) {
            ErrorLoggingLib.runtimeErrorLogging();
        }
        return powerTier;
    }

    /**
     * Gets the PowerTier object associated with the id,
     * if it's not existing return the fallback PowerTier
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     */
    public static PowerTier getPowerTierWithFallback(int id) {
        PowerTier powerTier = powerTierMap.get(id);
        if (powerTier == null) {
            return getFallbackPowerTier();
        }
        return powerTier;
    }

    /**
     * returns if that PowerTier object exists
     *
     * @param id Power-tier id
     * @return If it exists
     */
    public static boolean powerTierExists(int id) {
        return powerTierMap.get(id) != null;
    }

    /**
     * Gets the Fallback PowerTier
     *
     * @return Returns PowerTier
     */
    public static PowerTier getFallbackPowerTier() {
        return powerTierMap.get(-1);
    }

    /**
     * Gets the number of PowerTier
     *
     * @return Returns int
     */
    public static int getSize() {
        return powerTierMap.size();
    }

    /**
     * Gets all the PowerTier
     *
     * @return Returns PowerTier[]
     */
    public static PowerTier[] getAllPowerTiers() {
        return powerTierMap.values().toArray(new PowerTier[0]);
    }

    /**
     * Gets gets the ids of registered PowerTiers
     *
     * @return Returns Integer[]
     */
    public static Integer[] getRegisteredIDDs() {
        return powerTierMap.keySet().toArray(new Integer[0]);
    }

    /**
     * Initialize FluxStorage with the provided power tier
     *
     * @param fluxStorage FluxStorageAdvanced object to initialize
     * @param powerTier   PowerTier object
     */
    public static void initFluxStorageWithPowerTier(FluxStorageAdvanced fluxStorage, PowerTier powerTier) {
        fluxStorage.setCapacity(powerTier.getCapacity());
        fluxStorage.setLimitReceive(Integer.min(powerTier.getRft() * 2, powerTier.getCapacity()));
        fluxStorage.setMaxExtract(powerTier.getRft());
    }

    /**
     * Initialize FluxStorage with the provided powerTierID
     *
     * @param fluxStorage FluxStorageAdvanced object to initialize
     * @param powerTierID PowerTier id
     */
    public static void initFluxStorageWithPowerTierID(FluxStorageAdvanced fluxStorage, int powerTierID) {
        PowerTier powerTier = getPowerTier(powerTierID);
        fluxStorage.setCapacity(powerTier.getCapacity());
        fluxStorage.setLimitReceive(Integer.min(powerTier.getRft() * 2, powerTier.getCapacity()));
        fluxStorage.setMaxExtract(powerTier.getRft());
    }

}
