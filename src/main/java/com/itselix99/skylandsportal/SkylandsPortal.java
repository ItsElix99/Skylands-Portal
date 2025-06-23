package com.itselix99.skylandsportal;

import com.itselix99.skylandsportal.blocks.AltAirBlock;
import com.itselix99.skylandsportal.blocks.SkylandsPortalBlock;
import com.itselix99.skylandsportal.events.TextureListener;
import com.itselix99.skylandsportal.items.GoldenStickItem;
import com.itselix99.skylandsportal.items.MagicWandItem;
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
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static net.minecraft.block.Block.GLASS_SOUND_GROUP;

public class SkylandsPortal {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    public static Block SKYLANDS_PORTAL;
    public static Block ALT_AIR;

    public static Item GOLDEN_STICK;
    public static Item MAGIC_WAND;

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        SKYLANDS_PORTAL = new SkylandsPortalBlock(Identifier.of(MOD_ID, "skylands_portal"), TextureListener.skylandsPortal).setHardness(-1.0F).setResistance(6000000.0F).setSoundGroup(GLASS_SOUND_GROUP).setLuminance(0.75F).setTranslationKey(MOD_ID, "skylands_portal");
        ALT_AIR = new AltAirBlock(Identifier.of(MOD_ID, "alt_air"), TextureListener.altAir, Material.AIR).setTranslationKey(MOD_ID, "alt_air");
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        GOLDEN_STICK = new GoldenStickItem(Identifier.of(MOD_ID, "golden_stick")).setHandheld().setTranslationKey(MOD_ID, "golden_stick");
        MAGIC_WAND = new MagicWandItem(Identifier.of(MOD_ID, "magic_wand")).setTranslationKey(MOD_ID, "magic_wand");
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