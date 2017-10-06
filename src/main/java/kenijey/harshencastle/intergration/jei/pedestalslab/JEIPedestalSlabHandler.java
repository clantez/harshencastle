package kenijey.harshencastle.intergration.jei.pedestalslab;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.intergration.jei.JEICategoryUIDs;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIPedestalSlabHandler extends BaseJeiHandler<PedestalSlabRecipes> {

	@Nonnull
	@Override
	public Class<PedestalSlabRecipes> getRecipeClass() {
		return PedestalSlabRecipes.class;
	}

	@Override
	public String getRecipeCategoryUid(PedestalSlabRecipes recipe) {
		return JEICategoryUIDs.PENDESTAL_SLAB;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(PedestalSlabRecipes recipe) {
		return new JEIPedestalSlabWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(PedestalSlabRecipes recipe) {
		return true;
	}
}
