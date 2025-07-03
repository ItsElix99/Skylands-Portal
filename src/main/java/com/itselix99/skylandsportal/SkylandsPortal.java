package com.itselix99.skylandsportal;

import com.itselix99.skylandsportal.block.AltAirBlock;
import com.itselix99.skylandsportal.block.SkylandsPortalBlock;
import com.itselix99.skylandsportal.event.TextureListener;
import com.itselix99.skylandsportal.item.GoldenStickItem;
import com.itselix99.skylandsportal.item.MagicWandItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static net.minecraft.block.Block.GLASS_SOUND_GROUP;

public class SkylandsPortal {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE = Null.get();

    public static Block SKYLANDS_PORTAL;
    public static Block ALT_AIR;

    public static Item GOLDEN_STICK;
    public static Item MAGIC_WAND;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        SKYLANDS_PORTAL = new SkylandsPortalBlock(NAMESPACE.id("skylands_portal"), TextureListener.skylandsPortal).setSoundGroup(GLASS_SOUND_GROUP).setLuminance(0.75F).setTranslationKey(NAMESPACE, "skylands_portal");
        ALT_AIR = new AltAirBlock(NAMESPACE.id("alt_air"), TextureListener.altAir, Material.AIR).setTranslationKey(NAMESPACE, "alt_air");
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        GOLDEN_STICK = new GoldenStickItem(NAMESPACE.id("golden_stick")).setHandheld().setTranslationKey(NAMESPACE, "golden_stick");
        MAGIC_WAND = new MagicWandItem(NAMESPACE.id("magic_wand")).setTranslationKey(NAMESPACE, "magic_wand");
    }

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(GOLDEN_STICK, 1), Item.STICK, Item.GOLD_INGOT);
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
            CraftingRegistry.addShapedRecipe(new ItemStack(MAGIC_WAND, 1), "odo", "oso", "oso", 's', new ItemStack(GOLDEN_STICK), 'd', new ItemStack(Item.DIAMOND));
        }
    }
}