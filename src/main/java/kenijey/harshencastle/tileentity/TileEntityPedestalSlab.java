package kenijey.harshencastle.tileentity;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.Vec3d;

public class TileEntityPedestalSlab extends BaseTileEntityHarshenSingleItemInventoryActive
{
	private PedestalSlabRecipes workingRecipe;
	
	@Override
	protected boolean checkForCompleation(boolean checkingUp) {
		boolean flag = false;
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
			for(int z = -1; z < 2; z++){
				if((world.getBlockState(pos.add(x, -1, z)).getBlock() != Blocks.SOUL_SAND || 
				world.getBlockState(pos.add(x, 0, z)).getBlock() != HarshenBlocks.blood_block) && !(x == 0 && z == 0))
					flag = false;
			}
		return flag;
	}
	
	@Override
	protected void tick() {
		if(!checkForCompleation(true) && isActive())
			deactivate();
		else if(isActive())
		{
			for(int x = -1; x < 2; x++)
				for(int z = -1; z < 2; z++)
					if(!(x == 0 && z == 0))
						for(int i = 0; i < 8; i ++)
						{
							Vec3d pos = new Vec3d(this.pos.add(x, 0, z)).addVector(randPos(), -0.1, randPos());
							HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.BLOOD, pos, 
									new Vec3d((this.pos.getX() + 0.5 - pos.x) / 20D, (this.pos.getY() + 0.5 - pos.y) / 20D, (this.pos.getZ() + 0.5 - pos.z) / 20D), 1f, false);

						}
			
		}	
		else if(checkForCompleation(false))
			activateRecipe();			
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
				{
					world.setBlockToAir(pos.add(x, 0, z));
					for(int i = 0; i < new Random().nextInt(10) + 100; i++)
						HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.BLOOD, new Vec3d(pos.add(x, 0, z)).addVector(randPos(), 0, randPos()), new Vec3d(0, 0.01, 0), 1f, false);
				}
					
	}
}
