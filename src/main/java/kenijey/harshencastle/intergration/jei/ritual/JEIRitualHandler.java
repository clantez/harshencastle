package kenijey.harshencastle.intergration.jei.ritual;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.recipies.RitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIRitualHandler extends BaseJeiHandler<RitualRecipes> 
{
	@Override
	public IRecipeWrapper getRecipeWrapper(RitualRecipes recipe) {
		return new JEIRitualWrapper(recipe);
	}
}
