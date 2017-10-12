package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventoryActive;
import kenijey.harshencastle.interfaces.IBloodSupply;
import net.minecraft.item.ItemStack;

public class TileEntityBloodFactory extends BaseTileEntityHarshenSingleItemInventoryActive
{	
	private IBloodSupply itemSupply;
	
	@Override
	protected int getTicksUntillDone() {
		return itemSupply == null ? 0 : itemSupply.ticksUntillUsed();
	}

	private int tickRate = 0;
	
	@Override
	protected void finishedTicking() 
	{
		setItem(ItemStack.EMPTY);	
	}
	
	@Override
	protected boolean checkForCompleation(boolean checkingUp) {
		if(getItem().getItem() instanceof IBloodSupply && !checkingUp && world.getTileEntity(pos.down()) instanceof TileEntityBloodVessel
				&& ((TileEntityBloodVessel)world.getTileEntity(pos.down())).canAdd(((IBloodSupply) getItem().getItem()).getAmountPerSecond()))
		{
			itemSupply = (IBloodSupply) getItem().getItem();
			activateRecipe();
		}
		return getItem().getItem() instanceof IBloodSupply;
	}
	
	@Override
	protected void tick() {	
		if(!isActive())
			checkForCompleation(false);
		if(isActive() && !(getItem().getItem() instanceof IBloodSupply))
		{
			itemSupply = null;
			deactivate();
		}
		if(isActive() && itemSupply != null && world.getTileEntity(pos.down()) instanceof TileEntityBloodVessel && tickRate++ % 20 == 0 )
		{
			if(!((TileEntityBloodVessel)world.getTileEntity(pos.down())).canAdd(itemSupply.getAmountPerSecond()))
				deactivate();
			else
				((TileEntityBloodVessel)world.getTileEntity(pos.down())).change(itemSupply.getAmountPerSecond());

		}
	}

}