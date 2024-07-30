package dev.microcontrollers.inventoryscale.mixin;

import dev.microcontrollers.inventoryscale.Scale;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {
    //#if MC >= 1.20.4
    @ModifyArgs(method = "drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;enableScissor(IIII)V"))
    private static void modifyScissor(Args args) {
        for (int i = 0; i < 4; i++) {
            args.set(i, (int) (((int) args.get(i)) * Scale.getScale()));
        }
    }
    //#endif
}
