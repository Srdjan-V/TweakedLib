package srki2k.tweakedlib.common.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.TweakedLib.TweakedPowerTier")
@ZenRegister
public class TweakedPowerTier {

    @ZenMethod
    public static void registerPowerTier(int tier, int capacity, int rft) {
        if (tier < 0) {
            CraftTweakerAPI.logError("PowerTier can not be smaller than 0!");
        }
        if (capacity < 1) {
            CraftTweakerAPI.logError("PowerTier capacity can not be smaller than 1!");
        }
        if (capacity == Integer.MAX_VALUE) {
            CraftTweakerAPI.logError("PowerTier capacity should not be MAX_INT!");
            capacity--;
        }
        if (capacity < rft) {
            CraftTweakerAPI.logError("PowerTier capacity can not be smaller than rft!");
        }

        if (PowerTierHandler.registerPowerTier(tier, capacity, rft)) {
            CraftTweakerAPI.logInfo("Added power tier: " + tier + " with capacity: " + capacity + " and " + rft + " RF/t");
            return;
        }

        CraftTweakerAPI.logError("PowerTier " + tier + " is already retested");

    }

}