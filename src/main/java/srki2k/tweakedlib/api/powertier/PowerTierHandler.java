package srki2k.tweakedlib.api.powertier;

import blusunrize.immersiveengineering.api.energy.immersiveflux.FluxStorageAdvanced;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.logging.errorlogginglib.ErrorLoggingLib;
import srki2k.tweakedlib.api.logging.errorlogginglib.PowerTierNotFound;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unused")
public final class PowerTierHandler {

    private static final HashMap<Integer, PowerTier> powerTierMap = new HashMap<>();

    private static final Set<PowerTier> powerTierList = new TreeSet<>();

    private static final PowerTier fallback;

    private static final int fallbackHashCode;

    static {
        //A fallback power tier instead of returning null or power tier 0
        fallback = new PowerTier(Integer.MAX_VALUE, 0);
        fallbackHashCode = fallback.hashCode();
        powerTierMap.put(fallbackHashCode, fallback);
        powerTierList.add(fallback);
    }

    /**
     * Registering a PowerTier object
     *
     * @param capacity The capacity, must be greater than rft
     * @param rft      The RF/t, must start from 1
     * @return true if the power tier has been registered false if not
     */
    public static int registerPowerTier(int capacity, int rft) {
        PowerTier powerTier = new PowerTier(capacity, rft);
        int hash = powerTier.hashCode();

        powerTierMap.put(hash, powerTier);
        powerTierList.add(powerTier);
        return hash;
    }

    public static PowerTier registerPowerTierAndReturnPowerTierObject(int capacity, int rft) {
        PowerTier powerTier = new PowerTier(capacity, rft);

        powerTierMap.put(powerTier.hashCode(), powerTier);
        powerTierList.add(powerTier);
        return powerTier;
    }


    /**
     * Gets the PowerTier object associated with the id
     * if it's not existing it will throw PowerTierNotFound exception
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     * @throws PowerTierNotFound Might throw if you try to get a non-existing power tier
     */
    public static PowerTier getPowerTier(int id) throws PowerTierNotFound {
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
        return powerTierMap.getOrDefault(id, fallback);
    }


    /**
     * Gets the Tier of the specified PowerTier object,
     * if it's not existing it will throw PowerTierNotFound exception
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     * @throws PowerTierNotFound Might throw if you try to get a non-existing power tier
     */
    public static int getTierOfSpecifiedPowerTier(int id) throws PowerTierNotFound {
        return indexOf(id,false);
    }

    /**
     * Gets the PowerTier object associated with the id,
     * if it's not existing return the fallback PowerTier
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     */
    public static int getTierOfSpecifiedPowerTierWithFallback(int id) {
        return indexOf(id,true);
    }

    private static int indexOf(int id, boolean fallback) {
        int index = 0;
        for (PowerTier powertier : powerTierList) {
            if (powertier.hashCode() == id) {
                return index;
            }
        }

        if (!fallback) {
            ErrorLoggingLib.runtimeErrorLogging();
        }

        return 0;
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

    public static boolean unRegisterPowerTier(int id) {
        return powerTierMap.remove(id) != null;
    }

    /**
     * Gets the Fallback PowerTier
     *
     * @return Returns PowerTier
     */
    public static PowerTier getFallbackPowerTier() {
        return fallback;
    }

    /**
     * Gets the Fallback PowerTier HashCode
     *
     * @return Returns PowerTier
     */
    public static int getFallbackPowerTierHashCode() {
        return fallbackHashCode;
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
    public static Integer[] getRegisteredIDs() {
        return powerTierMap.keySet().toArray(new Integer[0]);
    }

    public static void clearPowerTierList() {
        TweakedLib.LOGGER.info("Clearing Power Tier Map");
        powerTierMap.clear();
        powerTierMap.put(fallbackHashCode, fallback);
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
