package kenijey.harshencastle.internal;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.BlockItem;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenPlugin;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenPlugin;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.api.IHarshenRegistry;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.handlers.vanillaInventory.HandlerTotemOfUndying;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

/**
 * The Inner plugin for HarshenCaste. 
 * This registers all the different recipes and all the inventory items.
 * @author Wyn Price
 *
 */
@HarshenPlugin
public class HarshenInternalPlugin implements IHarshenPlugin {

	@Override
	public void register(IHarshenRegistry registry) 
	{
		//magic table recipes
		registry.registerMagicTableRecipe(HarshenUtils.getMixupBook(), EnumGlassContainer.DIAMOND.getHarshenStack(), EnumGlassContainer.LAVA.getHarshenStack(), new HarshenStack(new ItemStack(Items.BOOK), new ItemStack(HarshenBlocks.ARCHIVE)), EnumGlassContainer.MAGIC.getHarshenStack());
		registry.registerMagicTableRecipe(new ItemStack(HarshenBlocks.JEWEL_DIRT), new HarshenStack(new ItemStack(Blocks.DIRT)), new HarshenStack(new ItemStack(Blocks.DIRT)), new HarshenStack(new ItemStack(Blocks.DIRT)), new HarshenStack(new ItemStack(Blocks.DIRT)));
		registry.registerMagicTableRecipe(new ItemStack(HarshenItems.REACH_PENDANT), new HarshenStack(new ItemStack(Items.ROTTEN_FLESH)), EnumGlassContainer.COAL.getHarshenStack(), new HarshenStack(new ItemStack(HarshenItems.ZOMBIE_EYE)), new HarshenStack(new ItemStack(Items.STONE_SWORD)));
		
		//pedestal slab recipes
		registry.registerPedestalSlabRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.NETHERRACK));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Items.ENDER_EYE)), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.BLOODY_APPLE));
		registry.registerPedestalSlabRecipe(new HarshenStack(new ItemStack(HarshenItems.PONTUS_CUBE)), new ItemStack(HarshenItems.BLOODY_PONTUS_CUBE));
		
		//cauldron recipes
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL)), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), EnumGlassContainer.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT)), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), EnumGlassContainer.HARSHING_WATER.getType());
		registry.registerCauldronRecipe(new HarshenStack("sand"), new ItemStack(Blocks.SOUL_SAND), EnumGlassContainer.HARSHEN_DIMENSIONAL_FLUID.getType());
		registry.registerCauldronRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.NETHERRACK), EnumGlassContainer.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack("cobblestone"), new ItemStack(Blocks.OBSIDIAN, 2), EnumGlassContainer.LAVA.getType());
		registry.registerCauldronRecipe(EnumGlassContainer.EMPTY.getHarshenStack(), EnumGlassContainer.CURE.getStack(), EnumGlassContainer.MILK.getType());
		registry.registerCauldronRecipe(EnumGlassContainer.EMPTY.getHarshenStack(), EnumGlassContainer.REGEN.getStack(), EnumGlassContainer.BLOOD.getType());
		registry.registerCauldronRecipe(new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)), new ItemStack(HarshenItems.RING_OF_BLOOD), EnumGlassContainer.BLOOD.getType());
		
		//heretic ritual recipes
		HarshenStack blockGemStack = new HarshenStack("blockGold", "blockDiamond", "blockEmerald");
		registry.registerHereticRecipe(new HarshenStack(new ItemStack(Items.APPLE)), new ItemStack(Items.GOLDEN_APPLE, 1, 1), EnumGlassContainer.HARSHING_WATER.getType(),
				blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone(), blockGemStack.clone());
		registry.registerHereticRecipe(new HarshenStack(new ItemStack(HarshenItems.ITIUM)), new ItemStack(HarshenItems.XRAY_PENDANT), EnumGlassContainer.EARTH.getType(), 
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)),
				new HarshenStack("gemEmerald"), new HarshenStack("gemQuartz"), new HarshenStack(new ItemStack(Items.ENDER_EYE)),
				new HarshenStack(new ItemStack(Blocks.END_ROD)), new HarshenStack("oreLapis"));
		registry.registerHereticRecipe(EnumGlassContainer.EMPTY.getHarshenStack(), EnumGlassContainer.MAGIC.getStack(), EnumGlassContainer.HARSHING_WATER.getType(), 
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack("dyePurple"), new HarshenStack("dyePink"),
				new HarshenStack(new ItemStack(Items.END_CRYSTAL)), new HarshenStack("enderpearl"), new HarshenStack(new ItemStack(Items.ENDER_EYE)),
				new HarshenStack(new ItemStack(Items.BLAZE_POWDER)), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)));
		registry.registerHereticRecipe(new HarshenStack("netherStar"), new ItemStack(HarshenItems.SOUL_BINDING_PENDANT), EnumGlassContainer.MAGIC.getType(), 
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack("dyeRed"), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)),
				new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)), new HarshenStack(new ItemStack(Items.FERMENTED_SPIDER_EYE)), new HarshenStack(new ItemStack(Items.CHORUS_FRUIT)),
				new HarshenStack(new ItemStack(Items.BLAZE_ROD)), new HarshenStack(new ItemStack(Items.GOLDEN_APPLE, 1, 1)));
		
		//ritual recipes
		registry.registerRitualRecipe(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER), true, new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS)), new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1 ,2)), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.EMPOWERED_SOUL_HARSHER_SWORD), true, new HarshenStack(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE, 1 ,0)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_ESSENCE)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 1), true, new HarshenStack(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("blockEmerald"), new HarshenStack("netherStar"));
		registry.registerRitualRecipe(new ItemStack(Items.NETHER_STAR), true, new HarshenStack(new ItemStack(HarshenItems.ITIUM)), new HarshenStack(new ItemStack(HarshenItems.LIGHT_EMITTED_SEED)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 , 1)), new HarshenStack("gemQuartz"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), true, new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0)), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT, 1, 0)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1)), new HarshenStack(new ItemStack(Items.IRON_SWORD)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.ELEMENTAL_PENDANT, 1, 0), true, new HarshenStack(new ItemStack(HarshenItems.ITIUM, 1, 0)), new HarshenStack(new ItemStack(Items.MAGMA_CREAM)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Items.GOLDEN_APPLE)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.MINERING), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)),
				new HarshenStack(HarshenUtils.getLapis()), new HarshenStack("stone"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.ENION_BOW), true, new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("blockRedstone"), new HarshenStack("blockPrismarineDark"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.LOOTING_EARRING), true, new HarshenStack(new ItemStack(HarshenBlocks.BLOCK_OF_HEADS)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(HarshenUtils.getLapis()), new HarshenStack("gemEmerald"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.ENDER_PENDANT), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Items.ENDER_EYE)),
				new HarshenStack(new ItemStack(HarshenItems.POWDER_OF_HERETISM)), new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.WATER_EARRING), true, new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack(new ItemStack(Blocks.PACKED_ICE)),
				new HarshenStack(new ItemStack(HarshenItems.ITIUM)), EnumGlassContainer.WATER.getHarshenStack());
		registry.registerRitualRecipe(new ItemStack(HarshenItems.SOUL_RIPPER_BOW), true, new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new HarshenStack("blockPrismarineDark"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.FIERY_RING), true, new HarshenStack(new ItemStack(Items.FLINT_AND_STEEL)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(Items.FIRE_CHARGE)), new HarshenStack(new ItemStack(HarshenItems.EMPTY_RING)));
		registry.registerRitualRecipe(new ItemStack(Items.BLAZE_POWDER, 12), true, new HarshenStack(new ItemStack(Items.BLAZE_ROD)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(Items.BLAZE_ROD)), new HarshenStack(new ItemStack(Items.BLAZE_ROD)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.RAPTOR_SCYTHE), true, new HarshenStack(new ItemStack(HarshenItems.IRON_SCYTHE)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new HarshenStack(new ItemStack(HarshenItems.VALOR_BADGE)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.SOUL_SHIELD), true, new HarshenStack(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("slimeball"), new HarshenStack("blockIron"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.MYSTIC_FEATHER, 2), true, new HarshenStack(), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack("feather"), new HarshenStack(new ItemStack(Items.CHORUS_FRUIT_POPPED)));
		registry.registerRitualRecipe(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), true, new HarshenStack(), new HarshenStack(),
				new HarshenStack("obsidian"), new HarshenStack("blockGold"));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.ENDER_BOW), true, new HarshenStack(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN)), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.IRON_BOW)), new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)));
		registry.registerRitualRecipe(new ItemStack(Items.DIAMOND), true, new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)),
				new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)), new HarshenStack(new ItemStack(HarshenItems.DIAMOND_SHARD)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.COMBAT_PENDANT), true, new HarshenStack(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)), new HarshenStack(new ItemStack(HarshenItems.VALOR_BADGE)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)), new HarshenStack("ingotIron"));
		ArrayList<ItemStack> foods = new ArrayList<>();//used to get all the registered foods
		for(Item item : ForgeRegistries.ITEMS.getValues())
			if(item instanceof ItemFood)
				foods.add(new ItemStack(item, 1, OreDictionary.WILDCARD_VALUE));
		ItemStack[] foodStacks = new ItemStack[foods.size()];
		foods.toArray(foodStacks);
		registry.registerRitualRecipe(new ItemStack(HarshenItems.FEEDING_EARRING), true, new HarshenStack(foodStacks),new HarshenStack(foodStacks),
				new HarshenStack(foodStacks), new HarshenStack(new ItemStack(HarshenItems.HARSHEN_CRYSTAL)));
		registry.registerRitualRecipe(new ItemStack(HarshenItems.LIGHTNING_STAFF), true, new HarshenStack("blockPrismarineDark"), new HarshenStack(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1)),
				new HarshenStack(new ItemStack(HarshenItems.RITUAL_STICK, 1, 1)), new HarshenStack(new ItemStack(HarshenItems.VALOR_BADGE)));
		
		//cauldron recipes
		registry.registerCauldronLiquid(EnumGlassContainer.LAVA.getStack(), EnumGlassContainer.EMPTY.getStack(), EnumGlassContainer.LAVA.getType(), 1);
		registry.registerCauldronLiquid(EnumGlassContainer.WATER.getStack(), EnumGlassContainer.EMPTY.getStack(), EnumGlassContainer.WATER.getType(), 1);
		registry.registerCauldronLiquid(EnumGlassContainer.MILK.getStack(), EnumGlassContainer.EMPTY.getStack(), EnumGlassContainer.MILK.getType(), 1);
		for(EnumGlassContainer container : EnumGlassContainer.values())
			if(container.isSubContainer())
			{
				registry.registerCauldronLiquid(container.getStack(), EnumGlassContainer.EMPTY.getStack().copy(), container.getType(), 1);
				if(HarshenUtils.glassContainerHasBlock(container))
					registry.registerCauldronLiquid(new ItemStack(HarshenItems.ITEM_LIQUID, 1, ItemLiquidTypeset.getMetaFromType(container.getType())),
							ItemStack.EMPTY, container.getType(), 3);
			}
		
		//inventory items
    	registry.registerInventoryItem(new BlockItem(Items.TOTEM_OF_UNDYING), EnumInventorySlots.NECK, new HandlerTotemOfUndying(), false, 0);
    	for(Item item : ForgeRegistries.ITEMS.getValues())
    		if(item instanceof IHarshenProvider)
    			registry.registerInventoryItem(new BlockItem(item), (IHarshenProvider)item);
    	for(Block block : ForgeRegistries.BLOCKS.getValues())
    		if(block instanceof IHarshenProvider)
    			registry.registerInventoryItem(new BlockItem(block), (IHarshenProvider)block);

	}

	@Override
	public String getModID() {
		return HarshenCastle.MODID;
	}
	

}
