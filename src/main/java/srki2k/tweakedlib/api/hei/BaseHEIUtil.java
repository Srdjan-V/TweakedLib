package srki2k.tweakedlib.api.hei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import srki2k.tweakedlib.common.Constants;
import zmaster587.advancedRocketry.dimension.DimensionManager;

import java.text.DecimalFormat;
import java.text.NumberFormat;


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
            pumpjack[0] = guiHelper.createDrawable(location, 0, 0, 84, 80);
            pumpjack[1] = guiHelper.createDrawable(location, 85, 0, 102, 17);

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

}
