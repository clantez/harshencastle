package kenijey.harshencastle.intergration.jei.pedestalslab;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIPedestalSlabHandler extends BaseJeiHandler<PedestalSlabRecipes> {

	@Override
	public IRecipeWrapper getRecipeWrapper(PedestalSlabRecipes recipe) {
		return new JEIPedestalSlabWrapper(recipe);
	}
}
