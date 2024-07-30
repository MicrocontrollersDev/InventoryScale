package dev.microcontrollers.inventoryscale.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.microcontrollers.inventoryscale.Scale;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Mouse.class)
public class MouseMixin {
    @ModifyExpressionValue(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I"))
    private int onMouseButtonWidth(int originalScaledWidth) {
        return (int) (originalScaledWidth / Scale.getScale());
    }

    @ModifyExpressionValue(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    private int onMouseButtonHeight(int originalScaledHeight) {
        return (int) (originalScaledHeight / Scale.getScale());
    }

    //#if MC <= 1.20.4
    //$$ @ModifyExpressionValue(method = "onCursorPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I"))
    //$$ private int onCursorPosWidth(int originalScaledWidth) {
    //$$     return (int) (originalScaledWidth / Scale.getScale());
    //$$ }

    //$$ @ModifyExpressionValue(method = "onCursorPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    //$$ private int onCursorPosHeight(int originalScaledHeight) {
    //$$     return (int) (originalScaledHeight / Scale.getScale());
    //$$ }
    //#endif

    @ModifyExpressionValue(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledWidth()I"))
    private int onMouseScrollWidth(int originalScaledWidth) {
        return (int) (originalScaledWidth / Scale.getScale());
    }

    @ModifyExpressionValue(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
    private int onMouseScrollHeight(int originalScaledHeight) {
        return (int) (originalScaledHeight / Scale.getScale());
    }
}
