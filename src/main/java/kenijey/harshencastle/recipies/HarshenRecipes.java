package kenijey.harshencastle.recipies;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
				new ItemStack(HarshenItems.ritual_crystal, 1 ,1), new ItemStack(Items.DIAMOND_SWORD)), new ItemStack(HarshenItems.soul_harsher_sword, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.itium, 1, 0), new ItemStack(Items.MAGMA_CREAM),
				new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.GOLDEN_APPLE)), new ItemStack(HarshenItems.elemental_pendant, 1, 0), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.SPIDER_EYE),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Blocks.STONE)), new ItemStack(HarshenItems.minering), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.iron_bow), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.PRISMARINE, 1, 2)), new ItemStack(HarshenItems.enion_bow), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.itium), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.EMERALD)), new ItemStack(HarshenItems.looting_earring), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Items.ENDER_EYE),
				new ItemStack(HarshenItems.powder_of_heretism), new ItemStack(HarshenItems.soul_infused_ingot)), new ItemStack(HarshenItems.ender_pendant), true);
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(Blocks.PACKED_ICE),
				new ItemStack(HarshenItems.itium), new ItemStack(HarshenItems.glass_container, 1, 2)), new ItemStack(HarshenItems.water_earring), true);
		
		
		
		PedestalSlabRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.NETHERRACK));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.ENDER_EYE), new ItemStack(HarshenItems.blood_infused_ender_eye));
		PedestalSlabRecipes.addRecipe(new ItemStack(Items.GOLDEN_APPLE), new ItemStack(HarshenItems.bloody_apple));
		PedestalSlabRecipes.addRecipe(new ItemStack(HarshenItems.pontus_cube), new ItemStack(HarshenItems.bloody_pontus_cube));
		
		
		
		ItemStack[] stackList = new ItemStack[8];
		for(int i = 0; i < 7; i ++)
			stackList[i] = new ItemStack(Blocks.GOLD_BLOCK);
		stackList[7] = new ItemStack(Blocks.SANDSTONE);
		HereticRitualRecipes.addRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.GOLDEN_APPLE, 1, 1), EnumHereticCauldronFluidType.HARSHING_WATER,
				stackList);
		
		HereticRitualRecipes.addRecipe(new ItemStack(HarshenItems.itium), new ItemStack(HarshenItems.xray_pendant), EnumHereticCauldronFluidType.HARSHING_WATER, 
				new ItemStack(HarshenItems.ritual_crystal, 1, 1), new ItemStack(HarshenItems.powder_of_heretism), new ItemStack(HarshenItems.soul_infused_ingot),
				new ItemStack(Items.EMERALD), new ItemStack(Items.QUARTZ), new ItemStack(Items.ENDER_EYE),
				new ItemStack(Blocks.END_ROD), new ItemStack(Blocks.LAPIS_ORE));
		
		
		
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.ritual_crystal, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1), EnumHereticCauldronFluidType.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.harshen_soul_ingot, 1, 0), new ItemStack(HarshenItems.soul_infused_ingot, 1, 0), EnumHereticCauldronFluidType.HARSHING_WATER);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), EnumHereticCauldronFluidType.HARSHEN_DIMENSIONAL_FLUID);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), EnumHereticCauldronFluidType.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.OBSIDIAN, 2), EnumHereticCauldronFluidType.LAVA);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.glass_container), new ItemStack(HarshenItems.glass_container, 1, 3), EnumHereticCauldronFluidType.MILK);
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
	}
}
