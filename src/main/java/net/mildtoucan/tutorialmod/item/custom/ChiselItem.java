package net.mildtoucan.tutorialmod.item.custom;

import net.mildtoucan.tutorialmod.block.ModBlocks;
import net.mildtoucan.tutorialmod.component.ModDataComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
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

                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
                //Saves coordinates of the last place it modified a block at.
                //You can delete the data with the below line
                //context.getStack().set(ModDataComponentTypes.COORDINATES, null);

            }
        }


        return ActionResult.SUCCESS; //Tells the game the action went through as expected. Makes the player do a right click animation
    }

    @Override //This here will contain a tooltip that requires that you press shift to reveal it.
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel_shift_down"));
        } else {
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel"));
        }

        if(stack.get(ModDataComponentTypes.COORDINATES) != null) {
            tooltip.add(Text.literal("Last Block Changed at " + stack.get(ModDataComponentTypes.COORDINATES)));
        }
        //Adds tooltip corresponding to the stack's COORDINATES value if it has any stored.

        super.appendTooltip(stack, context, tooltip, type);
    }
}
