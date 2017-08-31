package kenijey.harshencastle.intergration.jei.hereticritual;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class JEIHereticRitualCategory extends BaseJeiCategory
{

	public JEIHereticRitualCategory(String UID, IRecipeCategoryRegistration reg) {
		super(UID, reg);
	}
	
	private static HashMap<EnumHereticCauldronFluidType, IDrawable> fluidTypes = new HashMap<>(EnumHereticCauldronFluidType.values().length);
	
	private IDrawable currentFluid;
	private IDrawable ritualFront;
	private String name;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIHereticRitualWrapper))
			return;
		JEIHereticRitualWrapper wrapper = (JEIHereticRitualWrapper) recipeWrapper;
		currentFluid = fluidTypes.get(wrapper.getCatalyst());
		name = "fluid." + wrapper.getCatalyst().getName();

		int index = 1;
		Point center = new Point(75, 46);
		Point point = new Point(center.x, center.y - 42);

		recipeLayout.getItemStacks().init(0, true, 7, 14);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(ingredients.getInputs(ItemStack.class).size() - 1));
		
		for(int i = 0; i < ingredients.getInputs(ItemStack.class).size() - 1; i++) {
			recipeLayout.getItemStacks().init(index, true, point.x, point.y);
			recipeLayout.getItemStacks().set(index, ingredients.getInputs(ItemStack.class).get(i));
			index += 1;
			point = rotatePointAbout(point, center, 45D);
		}

		recipeLayout.getItemStacks().init(index, false, 4, 88);
		recipeLayout.getItemStacks().set(index, ingredients.getOutputs(ItemStack.class).get(0));
	}

	private Point rotatePointAbout(Point in, Point about, double degrees) {
		double rad = degrees * Math.PI / 180.0;
		double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
		double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
		return new Point((int) newX, (int) newY);
	}
	@Override
	protected void createDrawable(IGuiHelper helper) {
		for(EnumHereticCauldronFluidType fluid : EnumHereticCauldronFluidType.values())
			fluidTypes.put(fluid, helper.createDrawable(fluid.getResourceLoc(), 0, 0, 17, 4));
		ritualFront = helper.createDrawable(new ResourceLocation(HarshenCastle.MODID, "textures/gui/jei/hereticritual-front.png"), 0, 0, 150, 110, 150, 110);

	}
	
	@Override
	protected void drawMore(Minecraft minecraft) {
		currentFluid.draw(minecraft, 6, 46);
		ritualFront.draw(minecraft);
	}
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX > 6 && mouseX < 23 && mouseY > 56 && mouseY < 60)
			return Arrays.asList(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}

}