package kenijey.harshencastle.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityHarshenDimensionalPedestal extends BaseTileEntityHarshenSingleItemInventory
{
	int activeTimer = 0;
	public static ArrayList<BlockPos> positionsOfGo = new ArrayList<BlockPos>(); 
	private boolean isActive = false;
	private RitualRecipes workingRecipe;
	
	public float getMove()
	{
		return activeTimer / 100f;
	}
	
	public BlockPos getMoveDirection()
	{
		for(BlockPos pos : positionsOfGo)
			if(pos.distanceSq(this.pos) < 2)
				for(EnumFacing face : EnumFacing.HORIZONTALS)
					if(this.pos.offset(face).equals(pos))
						return new BlockPos(0, 0, 0).offset(face);
				
		return null;
	}
	
	@Override
	public void tick() {
		boolean flag = handler.getStackInSlot(0).getItem() == Item.getItemFromBlock(Blocks.AIR);
		if(flag)
			timer = 0;
		if(flag != hasItem)
		{
			hasItem = flag;
			dirty();
		}
		
		if(isActive)
			if(activeTimer++ == 100)
			{
				BlockPos rem = null;
				isActive = false;
				handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
				for(BlockPos pos : positionsOfGo)
				{
					if(pos.distanceSq(this.pos) < 2)
						for(EnumFacing face : EnumFacing.HORIZONTALS)
							if(this.pos.offset(face).equals(pos))
							{
								if(workingRecipe == null)
									continue;
								if(workingRecipe.lightning())
									world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
								if(!world.isRemote)
									InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), workingRecipe.getOutput());
								rem = pos;
								break;
							}
					if(rem == null)
						break;
				}
				if(!world.isRemote)
					positionsOfGo.remove(rem);
			}
	}
	
	@Override
	public boolean setItem(ItemStack item) {
		super.setItem(item);
		checkForCompleation();
		return true;
	}

	private void checkForCompleation()
	{
		boolean found = false;
		for(RitualRecipes recipe : RitualRecipes.getRecipes(getItem()))
		{
			if(found)
				break;
			ArrayList<Item> localItems = new ArrayList<Item>();
			for(ItemStack stack : recipe.getInputs())
				localItems.add(stack.getItem());
			for(EnumFacing facing : EnumFacing.HORIZONTALS)
			{
				BlockPos position = pos.offset(facing);
				ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
				ArrayList<Boolean> isBlock = new ArrayList<Boolean>();
				ArrayList<Boolean> isBlockHolding = new ArrayList<Boolean>();
				for(EnumFacing face : EnumFacing.HORIZONTALS)
				{
					boolean flag = world.getTileEntity(position.offset(face)) instanceof TileEntityHarshenDimensionalPedestal;
					isBlock.add(flag);
					if(flag)
						blocks.add(position.offset(face));
				}
				if(!isBlock.contains(false))
				{
					for(EnumFacing face : EnumFacing.HORIZONTALS)
						if(localItems.contains(((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position.offset(face))).getItem().getItem()))
							localItems.remove(((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position.offset(face))).getItem().getItem());
					if(localItems.isEmpty())
					{
						this.workingRecipe = recipe;
						activate(position, blocks);
						found = true;
						break;
					}
				}
			}
		}
			
	}
	
	private void activate(BlockPos pos, ArrayList<BlockPos> positions)
	{
		positionsOfGo.clear();
		positionsOfGo.add(pos);
		for(BlockPos position : positions)
			((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position)).setActive();
	}
	
	public void setActive()
	{
		isActive = true;
		activeTimer = 0;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
}
