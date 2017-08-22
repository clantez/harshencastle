package kenijey.harshencastle.recipies;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HarshenRecipes {
	
	public static ArrayList<RitualRecipes> allRitualRecipes = new ArrayList<>();
	public static ArrayList<CauldronRecipes> allCauldronRecipes = new ArrayList<>();
	public static ArrayList<PedestalSlabRecipes> allPedestalRecipes = new ArrayList<>();
	
	public static void register()
	{
		craftingRegistry();
		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.pontus_world_gate_parts, 1, 0), new ItemStack(HarshenItems.pontus_world_gate_parts, 1, 1),
				new ItemStack(HarshenItems.pontus_world_gate_parts, 1 ,2), new ItemStack(HarshenItems.harshen_soul_fragment)), new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 0), true);		
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.soul_harsher_sword, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.light_emitted_essence, 1 ,0), new ItemStack(HarshenItems.blood_essence)), new ItemStack(HarshenItems.empowered_soul_harsher_sword, 1, 0), true);
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1),
				new ItemStack(HarshenItems.itium, 1 ,0), new ItemStack(Items.NETHER_STAR)), new ItemStack(HarshenItems.pontus_world_gate_spawner, 1, 1), true);
		RitualRecipes.addRecipe(Arrays.asList(new ItemStack(HarshenItems.itium, 1, 0), new ItemStack(HarshenItems.light_emitted_essence, 1, 0),
				new ItemStack(HarshenItems.ritual_crystal, 1 ,1), new ItemStack(Items.QUARTZ)), new ItemStack(Items.NETHER_STAR, 1, 0), true);
	
		
		PedestalSlabRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.NETHERRACK));
		
		
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.ritual_crystal, 1, 0), new ItemStack(HarshenItems.ritual_crystal, 1, 1), EnumHetericCauldronFluidType.BLOOD);
		CauldronRecipes.addRecipe(new ItemStack(HarshenItems.harshen_soul_ingot, 1, 0), new ItemStack(HarshenItems.soul_infused_ingot, 1, 0), EnumHetericCauldronFluidType.HARSHING_WATER);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.SAND, 1, 0), new ItemStack(Blocks.SOUL_SAND, 1, 0), EnumHetericCauldronFluidType.HARSHEN_DIMENSIONAL_FLUID);
		CauldronRecipes.addRecipe(new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.NETHERRACK, 1, 0), EnumHetericCauldronFluidType.BLOOD);
	}	
	
	public static void craftingRegistry()
	{
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "soul_harsher_sword"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.soul_harsher_sword),
				" h ",
				"hsh",
				" i ",
				
				's', new ItemStack(HarshenItems.harshen_soul_fragment),
				'h', new ItemStack(HarshenItems.harshen_crystal),
				'i', "ingotIron");
		
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
				'g', "nuggetGold");
		
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
				
				'e', new ItemStack(Items.ENDER_EYE),
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
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "ladle"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.ladle),
				"  s",
				"  s",
				" p ",
				
				's', "stickWood",
				'p', "plankWood");
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "blood_collector"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.blood_collector, 1, 0),
				"  i",
				"rbi",
				"nri",
				
				'b', "blockIron",
				'i', "ingotIron",
				'r', new ItemStack(Blocks.IRON_BARS),
				'n', "nuggetIron");
	}
}
