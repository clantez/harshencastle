package kenijey.harshencastle.internal;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenPlugin;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenPlugin;
import kenijey.harshencastle.api.IHarshenRegistry;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.objecthandlers.HarshenGlassContainerIngredient;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class HarshenAPIHandler
{
	
	public final static ArrayList<IHarshenPlugin> ALL_PLUGINS = new ArrayList<>();

	
	public static ArrayList<RitualRecipes> allRitualRecipes = new ArrayList<>();
	public static ArrayList<CauldronRecipes> allCauldronRecipes = new ArrayList<>();
	public static ArrayList<HereticRitualRecipes> allHereticCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	public static ArrayList<MagicTableRecipe> allMagicTableRecipes = new ArrayList<>();

	public static boolean hasInit;
		
	public static void loadPlugins(ASMDataTable asmData) {
		ALL_PLUGINS.addAll(HarshenUtils.getInstancesOfAnnotation(asmData, HarshenPlugin.class, IHarshenPlugin.class));
	}
	
	
	public static void register()
	{
		allRitualRecipes.clear();
		allCauldronRecipes.clear();
		allHereticCauldronRecipes.clear();
		allPedestalRecipes.clear();
		allMagicTableRecipes.clear();
					
		for(IHarshenPlugin plugin : ALL_PLUGINS)
			plugin.register(new HarshenRegistry());
		
// DONE		MagicTableRecipe.addRecipe(array(new HarshenStack(EnumGlassContainer.DIAMOND.getStack()), new HarshenStack(EnumGlassContainer.LAVA.getStack()), new HarshenStack(new ItemStack(Items.BOOK), new ItemStack(HarshenBlocks.ARCHIVE)), new HarshenStack(EnumGlassContainer.MAGIC.getStack())), HarshenUtils.getMixupBook());
//		MagicTableRecipe.addRecipe(hArray(new ItemStack(Blocks.DIRT), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.DIRT), new ItemStack(Blocks.DIRT)), new ItemStack(HarshenBlocks.JEWEL_DIRT));
//		MagicTableRecipe.addRecipe(hArray(new ItemStack(Items.ROTTEN_FLESH), EnumGlassContainer.COAL.getStack(), new ItemStack(HarshenItems.ZOMBIE_EYE), new ItemStack(Items.STONE_SWORD)), new ItemStack(HarshenItems.REACH_PENDANT));
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 0), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 1),
//				new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1 ,2), new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 0), true);		
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE, 1 ,0), new ItemStack(HarshenItems.BLOOD_ESSENCE)), new ItemStack(HarshenItems.EMPOWERED_SOUL_HARSHER_SWORD, 1, 0), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Blocks.EMERALD_BLOCK, 1 ,0), new ItemStack(Items.NETHER_STAR)), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 1), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.ITIUM, 1, 0), new ItemStack(HarshenItems.LIGHT_EMITTED_SEED, 1, 0),
//				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1), new ItemStack(Items.QUARTZ)), new ItemStack(Items.NETHER_STAR, 1, 0), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0), new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT, 1, 0),
//				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1), new ItemStack(Items.IRON_SWORD)), new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.ITIUM, 1, 0), new ItemStack(Items.MAGMA_CREAM),
//				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.ELEMENTAL_PENDANT, 1, 0), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(HarshenItems.EMPTY_RING),
//				HarshenUtils.getLapis(), new ItemStack(Blocks.STONE)), new ItemStack(HarshenItems.MINERING), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.ENION_BOW), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenBlocks.BLOCK_OF_HEADS), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				HarshenUtils.getLapis(), new ItemStack(Items.EMERALD)), new ItemStack(HarshenItems.LOOTING_EARRING), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.ENDER_EYE),
//				new ItemStack(HarshenItems.POWDER_OF_HERETISM), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new ItemStack(HarshenItems.ENDER_PENDANT), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Blocks.PACKED_ICE),
//				new ItemStack(HarshenItems.ITIUM), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 2)), new ItemStack(HarshenItems.WATER_EARRING), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.SOUL_RIPPER_BOW), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Items.FLINT_AND_STEEL), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Items.FIRE_CHARGE), new ItemStack(HarshenItems.EMPTY_RING)), new ItemStack(HarshenItems.FIERY_RING), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Items.BLAZE_ROD), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.BLAZE_ROD)), new ItemStack(Items.BLAZE_POWDER, 11), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.IRON_SCYTHE), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), new ItemStack(HarshenItems.VALOR_BADGE)), new ItemStack(HarshenItems.RAPTOR_SCYTHE), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.IRON_BLOCK)), new ItemStack(HarshenItems.SOUL_SHIELD), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Blocks.AIR), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(Items.FEATHER), new ItemStack(Items.CHORUS_FRUIT_POPPED)), new ItemStack(HarshenItems.MYSTIC_FEATHER, 2), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR),
//				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.GOLD_BLOCK)), new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)), new ItemStack(HarshenItems.ENDER_BOW), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.DIAMOND_SHARD), new ItemStack(HarshenItems.DIAMOND_SHARD),
//				new ItemStack(HarshenItems.DIAMOND_SHARD), new ItemStack(HarshenItems.DIAMOND_SHARD)), new ItemStack(Items.DIAMOND), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE), new ItemStack(HarshenItems.VALOR_BADGE),
//				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.IRON_INGOT)), new ItemStack(HarshenItems.COMBAT_PENDANT), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Items.BREAD), new ItemStack(Items.BAKED_POTATO),
//				new ItemStack(Items.APPLE), new ItemStack(HarshenItems.HARSHEN_CRYSTAL)), new ItemStack(HarshenItems.FEEDING_EARRING), true);
//		
//		RitualRecipes.addRecipe(hArray(new ItemStack(Items.DRAGON_BREATH), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
//				new ItemStack(HarshenBlocks.JEWEL_DIRT, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)),
//				new ItemStack(HarshenItems.LIGHTNING_STAFF), true);
//		
//		
//		PedestalSlabRecipes.addRecipe(new HarshenStack(Blocks.COBBLESTONE), new ItemStack(Blocks.NETHERRACK));
//		PedestalSlabRecipes.addRecipe(new HarshenStack(Items.ENDER_EYE), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE));
//		PedestalSlabRecipes.addRecipe(new HarshenStack(Items.GOLDEN_APPLE), new ItemStack(HarshenItems.BLOODY_APPLE));
//		PedestalSlabRecipes.addRecipe(new HarshenStack(HarshenItems.PONTUS_CUBE), new ItemStack(HarshenItems.BLOODY_PONTUS_CUBE));
//		
//		
//		
//		HarshenStack[] stackList = new HarshenStack[8];
//		for(int i = 0; i < 8; i ++)
//			stackList[i] = new HarshenStack(new ItemStack(Blocks.GOLD_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), new ItemStack(Blocks.EMERALD_BLOCK));
//		HereticRitualRecipes.addRecipe(new HarshenStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE, 1, 1), EnumGlassContainer.HARSHING_WATER.getType(),
//				stackList);
//		
//		HereticRitualRecipes.addRecipe(new HarshenStack(HarshenItems.ITIUM), new ItemStack(HarshenItems.XRAY_PENDANT), EnumGlassContainer.EARTH.getType(), 
//				new HarshenStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new HarshenStack(HarshenItems.POWDER_OF_HERETISM), new HarshenStack(HarshenItems.SOUL_INFUSED_INGOT),
//				new HarshenStack(Items.EMERALD), new HarshenStack(Items.QUARTZ), new HarshenStack(Items.ENDER_EYE),
//				new HarshenStack(Blocks.END_ROD), new HarshenStack(Blocks.LAPIS_ORE));
//		
//		HereticRitualRecipes.addRecipe(new HarshenStack(EnumGlassContainer.EMPTY.getStack()), EnumGlassContainer.MAGIC.getStack(), EnumGlassContainer.HARSHING_WATER.getType(), 
//				new HarshenStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new HarshenStack(HarshenUtils.getDye(EnumDyeColor.PURPLE)), new HarshenStack(HarshenUtils.getDye(EnumDyeColor.PINK)),
//				new HarshenStack(Items.END_CRYSTAL), new HarshenStack(Items.ENDER_PEARL), new HarshenStack(Items.ENDER_EYE),
//				new HarshenStack(Items.BLAZE_POWDER), new HarshenStack(HarshenItems.HARSHEN_SOUL_FRAGMENT));
//		
//		HereticRitualRecipes.addRecipe(new HarshenStack(Items.NETHER_STAR), new ItemStack(HarshenItems.SOUL_BINDING_PENDANT), EnumGlassContainer.MAGIC.getType(), 
//				new HarshenStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new HarshenStack(HarshenUtils.getDye(EnumDyeColor.RED)), new HarshenStack(HarshenItems.SOUL_INFUSED_INGOT),
//				new HarshenStack(HarshenItems.HARSHEN_SOUL_FRAGMENT), new HarshenStack(Items.FERMENTED_SPIDER_EYE), new HarshenStack(Items.CHORUS_FRUIT),
//				new HarshenStack(Items.BLAZE_ROD), new HarshenStack(Items.GOLDEN_APPLE, 1, 1));
//		
//		CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.RITUAL_CRYSTAL, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), EnumGlassContainer.BLOOD.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT, 1, 0), EnumGlassContainer.HARSHING_WATER.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), EnumGlassContainer.HARSHEN_DIMENSIONAL_FLUID.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), EnumGlassContainer.BLOOD.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(Blocks.COBBLESTONE), new ItemStack(Blocks.OBSIDIAN, 2), EnumGlassContainer.LAVA.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.GLASS_CONTAINER), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 3), EnumGlassContainer.MILK.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.GLASS_CONTAINER), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 2), EnumGlassContainer.BLOOD.getType());
//		CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.EMPTY_RING), new ItemStack(HarshenItems.RING_OF_BLOOD), EnumGlassContainer.BLOOD.getType());
//		
//		for(EnumGlassContainer glass : EnumGlassContainer.values())
//			if(HarshenUtils.glassContainerHasState(glass))
//			{
//				Block block = ((IBlockState) glass.getType().getStateOrLoc()).getBlock();
//				if(HarshenUtils.glassContainerHasBlock(glass))
//				{
//					CauldronRecipes.addRecipe(new HarshenStack(block), ItemLiquidTypeset.getStackFromType(glass.getType()), EnumGlassContainer.WATER.getType());
//					CauldronRecipes.addRecipe(new HarshenStack(HarshenItems.SOLIDIFYING_PASTE), new ItemStack(Item.getItemFromBlock(block)), glass.getType());
//				}
//				if(!hasInit)
//				{
//					HarshenGlassContainerIngredient[] ingridientList = new HarshenGlassContainerIngredient[9];
//					ingridientList[0] = new HarshenGlassContainerIngredient(EnumGlassContainer.EMPTY.getStack()); 
//					for(int i = 1; i < 9; i++)
//						ingridientList[i] = new HarshenGlassContainerIngredient(HarshenUtils.toList(HarshenUtils.getAllRelatives(HarshenUtils.phaseBucket(block))));
//					GameRegistry.addShapelessRecipe(new ResourceLocation(HarshenCastle.MODID, glass.getName() + "_container"), new ResourceLocation("harshen_items"), glass.getStack(), ingridientList);
//				}
//					
//			}
		
		hasInit = true;
	}
}
