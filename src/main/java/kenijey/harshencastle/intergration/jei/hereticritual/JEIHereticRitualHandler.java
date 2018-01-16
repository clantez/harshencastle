package kenijey.harshencastle.intergration.jei.hereticritual;

import kenijey.harshencastle.base.BaseJeiHandler;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import mezz.jei.api.recipe.IRecipeWrapper;

public class JEIHereticRitualHandler extends BaseJeiHandler<HereticRitualRecipes>
{
	@Override
	public IRecipeWrapper getRecipeWrapper(HereticRitualRecipes recipe) {
		return new JEIHereticRitualWrapper(recipe);
	}
}
