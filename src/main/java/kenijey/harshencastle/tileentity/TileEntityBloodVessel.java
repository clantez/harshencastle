package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseHarshenTileEntity;
import kenijey.harshencastle.interfaces.IHudDisplay;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class TileEntityBloodVessel extends BaseHarshenTileEntity implements IHudDisplay
{
	private int bloodLevel = 0;
	public static final int maxLevel = 50;
	
	public void add(int amount)
	{
		bloodLevel = MathHelper.clamp(bloodLevel += amount, 0, maxLevel);
	}
	
	public void remove(int amount)
	{
		bloodLevel = MathHelper.clamp(bloodLevel -= amount, 0, maxLevel);
	}
	
	public boolean canRemove(int amount)
	{
		return bloodLevel - amount >= 0;
	}
	
	public boolean canAdd(int amount)
	{
		return bloodLevel + amount <= maxLevel;
	}
		
	@Override
	public String getText() {
		return String.valueOf(bloodLevel) + "/" + String.valueOf(maxLevel);
	}

	public int getPossibleAdd()
	{
		return maxLevel - bloodLevel;
	}
	
	public void setBloodLevel(int bloodLevel) {
		this.bloodLevel = bloodLevel;
	}
	
	public int getPossibleRemove()
	{
		return bloodLevel;
	}
	
	public int getMax()
	{
		return this.maxLevel;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.bloodLevel = compound.getInteger("BloodLevel");
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("BloodLevel", this.bloodLevel);
		return super.writeToNBT(nbt);
	}
}
