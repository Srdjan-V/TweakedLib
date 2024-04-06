package io.github.srdjanv.tweakedlib.mixin;

import mcp.mobius.waila.api.impl.MetaDataProvider;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = MetaDataProvider.class, remap = false)
public class MetaDataProviderMixin {

/*    @WrapOperation(method = "handleBlockTextData",
            at = @At(value = "INVOKE", target = "Lmcp/mobius/waila/api/IWailaDataProvider;getWailaBody(Lnet/minecraft/item/ItemStack;Ljava/util/List;Lmcp/mobius/waila/api/IWailaDataAccessor;Lmcp/mobius/waila/api/IWailaConfigHandler;)Ljava/util/List;"))
    private List<String> bypassExpensiveCalculationIfNecessary(IWailaDataProvider instance,
                                                               ItemStack itemStack, List<String> tooltip,
                                                               IWailaDataAccessor accessor, IWailaConfigHandler config,
                                                               Operation<List<String>> original) {
        WallaOverwriteManager woInstance = WallaOverwriteManager.getInstance();
        var over = woInstance.getWailaBodyOverwrite(accessor.getTileEntity());
        List<String> ret = null;
        if (over != null) {
          //  ret = over.getWailaBody(itemStack, tooltip, accessor, config);
        }
        if (ret == null) return original.call(instance, itemStack, tooltip, accessor, config);
        return ret;
    }*/
}
