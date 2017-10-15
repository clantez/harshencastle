package kenijey.harshencastle.intergration.jei.hereticritual;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.base.BaseJeiWrapper;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;

public class JEIHereticRitualWrapper extends BaseJeiWrapper	
{
	private final ItemStack output;
	private final CauldronLiquid catalyst;
	private final List<List<ItemStack>> allInputs;

	@SuppressWarnings("unchecked")
	public JEIHereticRitualWrapper(HereticRitualRecipes recipe) {
		ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
		builder.add(recipe.getCauldronInput().getStackList());
		for(HarshenStack stack : recipe.getPedestalItems())
			builder.add(stack.getStackList());
		if(HarshenUtils.glassContainerHasBlock(recipe.getCatalyst()))
			builder.add(ImmutableList.of(new ItemStack(HarshenItems.ITEM_LIQUID, 1, ItemLiquidTypeset.getMetaFromType(recipe.getCatalyst()))));
		builder.add(ImmutableList.of(EnumGlassContainer.getContainerFromType(recipe.getCatalyst()).getStack()));
		builder.add(ImmutableList.of(new ItemStack(HarshenBlocks.HERETIC_CAULDRON)));
		output = recipe.getOutput();
		allInputs = builder.build();
		catalyst = recipe.getCatalyst();
	}
	

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, allInputs);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	public CauldronLiquid getCatalyst() 
	{
		return catalyst;
	}
}
