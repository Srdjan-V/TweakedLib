package srki2k.tweakedlib.api.hei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.DimensionType;
import org.lwjgl.input.Keyboard;
import srki2k.tweakedlib.TweakedLib;
import srki2k.tweakedlib.api.powertier.PowerTierHandler;
import srki2k.tweakedlib.common.Constants;
import zmaster587.advancedRocketry.dimension.DimensionManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.IllegalFormatException;
import java.util.List;


@SuppressWarnings("deprecation")
public class BaseHEIUtil {

    public static final NumberFormat numberFormat = new DecimalFormat("#,###,###,###.#");

    public static final NumberFormat percentFormat = new DecimalFormat("00.00");

    private static final ItemStack[] catalyst = new ItemStack[2];

    private static IDrawable[] pumpjack;

    private static IDrawable[] excavator;

    public static void initPumpjackGui(IGuiHelper guiHelper, ItemStack catalyst, String modid) {
        if (pumpjack == null) {
            ResourceLocation location = new ResourceLocation(modid, "textures/gui/pumpjack.png");

            pumpjack = new IDrawable[2];
            pumpjack[0] = guiHelper.createDrawable(location, 0, 0, 77, 80);
            pumpjack[1] = guiHelper.createDrawable(location, 78, 0, 16, 16);

            BaseHEIUtil.catalyst[0] = catalyst;
        }
    }

    public static void initExcavatorGui(IGuiHelper guiHelper, ItemStack catalyst, String modid) {
        if (excavator == null) {
            ResourceLocation location = new ResourceLocation(modid, "textures/gui/excavator.png");

            excavator = new IDrawable[1];
            excavator[0] = guiHelper.createDrawable(location, 0, 0, 159, 77);

            BaseHEIUtil.catalyst[1] = catalyst;
        }
    }


    public static ItemStack getPumpjackCatalyst() {
        return catalyst[0];
    }

    public static IDrawable getPumpjackBackground() {
        return pumpjack[0];
    }

    public static IDrawable getPumpjackWarning() {
        return pumpjack[1];
    }


    public static ItemStack getExcavatorCatalyst() {
        return catalyst[1];
    }

    public static IDrawable getExcavatorBackground() {
        return excavator[0];
    }


    public static String formatString(String s) {
        return (Character.toUpperCase(s.charAt(0)) + s.substring(1)).replace("_", " ");
    }

    public static String detailedDimension(int[] dim) {
        StringBuilder stringBuilder = new StringBuilder();

        int dimLength = dim.length;
        if (dimLength == 0) {
            return stringBuilder.append("[]").toString();
        }

        int elements = 1;
        foundDim:
        for (int id : dim) {
            //Vanilla dims and dims registered with DimensionType.register()
            for (DimensionType dimensionType : DimensionType.values()) {
                if (dimensionType.getId() == id) {
                    stringBuilder.append(formatString(dimensionType.getName())).
                            append(" [").
                            append(id).
                            append("]");

                    if (elements < dimLength) {
                        stringBuilder.append(", ");
                        elements++;
                        continue foundDim;
                    }
                    return stringBuilder.toString();
                }
            }

            //Advanced Rocketry planets
            if (Constants.isAdvancedRocketryLoaded()) {
                DimensionManager dimensionManager = DimensionManager.getInstance();
                Integer[] dims = dimensionManager.getRegisteredDimensions();

                for (Integer integer : dims) {
                    if (integer == id) {
                        stringBuilder.append(formatString(dimensionManager.getDimensionProperties(id).getName())).
                                append(" [").
                                append(id).
                                append("]");

                        if (elements < dimLength) {
                            stringBuilder.append(", ");
                            elements++;
                            continue foundDim;
                        }
                        return stringBuilder.toString();
                    }
                }
            }
        }

        return stringBuilder.append("Wrong dimension id or Missing integration").toString();
    }


    public static void dimensionListData(List<String> list, int[] dimensionWhitelist, int[] dimensionBlacklist) {
        list.add(translateToLocalFormatted("tweakedlib.jei.dimensions"));
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            list.add(translateToLocalFormatted("tweakedlib.jei.dimension_whitelist", detailedDimension(dimensionWhitelist)));
            list.add(translateToLocalFormatted("tweakedlib.jei.dimension_blacklist", detailedDimension(dimensionBlacklist)));

            return;
        }

        list.add(translateToLocalFormatted("tweakedlib.jei.dimension_whitelist", Arrays.toString(dimensionWhitelist)));
        list.add(translateToLocalFormatted("tweakedlib.jei.dimension_blacklist", Arrays.toString(dimensionBlacklist)));

        list.add("");
        list.add(translateToLocalFormatted("tweakedlib.jei.lshift"));
    }

    public static void powerTierListData(List<String> list, int powerTier) {
        list.add(translateToLocalFormatted("tweakedlib.jei.power_tier", powerTier));
        list.add(translateToLocalFormatted("tweakedlib.jei.power_capacity", BaseHEIUtil.numberFormat.format(PowerTierHandler.getPowerTier(powerTier).getCapacity())));
        list.add(translateToLocalFormatted("tweakedlib.jei.power_usage", BaseHEIUtil.numberFormat.format(PowerTierHandler.getPowerTier(powerTier).getRft())));
    }

    public static String translateToLocal(String key) {
        return I18n.canTranslate(key) ? I18n.translateToLocal(key) : I18n.translateToFallback(key);
    }

    public static String translateToLocalFormatted(String key, Object... format) {
        String s = translateToLocal(key);

        try {
            return String.format(s, format);
        } catch (IllegalFormatException e) {
            TweakedLib.LOGGER.error("Format error: {}", s, e);
            return "Format error: " + s;
        }
    }

}
