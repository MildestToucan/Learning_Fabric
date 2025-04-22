package net.mildtoucan.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mildtoucan.tutorialmod.TutorialMod;
import net.mildtoucan.tutorialmod.block.custom.MagicBlock;
import net.mildtoucan.tutorialmod.block.custom.PinkGarnetLampBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.TUFF)));

    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)));


    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create()
                    .strength(3f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block PINK_GARNET_STAIRS = registerBlock("pink_garnet_stairs",
            new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));
    /*We're essentially asking the game to register a stairs block that'll take the default block state of the pink
    garnet block for its appearance. Our other non-cubic blocks will be based on this, so might aswell just duplicate
    and then modify.*/

    public static final Block PINK_GARNET_SLAB = registerBlock("pink_garnet_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));

    /* A slab block only requires the AbstractBlock.Settings */


    public static final Block PINK_GARNET_BUTTON = registerBlock("pink_garnet_button",
            new ButtonBlock(BlockSetType.IRON, 2,
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()
                            .noCollision()));

    /*A new button block can be created from an existing BlockSetType. Remember to remove the collision.*/

    public static final Block PINK_GARNET_PRESSURE_PLATE = registerBlock("pink_garnet_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));


    public static final Block PINK_GARNET_FENCE = registerBlock("pink_garnet_fence",
            new FenceBlock(AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));


    public static final Block PINK_GARNET_FENCE_GATE = registerBlock("pink_garnet_fence_gate",
            new FenceGateBlock(WoodType.ACACIA,
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));


    public static final Block PINK_GARNET_WALL = registerBlock("pink_garnet_wall",
            new WallBlock(AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()));


    public static final Block PINK_GARNET_DOOR = registerBlock("pink_garnet_door",
            new DoorBlock(BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()
                            .nonOpaque()));


    public static final Block PINK_GARNET_TRAPDOOR = registerBlock("pink_garnet_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON,
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()
                            .nonOpaque()));

    //The .nonOpaque Method is important if you have any see-through elements in a block's texture
    //Remember to add all of these to the ModItemGroups Class

    public static final Block PINK_GARNET_LAMP = registerBlock("pink_garnet_lamp", new PinkGarnetLampBlock
            (AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0))); //This makes the luminance of the block dependent on the CLICKED BlockState.
    //If the CLICKED boolean is true, then it'll have 15 luminance, else 0. Think of it as setting conditions for the light level.

    private static Block registerBlock(String name, Block block) {
        registerBlocKItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }


    private static void registerBlocKItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PINK_GARNET_BLOCK);
            entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
        });
    }
}
