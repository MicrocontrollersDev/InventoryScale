package dev.microcontrollers.inventoryscale.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.microcontrollers.inventoryscale.Scale;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GameRenderer.class)
public class GameRendererMixin {
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;renderWithTooltip(Lnet/minecraft/client/gui/DrawContext;IIF)V"), index = 1)
    private int fixMouseX(int mouseX) {
        return (int) (mouseX / Scale.getScale());
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;renderWithTooltip(Lnet/minecraft/client/gui/DrawContext;IIF)V"), index = 2)
    private int fixMouseY(int mouseY) {
        return (int) (mouseY / Scale.getScale());
    }


    // Here local capture was changed to a @Local as it's less brittle
    @Inject(method = "render", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;", shift = At.Shift.BEFORE, ordinal = 1))
    //#if MC >= 1.21
    private void onScreenRenderPre(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //#else
    //$$ private void aonScreenRenderPre(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //#endif
        drawContext.getMatrices().push();
        drawContext.getMatrices().scale(Scale.getScale(), Scale.getScale(), 1f);
    }

    // Here local capture was changed to a @Local as it's less brittle
    @Inject(method = "render", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;", shift = At.Shift.AFTER, ordinal = 3))
    //#if MC >= 1.21
    private void onScreenRenderPost(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //#else
    //$$ private void aonScreenRenderPost(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
    //#endif
        drawContext.getMatrices().pop();
    }


}
