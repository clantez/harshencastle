package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import net.minecraft.item.ItemStack;

public class TileEntityPedestalSlab extends BaseTileEntityHarshenSingleItemInventory 
{
	
	private boolean isactive;
	@Override
	protected void checkForCompleation() {
		if(PedestalSlabRecipes.getRecipe(getItem()) != null) 
			System.out.println(PedestalSlabRecipes.getRecipe(getItem()).getOutput());
	}
}
