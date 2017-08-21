package kenijey.harshencastle.intergration.jei.cauldron;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class JEICauldronCategory extends BaseJeiCategory
{
	public JEICauldronCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}

	private static HashMap<EnumHetericCauldronFluidType, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;
	private IDrawable frontOfCauldron;
	private String name;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEICauldronWrapper))
			return;
		JEICauldronWrapper wrapper = (JEICauldronWrapper) recipeWrapper;
		currentFluid = fluidTypes.get(wrapper.getCatalyst());
		name = "fluid." + wrapper.getCatalyst().getName();
		recipeLayout.getItemStacks().init(0, true, 65, 1);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0));
		recipeLayout.getItemStacks().init(1, false, 66, 29);
		recipeLayout.getItemStacks().set(1, Arrays.asList(new ItemStack(HarshenItems.ladle)));
		recipeLayout.getItemStacks().init(2, false, 132, 41);
		recipeLayout.getItemStacks().set(2, ingredients.getOutputs(ItemStack.class).get(0));
	}
	
	@Override
	protected void createDrawable(IGuiHelper helper) {
		for(EnumHetericCauldronFluidType fluid : EnumHetericCauldronFluidType.values())
			fluidTypes.put(fluid, helper.createDrawable(fluid.getResourceLoc(), 0, 0, 38, 14, 38, 14));
		frontOfCauldron = helper.createDrawable(new ResourceLocation(HarshenCastle.MODID, "textures/gui/jei/cauldron-front.png"), 0, 0, 150, 110, 150, 110);
	}
	
	@Override
	protected void drawMore(Minecraft minecraft) {
		currentFluid.draw(minecraft, 56, 49);
		frontOfCauldron.draw(minecraft);
	}
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX > 56 && mouseX < 94 && mouseY > 49 && mouseY < 63)
			return Arrays.asList(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}

}
