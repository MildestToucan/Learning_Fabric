package net.mildtoucan.tutorialmod.item.custom;

import net.mildtoucan.tutorialmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class ChiselItem extends Item {
    public static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    ModBlocks.PINK_GARNET_DEEPSLATE_ORE, ModBlocks.RAW_PINK_GARNET_BLOCK,
                    Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE,
                    Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK,
                    Blocks.NETHER_BRICKS, Blocks.CHISELED_NETHER_BRICKS
            ); /* This Map creates pairs of blocks where the former (the "key") will be turned into the latter once it
    goes through the useOnBlock Method. */

    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock(); //These lines grab info from the World to identify the block being clicked on.

        if(CHISEL_MAP.containsKey(clickedBlock)) { //Checks when using the Chisel that the Map has a Key corresponding to the clicked block.
            if(!world.isClient()) { //This check is important: it checks that we're on the Server side of things rather than the Client side.
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1,((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()), //Tells the game to remove one durability
                        item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS); //Plays sound after usage
            }
        }


        return ActionResult.SUCCESS; //Tells the game the action went through as expected.
    }
}
