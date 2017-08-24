package kenijey.harshencastle.base;

import net.minecraft.util.math.BlockPos;

public abstract class BaseTileEntityHarshenSingleItemInventoryActive extends BaseTileEntityHarshenSingleItemInventory 
{
	protected boolean isActive;
	protected int activeTimer;
	
	@Override
	public void update() {
		if(isActive)
			if(activeTimer++ == getTicksUntillDone())
			{
				finishedTicking();
				deactivate();
			}
			else;
		else activeTimer = 0;
		super.update();
	}
	
	@Override
	protected void onItemAdded() {
		checkForCompleation(false);
	}
	
	protected boolean checkForCompleation(boolean checkingUp)
	{
		return false;
	}
	
	public void deactivate()
	{
		this.isActive = false;
		this.activeTimer = 0;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public int getActiveTimer() {
		return activeTimer;
	}
	
	protected void activateRecipe()
	{
		this.activeTimer = 0;
		this.isActive = true;
	}
	
	protected abstract int getTicksUntillDone();
	
	protected abstract void finishedTicking();
}
