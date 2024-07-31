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

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Inventory Scale"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Inventory Scale"))
                        .option(Option.createBuilder(float.class)
                                .name(Text.literal("Container Scale"))
                                .description(OptionDescription.of(Text.of("Change container scaling. Some values may make containers bigger than your screen. Set to 1x to use the default.")))
                                .binding(2F, () -> config.containerSize, newVal -> config.containerSize = newVal)
                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%.1f", value) + "x"))
                                        .range(0.5F, 6F)
                                        .step(0.1F))
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
