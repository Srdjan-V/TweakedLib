package srki2k.tweakedlib.common.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@SuppressWarnings("unused")
@ZenClass("mods.TweakedLib.TweakedPowerTier")
@ZenRegister
public class TweakedPowerTier {

    @ZenMethod
    public static int registerPowerTier(int capacity, int rft) {
        boolean isValid = true;
        if (capacity == Integer.MAX_VALUE) {
            CraftTweakerAPI.logError("PowerTier capacity should not be MAX_INT!");
            isValid = false;
        }
        if (capacity < rft) {
            CraftTweakerAPI.logError("PowerTier capacity can not be smaller than rft!");
            isValid = false;
        }

       int powerTier =  PowerTierHandler.registerPowerTier(capacity, rft);
        if (powerTier != PowerTierHandler.getFallbackPowerTierHashCode() && isValid) {
            CraftTweakerAPI.logInfo("Added powerTier with capacity: " + capacity + " and " + rft + " RF/t");
            return powerTier;
        }

        CraftTweakerAPI.logError("Returning FallbackPowerTier");
        return PowerTierHandler.getFallbackPowerTierHashCode();
    }

}