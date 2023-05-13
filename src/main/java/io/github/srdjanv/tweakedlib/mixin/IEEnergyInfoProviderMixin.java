package io.github.srdjanv.tweakedlib.mixin;

import blusunrize.immersiveengineering.common.util.compat.OneProbeHelper;
import io.github.srdjanv.tweakedlib.api.top.ITopProbeInfoOverwrite;
import io.github.srdjanv.tweakedlib.api.top.TopOverwriteManager;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = OneProbeHelper.EnergyInfoProvider.class, remap = false)
public class IEEnergyInfoProviderMixin {

    @Inject(method = "addProbeInfo", at = @At("HEAD"), cancellable = true)
    private void tweakedLib$addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data, CallbackInfo ci) {
        TileEntity tile = world.getTileEntity(data.getPos());
        if (tile != null) {
            ITopProbeInfoOverwrite overwrite = TopOverwriteManager.getInstance().getOverwrite(tile);
            if (overwrite != null) {
                overwrite.addProbeInfo(tile, mode, probeInfo, player, world, blockState, data);
                ci.cancel();
            }
        }
    }
}
