package dev.microcontrollers.inventoryscale.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
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


    @SerialEntry public int containerSize = 1;

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Inventory Scale"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Inventory Scale"))
                        .option(Option.createBuilder(int.class)
                                .name(Text.literal("Container Scale"))
                                .description(OptionDescription.of(Text.of("Change container scaling. Some values may make containers bigger than your screen. Set to 1 to use the default.")))
                                .binding(2, () -> config.containerSize, newVal -> config.containerSize = newVal)
                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                        .valueFormatter(value -> Text.of(String.format("%01d", value) + "x"))
                                        .range(1, 4)
                                        .step(0.5))
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
