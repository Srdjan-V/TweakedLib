package io.github.srdjanv.tweakedlib.mixin;

import blusunrize.immersiveengineering.common.util.compat.waila.IEWailaDataProvider;
import io.github.srdjanv.tweakedlib.api.waila.IEWallaOverwriteManager;
import io.github.srdjanv.tweakedlib.api.waila.IWailaIEBodyOverwrite;
import io.github.srdjanv.tweakedlib.api.waila.IWailaIENBTDataOverwrite;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = IEWailaDataProvider.class, remap = false)
public class IEWailaDataProviderMixin {

    @Inject(method = "getWailaBody", at = @At("HEAD"), cancellable = true)
    private void tweakedLib$getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config, CallbackInfoReturnable<List<String>> cir) {
        var tile = accessor.getTileEntity();
        if (tile != null) {
            IWailaIEBodyOverwrite overwrite = IEWallaOverwriteManager.getInstance().getIEBodyOverwrite(tile);
            if (overwrite != null) {
                cir.setReturnValue(overwrite.getWailaBody(itemStack, currenttip, accessor, config));
            }
        }
    }

    @Inject(method = "getNBTData", at = @At("HEAD"), cancellable = true)
    private void tweakedLib$getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos, CallbackInfoReturnable<NBTTagCompound> cir) {
        if (te != null) {
            IWailaIENBTDataOverwrite overwrite = IEWallaOverwriteManager.getInstance().getIENBTDataOverwrite(te);
            if (overwrite != null) {
                cir.setReturnValue(overwrite.getNBTData(player, te, tag, world, pos));
            }
        }
    }
}
