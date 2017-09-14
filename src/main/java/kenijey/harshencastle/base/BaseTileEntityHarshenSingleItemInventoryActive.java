package kenijey.harshencastle.base;

import net.minecraft.nbt.NBTTagCompound;

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
	
	public void activateRecipe()
	{
		this.activeTimer = 0;
		this.isActive = true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		isActive = compound.getBoolean("isActive");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("isActive", isActive);
		return super.writeToNBT(nbt);
	}
	
	protected abstract int getTicksUntillDone();
	
	protected abstract void finishedTicking();
}
