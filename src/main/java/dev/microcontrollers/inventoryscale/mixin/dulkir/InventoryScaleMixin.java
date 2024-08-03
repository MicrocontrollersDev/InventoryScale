package dev.microcontrollers.inventoryscale.mixin.dulkir;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.moulberry.mixinconstraints.annotations.IfModLoaded;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@IfModLoaded(value = "dulkirmod-fabric")
@Pseudo
@Mixin(targets = "com.dulkirfabric.features.InventoryScale")
public class InventoryScaleMixin {
    @Dynamic("DulkirMod-Fabric")
    @ModifyReturnValue(method = "getScale", at = @At("RETURN"))
    private float disableDulkirScale(float value) {
        return 1F;
    }
}
