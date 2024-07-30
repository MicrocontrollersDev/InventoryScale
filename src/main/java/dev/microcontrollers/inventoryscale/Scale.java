package dev.microcontrollers.inventoryscale;

import dev.microcontrollers.inventoryscale.config.InventoryScaleConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;

/*
    The following class was taken from DulkirMod-Fabric under MPL 2.0
    https://github.com/inglettronald/DulkirMod-Fabric/blob/master/LICENSE
    No changes of note have been made other than adapting to this project
 */
public class Scale {
    public static float getScale() {
        if (MinecraftClient.getInstance().currentScreen instanceof HandledScreen<?>) {
            return InventoryScaleConfig.CONFIG.instance().containerSize;
        }
        return 1F;
    }
}
