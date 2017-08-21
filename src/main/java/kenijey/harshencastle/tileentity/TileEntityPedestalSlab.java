package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenijey.harshencastle.recipies.HarshenRecipes;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class TileEntityPedestalSlab extends BaseTileEntityHarshenSingleItemInventoryActive
{
	
	private boolean isactive;
	private PedestalSlabRecipes workingRecipe;
	
	@Override
	protected boolean checkForCompleation(boolean checkingUp) {
		boolean flag = false;
		if(!checkingUp)
			for(PedestalSlabRecipes recipe : HarshenRecipes.allPedestalRecipes)
				if(recipe.getInput().getItem() == getItem().getItem())
				{
					if(!checkingUp)
						workingRecipe = recipe;
					flag = true;
				}
		if(!flag)
			return false;
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++)
				if(!(world.getBlockState(pos.add(x, -1, z)).getBlock() == Blocks.SOUL_SAND && 
				(world.getBlockState(pos.add(x, 0, z)).getBlock() == HarshenBlocks.blood_block || (x == 0 && z == 0))))
					flag = false;
		return flag;
	}
	
	@Override
	protected void onItemAdded() {
		if(checkForCompleation(false))
			activateRecipe();
	}

	@Override
	protected int getTicksUntillDone() {
		return 300;
	}
	@Override
	protected void finishedTicking() {
		setItem(workingRecipe.getOutput());
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++)
				if(!(x == 0 && z == 0))
					world.setBlockToAir(pos.add(x, 0, z));
	}
}
