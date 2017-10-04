package kenijey.harshencastle;

import java.util.ArrayList;

import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HarshenRecipes {
	
	public static ArrayList<RitualRecipes> allRitualRecipes = new ArrayList<>();
	public static ArrayList<CauldronRecipes> allCauldronRecipes = new ArrayList<>();
	public static ArrayList<HereticRitualRecipes> allHereticCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	public static ArrayList<MagicTableRecipe> allMagicTableRecipes = new ArrayList<>();
	
	public static void register()
	{
		craftingRegistry();
		
		MagicTableRecipe.addRecipe(array(new ItemStack(Items.COAL), new ItemStack(Blocks.LOG), new ItemStack(Items.DIAMOND), new ItemStack(Blocks.STONE)), new ItemStack(Blocks.DIAMOND_BLOCK));
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 0), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1, 1),
				new ItemStack(HarshenItems.PONTUS_WORLD_GATE_PARTS, 1 ,2), new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT)), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 0), true);		
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE, 1 ,0), new ItemStack(HarshenItems.BLOOD_ESSENCE)), new ItemStack(HarshenItems.EMPOWERED_SOUL_HARSHER_SWORD, 1, 0), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Blocks.EMERALD_BLOCK, 1 ,0), new ItemStack(Items.NETHER_STAR)), new ItemStack(HarshenItems.PONTUS_WORLD_GATE_SPAWNER, 1, 1), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.ITIUM, 1, 0), new ItemStack(HarshenItems.LIGHT_EMITTED_SEED, 1, 0),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1), new ItemStack(Items.QUARTZ)), new ItemStack(Items.NETHER_STAR, 1, 0), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0), new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT, 1, 0),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1 ,1), new ItemStack(Items.IRON_SWORD)), new ItemStack(HarshenItems.SOUL_HARSHER_SWORD, 1, 0), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.ITIUM, 1, 0), new ItemStack(Items.MAGMA_CREAM),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.ELEMENTAL_PENDANT, 1, 0), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.SPIDER_EYE),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Blocks.STONE)), new ItemStack(HarshenItems.MINERING), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.ENION_BOW), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenBlocks.BLOCK_OF_HEADS), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.EMERALD)), new ItemStack(HarshenItems.LOOTING_EARRING), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.ENDER_EYE),
				new ItemStack(HarshenItems.POWDER_OF_HERETISM), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT)), new ItemStack(HarshenItems.ENDER_PENDANT), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Blocks.PACKED_ICE),
				new ItemStack(HarshenItems.ITIUM), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 2)), new ItemStack(HarshenItems.WATER_EARRING), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.SOUL_RIPPER_BOW), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenBlocks.BLOCK_OF_HEADS), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Items.FIRE_CHARGE), new ItemStack(Blocks.GOLD_BLOCK)), new ItemStack(HarshenItems.FIERY_RING), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(Items.BLAZE_ROD), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.BLAZE_ROD)), new ItemStack(Items.BLAZE_POWDER, 11), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.IRON_SCYTHE), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(HarshenItems.SOUL_INFUSED_INGOT), new ItemStack(HarshenItems.VALOR_BADGE)), new ItemStack(HarshenItems.RAPTOR_SCYTHE), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.IRON_BLOCK)), new ItemStack(HarshenItems.SOUL_SHIELD), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(Blocks.AIR), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(Items.FEATHER), new ItemStack(Items.CHORUS_FRUIT_POPPED)), new ItemStack(HarshenItems.MYSTIC_FEATHER, 2), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR),
				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.GOLD_BLOCK)), new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenBlocks.GILDED_OBSIDIAN), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1),
				new ItemStack(HarshenItems.IRON_BOW), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE)), new ItemStack(HarshenItems.ENDER_BOW), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.DIAMOND_SHARD), new ItemStack(HarshenItems.DIAMOND_SHARD),
				new ItemStack(HarshenItems.DIAMOND_SHARD), new ItemStack(HarshenItems.DIAMOND_SHARD)), new ItemStack(Items.DIAMOND), true);
		
		RitualRecipes.addRecipe(array(new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE), new ItemStack(HarshenItems.VALOR_BADGE),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(Items.IRON_INGOT)), new ItemStack(HarshenItems.COMBAT_PENDANT), true);
		
		
		
		PedestalSlabRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.NETHERRACK));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.ENDER_EYE), new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.GOLDEN_APPLE), new ItemStack(HarshenItems.BLOODY_APPLE));
		PedestalSlabRecipes.addRecipe(new ItemStack(HarshenItems.PONTUS_CUBE), new ItemStack(HarshenItems.BLOODY_PONTUS_CUBE));
		
		
		
		ItemStack[] stackList = new ItemStack[8];
		for(int i = 0; i < 8; i ++)
			stackList[i] = new ItemStack(Blocks.GOLD_BLOCK);
		HereticRitualRecipes.addRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE, 1, 1), EnumGlassContainer.HARSHING_WATER.getType(),
				stackList);
		
		HereticRitualRecipes.addRecipe(new ItemStack(HarshenItems.ITIUM), new ItemStack(HarshenItems.XRAY_PENDANT), EnumGlassContainer.EARTH.getType(), 
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), new ItemStack(HarshenItems.POWDER_OF_HERETISM), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT),
				new ItemStack(Items.EMERALD), new ItemStack(Items.QUARTZ), new ItemStack(Items.ENDER_EYE),
				new ItemStack(Blocks.END_ROD), new ItemStack(Blocks.LAPIS_ORE));
		
		
		
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 0), new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 1), EnumGlassContainer.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT, 1, 0), new ItemStack(HarshenItems.SOUL_INFUSED_INGOT, 1, 0), EnumGlassContainer.HARSHING_WATER);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), EnumGlassContainer.HARSHEN_DIMENSIONAL_FLUID);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), EnumGlassContainer.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.OBSIDIAN, 2), EnumGlassContainer.LAVA);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.GLASS_CONTAINER), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 3), EnumGlassContainer.MILK);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.GLASS_CONTAINER), new ItemStack(HarshenItems.GLASS_CONTAINER, 1, 2), EnumGlassContainer.BLOOD);
		
		for(EnumGlassContainer glass : EnumGlassContainer.values())
			if(HarshenUtils.glassContainerHasBlock(glass))
			{
				CauldronRecipes.addRecipe(new ItemStack(((IBlockState) glass.getType().getStateOrLoc()).getBlock()), ItemLiquidTypeset.getStackFromType(glass.getType()), EnumGlassContainer.WATER);
				CauldronRecipes.addRecipe(new ItemStack(HarshenItems.SOLIDIFYING_PASTE), new ItemStack(Item.getItemFromBlock(((IBlockState)glass.getType().getStateOrLoc()).getBlock())), glass);
			}
	}
	
	public static void craftingRegistry()
	{		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_soul_ingot"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT),
				"e e",
				"csc",
				"e e",
				
				'c', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'e', "gemEmerald");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "pontus_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.PONTUS_RING),
				" s ",
				"gig",
				" g ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'g', new ItemStack(HarshenItems.POWDER_OF_HERETISM));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "bloody_earring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.BLOODY_EARRING),
				" i ",
				" s ",
				" b ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'b', new ItemStack(HarshenItems.BLOOD_ESSENCE));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_helmet"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_helmet),
				"iii",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_chestplate"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_chestplate),
				"i i",
				"iii",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_leggings"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_leggings),
				"iii",
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_boots"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_boots),
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_dimensional_pedestal"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL),
				" i ",
				" i ",
				" i ",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_dimensional_dirt"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT, 3),
				" e ",
				"ede",
				" e ",
				
				'e', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				'd', "dirt");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "ritual_crystal.passive"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.RITUAL_CRYSTAL, 1, 0),
				" l ",
				"cec",
				" l ",
				
				'e', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'c', new ItemStack(HarshenItems.HARSHEN_CRYSTAL),
				'l', new ItemStack(HarshenItems.LIGHT_EMITTED_SEED));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "pedestal_slab"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.PEDESTAL_SLAB),
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "heretic_cauldron"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.HERETIC_CAULDRON),
				"i i",
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.HARSHEN_SOUL_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "ritual_stick"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.RITUAL_STICK, 1, 0),
				" s ",
				" s ",
				
				's', "stickWood");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "heretic_stick"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.RITUAL_STICK, 1, 1),
				"psp",
				
				'p', new ItemStack(HarshenItems.POWDER_OF_HERETISM),
				's', new ItemStack(HarshenItems.RITUAL_STICK, 1, 0));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_collector"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.BLOOD_COLLECTOR, 1, 0),
				"  i",
				"rbi",
				"nri",
				
				'b', "blockIron",
				'i', "ingotIron",
				'r', new ItemStack(Blocks.IRON_BARS),
				'n', "nuggetIron");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "one_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ONE_RING),
				" g ",
				"geg",
				" g ",
				
				'e', new ItemStack(HarshenItems.LIGHT_EMITTED_SEED),
				'g', new ItemStack(Items.GOLD_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "telering"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.TELERING),
				" i ",
				"geg",
				" g ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				'e', new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE),
				'g', new ItemStack(Items.GOLDEN_CARROT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "glass_container"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.GLASS_CONTAINER),
				" g ",
				"g g",
				" g ",
				
				'g', new ItemStack(Blocks.GLASS_PANE));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "zombi_pendant"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ZOMBI_PENDANT),
				"g g",
				" z ",
				" e ",
				
				'z', new ItemStack(HarshenItems.ZOMBIE_EYE),
				'g', new ItemStack(Items.GOLD_INGOT),
				'e', new ItemStack(HarshenItems.LIGHT_EMITTED_SEED));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "punchy_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.PUNCHY_RING),
				"nnn",
				" b ",
				
				'n', new ItemStack(Items.IRON_NUGGET),
				'b', new ItemStack(Blocks.IRON_BARS));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "iron_bow"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.IRON_BOW),
				" il",
				"i l",
				" il",
				
				'i', new ItemStack(Items.IRON_INGOT),
				'l', new ItemStack(Items.LEAD));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_placer"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.BLOOD_PLACER),
				"qrq",
				"qdq",
				"qqq",
				
				'q', new ItemStack(Items.QUARTZ),
				'd', new ItemStack(Blocks.DISPENSER),
				'r', new ItemStack(Items.COMPARATOR));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_vessel"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.BLOOD_VESSEL),
				" t ",
				"ggg",
				" t ",
				
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0),
				'g', new ItemStack(Blocks.STAINED_GLASS, 1, 14));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "iron_scythe"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.IRON_SCYTHE),
				"iii",
				" s ",
				"s  ",
				
				'i', new ItemStack(Items.IRON_INGOT),
				's', "stickWood");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_factory"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.BLOOD_FACTORY),
				"t t",
				" t ",
				" i ",
				
				'i', new ItemStack(HarshenItems.SOUL_INFUSED_INGOT),
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "elytra"), new ResourceLocation("harshen_items"),
				new ItemStack(Items.ELYTRA),
				"flf",
				"f f",
				"f f",
				
				'f', new ItemStack(HarshenItems.MYSTIC_FEATHER),
				'l', new ItemStack(Items.LEAD));
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "solidifying_paste"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.SOLIDIFYING_PASTE, 16),
				" d ",
				"dcd",
				" d ",
				
				'd', "dirt",
				'c', Items.CLAY_BALL);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_magic_table"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.HARSHEN_MAGIC_TABLE),
				"wew",
				" c ",
				"w w",
				
				'e', new ItemStack(HarshenItems.LIGHT_EMITTED_SEED),
				'w', new ItemStack(HarshenBlocks.PONTUS_CHAOTIC_WOOD),
				'c', new ItemStack(HarshenBlocks.HARSHEN_DIMENSIONAL_WOOD_CRATE));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "elytra_pendant"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ELYTRA_PENDANT),
				"fif",
				
				'f', new ItemStack(HarshenItems.MYSTIC_FEATHER),
				'i', new ItemStack(HarshenItems.ITIUM));
	}
	
	public static <T> ArrayList<T> array(T... list)
	{
		return HarshenUtils.toArray(list);
	}
	
}
