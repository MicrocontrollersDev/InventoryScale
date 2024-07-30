package dev.microcontrollers.inventoryscale.mixin;

import dev.microcontrollers.inventoryscale.Scale;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.lang.Math.ceil;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow public int width;
    @Shadow public int height;

    @Inject(method = "init(Lnet/minecraft/client/MinecraftClient;II)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/Screen;height:I", shift = At.Shift.AFTER))
    private void onInitAfterViewportSizeSet(MinecraftClient client, int width, int height, CallbackInfo ci) {
        this.width = (int) ceil((double) width / Scale.getScale());
        this.height = (int) ceil((double) height / Scale.getScale());
    }

    @Inject(method = "resize", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/Screen;height:I", shift = At.Shift.AFTER))
    private void onResizeAfterViewportSizeSet(MinecraftClient client, int width, int height, CallbackInfo ci) {
        this.width = (int) ceil((double) width / Scale.getScale());
        this.height = (int) ceil((double) height / Scale.getScale());
    }
}
