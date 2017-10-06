package kenijey.harshencastle.intergration.jei.hereticritual;

import javax.annotation.Nonnull;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.intergration.jei.JEICategoryUIDs;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticRitualHandler extends BaseJeiHandler<HereticRitualRecipes>
{
	@Nonnull
	@Override
	public Class<HereticRitualRecipes> getRecipeClass() {
		return HereticRitualRecipes.class;
	}

	@Override
	public String getRecipeCategoryUid(HereticRitualRecipes recipe) {
		return JEICategoryUIDs.HERETIC_RITUAL;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(HereticRitualRecipes recipe) {
		return new JEIHereticRitualWrapper(recipe);
	}

	@Override
	public boolean isRecipeValid(HereticRitualRecipes recipe) {
		return true;
	}
}
