package kenijey.harshencastle.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes 
{
	public static void init()
	{
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "soul_harsher_sword"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.soul_harsher_sword),
				"h",
				"hsh",
				"i",
				
				's', HarshenItems.harshen_soul_fragment,
				'h', HarshenItems.harshen_essence,
				'i', Items.IRON_INGOT);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harshen_soul_ingot"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.harshen_soul_ingot),
				"e e",
				"shs",
				"e e",
				
				'h', HarshenItems.harshen_essence,
				's', HarshenItems.harshen_soul_fragment,
				'e', Items.EMERALD);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "pontus_ring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.pontus_ring),
				" s ",
				"gig",
				" g ",
				
				'i', HarshenItems.itium,
				's', HarshenItems.harshen_soul_fragment,
				'g', Items.GOLD_NUGGET);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "bloody_earring"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.bloody_earring),
				" i ",
				" s ",
				" b ",
				
				'i', HarshenItems.itium,
				's', HarshenItems.harshen_soul_fragment,
				'b', HarshenItems.blood_essence);
	}
}
