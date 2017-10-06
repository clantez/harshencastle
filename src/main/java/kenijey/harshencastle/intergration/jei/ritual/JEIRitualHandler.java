package kenijey.harshencastle.intergration.jei.ritual;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.intergration.jei.JEICategoryUIDs;
import kenijey.harshencastle.recipies.RitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIRitualHandler extends BaseJeiHandler<RitualRecipes> 
{
	@Nonnull
	@Override
	public Class<RitualRecipes> getRecipeClass() {
		return RitualRecipes.class;
	}

	@Override
	public String getRecipeCategoryUid(RitualRecipes recipe) {
		return JEICategoryUIDs.RITUAL;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(RitualRecipes recipe) {
		return new JEIRitualWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(RitualRecipes recipe) {
		return recipe.getInputs().size() == 4;
	}
}
