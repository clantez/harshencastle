package kenijey.harshencastle.intergration.jei.hereticritual;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseJeiCategory;
import kenijey.harshencastle.enums.blocks.CauldronLiquid;
import kenijey.harshencastle.items.GlassContainer;
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
	
	private static HashMap<CauldronLiquid, IDrawable> fluidTypes = new HashMap<>();
	
	private IDrawable currentFluid;
	private IDrawable ritualFront;
	private String name;

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if(!(recipeWrapper instanceof JEIHereticRitualWrapper))
			return;
		JEIHereticRitualWrapper wrapper = (JEIHereticRitualWrapper) recipeWrapper;
		currentFluid = fluidTypes.get(wrapper.getCatalyst());
		name = GlassContainer.getGlassContaining(wrapper.getCatalyst());

		int index = 1;
		Point center = new Point(75, 46);
		Point point = new Point(center.x, center.y - 52);

		recipeLayout.getItemStacks().init(0, true, 7, 14);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(ingredients.getInputs(ItemStack.class).size() - 1));
		
		List<List<ItemStack>> stackList = ingredients.getInputs(ItemStack.class);
		stackList.remove(stackList.size() - 1);
		Collections.shuffle(stackList);
		for(int i = 0; i < stackList.size(); i++) {
			recipeLayout.getItemStacks().init(index, true, point.x, point.y);
			recipeLayout.getItemStacks().set(index, stackList.get(i));
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
//		for(EnumHereticCauldronFluidType fluid : EnumHereticCauldronFluidType.values())
//			fluidTypes.put(fluid, helper.createDrawable(fluid.getResourceLoc(), 0, 0, 17, 4));
		ritualFront = helper.createDrawable(new ResourceLocation(HarshenCastle.MODID, "textures/gui/jei/hereticritual-front.png"), 0, 0, 150, 110, 150, 110);

	}
	
	@Override
	protected void drawMore(Minecraft minecraft) {
//		currentFluid.draw(minecraft, 6, 46);
		ritualFront.draw(minecraft);
	}
	
	@Override
	public List getTooltipStrings(int mouseX, int mouseY) {
		if(mouseX > 5 && mouseX < 24 && mouseY > 45 && mouseY < 51)
			return Arrays.asList(new TextComponentTranslation(name).getFormattedText());
		return super.getTooltipStrings(mouseX, mouseY);
	}

}
