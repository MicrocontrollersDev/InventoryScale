package dev.microcontrollers.inventoryscale;

import dev.microcontrollers.inventoryscale.config.InventoryScaleConfig;
import net.fabricmc.api.ModInitializer;

public class InventoryScale implements ModInitializer {
	@Override
	public void onInitialize() {
		InventoryScaleConfig.CONFIG.load();
	}
}