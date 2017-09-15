package kenijey.harshencastle;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
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
	
	public static void register()
	{
		craftingRegistry();
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.pontus_world_gate_parts, 1, 0), new ItemStack(HarshenItems.pontus_world_gate_parts, 1, 1),
				new ItemStack(HarshenItems.pontus_world_gate_parts, 1 ,2), new ItemStack(HarshenItems.harshen_soul_fragment)), new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 0), true);		
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.soul_harsher_sword, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.blood_infused_ender_eye, 1 ,0), new ItemStack(HarshenItems.blood_essence)), new ItemStack(HarshenItems.empowered_soul_harsher_sword, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Blocks.EMERALD_BLOCK, 1 ,0), new ItemStack(Items.NETHER_STAR)), new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 1), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.itium, 1, 0), new ItemStack(HarshenItems.light_emitted_essence, 1, 0),
				new ItemStack(HarshenItems.ritual_crystal, 1 ,1), new ItemStack(Items.QUARTZ)), new ItemStack(Items.NETHER_STAR, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.harshen_soul_ingot, 1, 0), new ItemStack(HarshenItems.harshen_soul_fragment, 1, 0),
				new ItemStack(HarshenItems.ritual_crystal, 1 ,1), new ItemStack(Items.IRON_SWORD)), new ItemStack(HarshenItems.soul_harsher_sword, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.itium, 1, 0), new ItemStack(Items.MAGMA_CREAM),
				new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.elemental_pendant, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.SPIDER_EYE),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Blocks.STONE)), new ItemStack(HarshenItems.minering), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.iron_bow), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.enion_bow), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenBlocks.block_of_heads), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.EMERALD)), new ItemStack(HarshenItems.looting_earring), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.ENDER_EYE),
				new ItemStack(HarshenItems.powder_of_heretism), new ItemStack(HarshenItems.soul_infused_ingot)), new ItemStack(HarshenItems.ender_pendant), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Blocks.PACKED_ICE),
				new ItemStack(HarshenItems.itium), new ItemStack(HarshenItems.glass_container, 1, 2)), new ItemStack(HarshenItems.water_earring), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.iron_bow), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.soul_infused_ingot), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.soul_ripper_bow), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenBlocks.block_of_heads), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.FIRE_CHARGE), new ItemStack(Blocks.GOLD_BLOCK)), new ItemStack(HarshenItems.fiery_ring), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(Items.BLAZE_ROD), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.BLAZE_ROD), new ItemStack(Items.BLAZE_ROD)), new ItemStack(Items.BLAZE_POWDER, 11), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.iron_scythe), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.soul_infused_ingot), new ItemStack(HarshenItems.valor_badge)), new ItemStack(HarshenItems.raptor_scythe), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.harshen_soul_fragment), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.SLIME_BALL), new ItemStack(Blocks.IRON_BLOCK)), new ItemStack(HarshenItems.soul_shield), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(Blocks.AIR), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.FEATHER), new ItemStack(Items.CHORUS_FRUIT_POPPED)), new ItemStack(HarshenItems.mystic_feather, 2), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(Blocks.AIR), new ItemStack(Blocks.AIR),
				new ItemStack(Blocks.OBSIDIAN), new ItemStack(Blocks.GOLD_BLOCK)), new ItemStack(HarshenBlocks.gilded_obsidian), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenBlocks.gilded_obsidian), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.iron_bow), new ItemStack(HarshenItems.blood_infused_ender_eye)), new ItemStack(HarshenItems.ender_bow), true);
		
		
		
		PedestalSlabRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.NETHERRACK));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.ENDER_EYE), new ItemStack(HarshenItems.blood_infused_ender_eye));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.GOLDEN_APPLE), new ItemStack(HarshenItems.bloody_apple));
		PedestalSlabRecipes.addRecipe(new ItemStack(HarshenItems.pontus_cube), new ItemStack(HarshenItems.bloody_pontus_cube));
		
		
		
		ItemStack[] stackList = new ItemStack[8];
		for(int i = 0; i < 8; i ++)
			stackList[i] = new ItemStack(Blocks.GOLD_BLOCK);
		HereticRitualRecipes.addRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE, 1, 1), EnumGlassContainer.HARSHING_WATER.getType(),
				stackList);
		
		HereticRitualRecipes.addRecipe(new ItemStack(HarshenItems.itium), new ItemStack(HarshenItems.xray_pendant), EnumGlassContainer.HARSHEN_DIMENSIONAL_FLUID.getType(), 
				new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(HarshenItems.powder_of_heretism), new ItemStack(HarshenItems.soul_infused_ingot),
				new ItemStack(Items.EMERALD), new ItemStack(Items.QUARTZ), new ItemStack(Items.ENDER_EYE),
				new ItemStack(Blocks.END_ROD), new ItemStack(Blocks.LAPIS_ORE));
		
		
		
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.ritual_crystal, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1), EnumGlassContainer.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.harshen_soul_ingot, 1, 0), new ItemStack(HarshenItems.soul_infused_ingot, 1, 0), EnumGlassContainer.HARSHING_WATER);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), EnumGlassContainer.HARSHEN_DIMENSIONAL_FLUID);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), EnumGlassContainer.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.OBSIDIAN, 2), EnumGlassContainer.LAVA);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.glass_container), new ItemStack(HarshenItems.glass_container, 1, 3), EnumGlassContainer.MILK);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.glass_container), new ItemStack(HarshenItems.glass_container, 1, 2), EnumGlassContainer.BLOOD);
		
		for(EnumGlassContainer glass : EnumGlassContainer.values())
			if(HarshenUtils.glassContainerHasBlock(glass))
			{
				CauldronRecipes.addRecipe(new ItemStack(((IBlockState) glass.getType().getStateOrLoc()).getBlock()), ItemLiquidTypeset.getStackFromType(glass.getType()), EnumGlassContainer.WATER);
				CauldronRecipes.addRecipe(new ItemStack(HarshenItems.solidifying_paste), new ItemStack(Item.getItemFromBlock(((IBlockState)glass.getType().getStateOrLoc()).getBlock())), glass);
			}
	}
	
	public static void craftingRegistry()
	{		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_soul_ingot"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.harshen_soul_ingot),
				"e e",
				"csc",
				"e e",
				
				'c', new ItemStack(HarshenItems.harshen_crystal),
				's', new ItemStack(HarshenItems.harshen_soul_fragment),
				'e', "gemEmerald");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "pontus_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.pontus_ring),
				" s ",
				"gig",
				" g ",
				
				'i', new ItemStack(HarshenItems.itium),
				's', new ItemStack(HarshenItems.harshen_soul_fragment),
				'g', new ItemStack(HarshenItems.powder_of_heretism));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "bloody_earring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.bloody_earring),
				" i ",
				" s ",
				" b ",
				
				'i', new ItemStack(HarshenItems.itium),
				's', new ItemStack(HarshenItems.harshen_soul_fragment),
				'b', new ItemStack(HarshenItems.blood_essence));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_helmet"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_helmet),
				"iii",
				"i i",
				"   ",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_chestplate"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_chestplate),
				"i i",
				"iii",
				"iii",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_leggings"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_leggings),
				"iii",
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_jaguar_armor_boots"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenArmors.harshen_jaguar_armor_boots),
				"   ",
				"i i",
				"i i",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_dimensional_pedestal"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.harshen_dimensional_pedestal),
				" i ",
				" i ",
				" i ",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_dimensional_dirt"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.harshen_dimensional_dirt, 3),
				" e ",
				"ede",
				" e ",
				
				'e', new ItemStack(HarshenItems.harshen_crystal),
				'd', "dirt");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "ritual_crystal.passive"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ritual_crystal, 1, 0),
				" l ",
				"cec",
				" l ",
				
				'e', new ItemStack(HarshenItems.harshen_soul_fragment),
				'c', new ItemStack(HarshenItems.harshen_crystal),
				'l', new ItemStack(HarshenItems.light_emitted_essence));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "pedestal_slab"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.pedestal_slab),
				"   ",
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "heretic_cauldron"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.heretic_cauldron),
				"i i",
				"i i",
				"iii",
				
				'i', new ItemStack(HarshenItems.harshen_soul_ingot));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "ritual_stick"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ritual_stick, 1, 0),
				" s ",
				" s ",
				
				's', "stickWood");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "heretic_stick"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ritual_stick, 1, 1),
				"psp",
				
				'p', new ItemStack(HarshenItems.powder_of_heretism),
				's', new ItemStack(HarshenItems.ritual_stick, 1, 0));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_collector"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.blood_collector, 1, 0),
				"  i",
				"rbi",
				"nri",
				
				'b', "blockIron",
				'i', "ingotIron",
				'r', new ItemStack(Blocks.IRON_BARS),
				'n', "nuggetIron");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "one_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.one_ring),
				" g ",
				"geg",
				" g ",
				
				'e', new ItemStack(HarshenItems.light_emitted_essence),
				'g', new ItemStack(Items.GOLD_INGOT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "telering"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.telering),
				" i ",
				"geg",
				" g ",
				
				'i', new ItemStack(HarshenItems.itium),
				'e', new ItemStack(HarshenItems.blood_infused_ender_eye),
				'g', new ItemStack(Items.GOLDEN_CARROT));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "glass_container"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.glass_container),
				" g ",
				"g g",
				" g ",
				
				'g', new ItemStack(Blocks.GLASS_PANE));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "zombi_pendant"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.zombi_pendant),
				"g g",
				" z ",
				" e ",
				
				'z', new ItemStack(HarshenItems.zombie_eye),
				'g', new ItemStack(Items.GOLD_INGOT),
				'e', new ItemStack(HarshenItems.light_emitted_essence));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "punchy_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.punchy_ring),
				"nnn",
				" b ",
				
				'n', new ItemStack(Items.IRON_NUGGET),
				'b', new ItemStack(Blocks.IRON_BARS));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "iron_bow"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.iron_bow),
				" il",
				"i l",
				" il",
				
				'i', new ItemStack(Items.IRON_INGOT),
				'l', new ItemStack(Items.LEAD));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_placer"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.blood_placer),
				"qrq",
				"qdq",
				"qqq",
				
				'q', new ItemStack(Items.QUARTZ),
				'd', new ItemStack(Blocks.DISPENSER),
				'r', new ItemStack(Items.COMPARATOR));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_vessel"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.blood_vessel),
				" t ",
				"ggg",
				" t ",
				
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0),
				'g', new ItemStack(Blocks.STAINED_GLASS, 1, 14));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "iron_scythe"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.iron_scythe),
				"iii",
				" s ",
				"s  ",
				
				'i', new ItemStack(Items.IRON_INGOT),
				's', "stickWood");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_factory"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.blood_factory),
				"t t",
				" t ",
				" i ",
				
				'i', new ItemStack(HarshenItems.soul_infused_ingot),
				't', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "elytra"), new ResourceLocation("harshen_items"),
				new ItemStack(Items.ELYTRA),
				"flf",
				"f f",
				"f f",
				
				'f', new ItemStack(HarshenItems.mystic_feather),
				'l', new ItemStack(Items.LEAD));
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "solidifying_paste"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.solidifying_paste, 16),
				" d ",
				"dcd",
				" d ",
				
				'd', "dirt",
				'c', Items.CLAY_BALL);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_magic_table"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.harshen_magic_table),
				"wew",
				" c ",
				"w w",
				
				'e', new ItemStack(HarshenItems.light_emitted_essence),
				'w', new ItemStack(HarshenBlocks.pontus_chaotic_wood),
				'c', new ItemStack(HarshenBlocks.harshen_dimensional_wood_crate));
	}
}
