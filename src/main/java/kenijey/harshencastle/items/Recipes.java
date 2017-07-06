package kenijey.harshencastle.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes 
{
	public static void init()
	{
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "harsher_sword"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.soul_harsher_sword),
				"a",
				"a",
				"b",
				'a', HarshenItems.harshen_soul_fragment,
				'b', Items.IRON_INGOT);
		
		GameRegistry.addShapedRecipe(new ResourceLocation("harshencastle", "waht do i do?"), new ResourceLocation("harshen_items"),
				new ItemStack(HarshenItems.soul_harsher_sword),
				"a",
				"a",
				"b",
				'a', HarshenItems.harshen_soul_fragment,
				'b', Items.IRON_INGOT);
		
	}
}
