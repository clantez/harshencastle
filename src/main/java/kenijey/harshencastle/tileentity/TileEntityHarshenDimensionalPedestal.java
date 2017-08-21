package kenijey.harshencastle.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenijey.harshencastle.recipies.HarshenRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class TileEntityHarshenDimensionalPedestal extends BaseTileEntityHarshenSingleItemInventoryActive
{
	//public static ArrayList<BlockPos> positionsOfGo = new ArrayList<BlockPos>(); 
	private RitualRecipes workingRecipe;
	
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
		if(isActive() && workingRecipe != null && !checkForCompleation(true))
			deactivateAll();
	}
	
	private void deactivateAll() 
	{
		isActive = false;
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
			((TileEntityHarshenDimensionalPedestal) world.getTileEntity(workingRecipe.getPositionOfRitual().offset(facing))).deactivate();	
	}

	@Override
	protected boolean checkForCompleation(boolean checkingUp)
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
						if(!checkingUp)
						{
							this.workingRecipe = recipe.setUpRitual(position);
							activate(position, blocks);
						}
						found = true;
						break;
					}
				}
			}
		}
		return found;
			
	}
	
	private void activate(BlockPos pos, ArrayList<BlockPos> positions)
	{
		for(BlockPos position : positions)
			((TileEntityHarshenDimensionalPedestal) world.getTileEntity(position)).resetActive();
	}
	@Override
	protected int getTicksUntillDone() {
		return 100;
	}

	@Override
	protected void finishedTicking() {
		handler.setStackInSlot(0, new ItemStack(Blocks.AIR));
		if(world.isRemote || workingRecipe == null)
			return;
		BlockPos pos = workingRecipe.getPositionOfRitual();
		for(EnumFacing face : EnumFacing.HORIZONTALS)
			if(this.pos.offset(face).equals(pos))
			{
				if(workingRecipe.lightning())
					world.addWeatherEffect(new EntityLightningBolt(world, pos.getX(), pos.getY(), pos.getZ(), true));
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), workingRecipe.getOutput());
				return;
			}
	}
}
