package kenijey.harshencastle.api;

import java.util.List;

import kenijey.harshencastle.enums.CauldronLiquid;
import net.minecraft.item.ItemStack;

public interface IHarshenPluginHandler 
{
	void registerRitualRecipe(ItemStack output, boolean useLightning, HarshenStack... inputs);
	
	void registerCauldronRecipe(HarshenStack input, ItemStack output, CauldronLiquid liquid);
		
	void registerHereticRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst, HarshenStack... pedstalItems);
	
	void registerPedestalRecipe(HarshenStack input, ItemStack output);

	void registerMagicTableRecipe(ItemStack output, HarshenStack... inputs);
	
	boolean hasInit();
	
}
