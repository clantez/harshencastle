package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
import net.minecraft.util.text.TextComponentTranslation;

public class JEICauldronCategory extends BaseJeiCategory
{
	public JEICauldronCategory(String name, IGuiHelper guiHelper) {
		super(name, guiHelper);
	}
	
	private static HashMap<EnumHetericCauldronFluidType, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;
	private String name;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEICauldronWrapper))
			return;
		JEICauldronWrapper wrapper = (JEICauldronWrapper) recipeWrapper;
		currentFluid = fluidTypes.get(wrapper.getCatalyst());
		name = "fluid." + wrapper.getCatalyst().getName();
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
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX < 70 && mouseY < 20)
			return Arrays.asList(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}

}
