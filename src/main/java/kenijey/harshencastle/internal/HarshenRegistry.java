package kenijey.harshencastle.internal;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenRegistry;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.item.ItemStack;

public class HarshenRegistry implements IHarshenRegistry
{

	@Override
	public void registerRitualRecipe(ItemStack output, boolean useLightning, HarshenStack... inputs) {
		if(inputs.length == 4)
			RitualRecipes.addRecipe(HarshenUtils.toArray(inputs), output, useLightning);
	}

	@Override
	public void registerCauldronRecipe(HarshenStack input, ItemStack output, CauldronLiquid liquid) {
		CauldronRecipes.addRecipe(input, output, liquid);
	}

	@Override
	public void registerHereticRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst,
			HarshenStack... pedstalItems) {
		if(pedstalItems.length == 8)
			HereticRitualRecipes.addRecipe(cauldronItem, output, catalyst, pedstalItems);
	}

	@Override
	public void registerPedestalRecipe(HarshenStack input, ItemStack output) {
		PedestalSlabRecipes.addRecipe(input, output);
	}

	@Override
	public void registerMagicTableRecipe(ItemStack output, HarshenStack... inputs) {
		if(inputs.length == 4)
			MagicTableRecipe.addRecipe(HarshenUtils.toArray(inputs), output);
	}

	@Override
	public boolean hasInit() {
		return HarshenAPIHandler.hasInit;
	}

}
