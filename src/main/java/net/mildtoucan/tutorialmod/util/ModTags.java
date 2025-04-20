package net.mildtoucan.tutorialmod.util;

import net.mildtoucan.tutorialmod.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        }

    }

    public static class Items {

        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        /*This creates a tag, we must now make a JSON file of the same name to include things within it.
        We'll create the JSON file in the data.tutorialmod.tags.item directory since it's an item.
        Of course, if it was a tag for something else, we'd put it in another tutorialmod.tags directory.
        Other mods are able to build compatibility layers by using our own mod's tags! It's a very useful tool for
        cross-compatible mods.*/


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        }
    }



}
