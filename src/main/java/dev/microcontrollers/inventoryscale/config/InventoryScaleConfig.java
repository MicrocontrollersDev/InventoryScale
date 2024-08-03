package dev.microcontrollers.inventoryscale.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class InventoryScaleConfig {
    public static final ConfigClassHandler<InventoryScaleConfig> CONFIG = ConfigClassHandler.createBuilder(InventoryScaleConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("inventoryscale.json"))
                    .build())
            .build();


    @SerialEntry public float containerSize = 1F;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.translatable("inventory-scale.inventory-scale"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("inventory-scale.inventory-scale"))
                        .option(Option.<Float>createBuilder()
                                .name(Text.translatable("inventory-scale.container-scale"))
                                .description(OptionDescription.of(Text.translatable("inventory-scale.container-scale.description")))
                                .binding(2F, () -> config.containerSize, newVal -> config.containerSize = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .formatValue(value -> Text.of(String.format("%.1f", value) + "x"))
                                        .range(0.5F, 6F)
                                        .step(0.1F))
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
