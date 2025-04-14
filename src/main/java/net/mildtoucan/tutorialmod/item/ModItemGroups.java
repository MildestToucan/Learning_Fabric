package net.mildtoucan.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.mildtoucan.tutorialmod.TutorialMod;
import net.mildtoucan.tutorialmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_items"))
                    .icon(() -> new ItemStack(ModItems.PINK_GARNET))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.RAW_PINK_GARNET);
                    }).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                    }).build());




    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for: " + TutorialMod.MOD_ID);
    }
}
