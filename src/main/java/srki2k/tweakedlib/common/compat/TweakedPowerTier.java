package srki2k.tweakedlib.common.compat;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.TweakedLib.TweakedPowerTier")
@ZenRegister
public class TweakedPowerTier {

    @ZenMethod
    public static void registerPowerUsage(int tier, int capacity, int rft) {
        if (tier < 0) {
            CraftTweakerAPI.logError("PowerUsage tier can not be smaller than 0!");
        }
        if (capacity < 1) {
            CraftTweakerAPI.logError("PowerUsage capacity can not be smaller than 1!");
        }
        if (capacity == Integer.MAX_VALUE) {
            CraftTweakerAPI.logError("PowerUsage capacity should not be MAX_INT!");
        }
        if (capacity < rft) {
            CraftTweakerAPI.logError("PowerUsage capacity can not be smaller than rft!");
        }

        PowerTierHandler.registerPowerUsage(tier, capacity, rft);
        CraftTweakerAPI.logInfo("Added power tier: " + tier + " with capacity: " + capacity + " and " + rft + " RF/t");

    }

}