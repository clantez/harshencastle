package kenijey.harshencastle;

import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.objecthandlers.HarshenGlassContainerIngredient;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class HarshenCraftingRecipes 
{
	public static void register()
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
				"grg",
				" i ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				's', new ItemStack(HarshenItems.HARSHEN_SOUL_FRAGMENT),
				'g', new ItemStack(HarshenItems.POWDER_OF_HERETISM),
				'r', new ItemStack(HarshenItems.EMPTY_RING));
		
		
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
				
				'e', new ItemStack(HarshenItems.EMPTY_RING),
				'g', "ingotGold");
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "telering"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.TELERING),
				" i ",
				"grg",
				" e ",
				
				'i', new ItemStack(HarshenItems.ITIUM),
				'e', new ItemStack(HarshenItems.BLOOD_INFUSED_ENDER_EYE),
				'g', new ItemStack(Items.GOLDEN_CARROT),
				'r', new ItemStack(HarshenItems.EMPTY_RING));
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "glass_container"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.GLASS_CONTAINER, 32),
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
				"nrn",
				" b ",
				
				'n', "nuggetIron",
				'b', new ItemStack(Blocks.IRON_BARS),
				'r', new ItemStack(HarshenItems.RING_OF_BLOOD));
		
		
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
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "empty_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.EMPTY_RING, 2),
				" n ",
				"n n",
				" n ",
				
				'n', "nuggetIron");
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "soul_reminder"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenBlocks.SOUL_REMINDER),
				"csc",
				"s s",
				"csc",
				
				'c', new ItemStack(Blocks.WEB),
				's', new ItemStack(Items.STRING));
		for(EnumGlassContainer glass : EnumGlassContainer.values())
			if(HarshenUtils.glassContainerHasState(glass))
			{
				Block block = ((IBlockState) glass.getType().getStateOrLoc()).getBlock();
				HarshenGlassContainerIngredient[] ingridientList = new HarshenGlassContainerIngredient[9];
				ingridientList[0] = new HarshenGlassContainerIngredient(EnumGlassContainer.EMPTY.getStack()); 
				for(int i = 1; i < 9; i++)
					ingridientList[i] = new HarshenGlassContainerIngredient(HarshenUtils.toList(HarshenUtils.getAllRelatives(HarshenUtils.phaseBucket(block))));
				GameRegistry.addShapelessRecipe(new ResourceLocation(HarshenCastle.MODID, glass.getType().getName().split(":")[1] + "_container"), new ResourceLocation("harshen_items"), glass.getStack(), ingridientList);
			}
	}
}
