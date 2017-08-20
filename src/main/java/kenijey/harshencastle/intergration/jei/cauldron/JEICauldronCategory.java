package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.HashMap;

import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class JEICauldronCategory extends BaseJeiCategory
{
	public JEICauldronCategory(String name, IGuiHelper guiHelper) {
		super(name, guiHelper);
	}
	
	private static HashMap<EnumHetericCauldronFluidType, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEICauldronWrapper))
			return;
		JEICauldronWrapper wrapper = (JEICauldronWrapper) recipeWrapper;
		System.out.println("S");
		currentFluid = fluidTypes.get(wrapper.getCatalyst());
		recipeLayout.getItemStacks().init(0, true, 67, 30);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
		recipeLayout.getItemStacks().init(4, false, 103, 17);
		recipeLayout.getItemStacks().set(4, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	@Override
	protected void createDrawable(IGuiHelper helper) {
		for(EnumHetericCauldronFluidType fluid : EnumHetericCauldronFluidType.values())
			fluidTypes.put(fluid, helper.createDrawable(fluid.getResourceLoc(), 0, 0, 70, 20));
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		overlay.draw(minecraft);
		currentFluid.draw(minecraft);
		GlStateManager.disableBlend();
		GlStateManager.disableAlpha();
	}


}
