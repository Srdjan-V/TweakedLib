package srki2k.tweakedlib.api.hei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class BaseHEIUtil {

    public static final NumberFormat numberFormat = new DecimalFormat("#,###,###,###.#");

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

}
