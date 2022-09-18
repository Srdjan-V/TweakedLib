package srki2k.tweakedlib.api.powertier;

import java.util.HashMap;

public final class PowerTierHandler {

    private static final HashMap<Integer, PowerTier> rftTier = new HashMap<>();

    static {
        //A fallback power tier instead of returning null or power tier 0
        rftTier.put(-1, new PowerTier(Integer.MAX_VALUE, 0));
    }

    /**
     * Sets the PowerTier object associated with the fluid of a given chunk
     *
     * @param tier     The tier of the power, must start from 0
     * @param capacity The capacity
     * @param rft      The RF/t
     */
    public static void registerPowerUsage(int tier, int capacity, int rft) {
        rftTier.put(tier, new PowerTier(capacity, rft));
    }

    /**
     * Gets the PowerTier object associated with the id
     *
     * @param id Power-tier id
     * @return Returns PowerTier
     */
    public static PowerTier getPowerTier(int id) {
        return rftTier.get(id);
    }

    /**
     * Gets the Fallback PowerTier
     *
     * @return Returns PowerTier
     */
    public static PowerTier getFallbackPowerTier() {
        return rftTier.get(-1);
    }

}
