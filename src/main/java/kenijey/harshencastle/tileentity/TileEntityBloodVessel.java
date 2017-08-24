package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseHarshenTileEntity;
import kenijey.harshencastle.interfaces.IHudDisplay;
import net.minecraft.util.math.MathHelper;

public class TileEntityBloodVessel extends BaseHarshenTileEntity implements IHudDisplay
{
	private int bloodLevel = 0;
	public static final int maxLevel = 50;
	
	public int add(int amount)
	{
		int ret = amount - (bloodLevel + amount <= maxLevel ? 0 : amount - (maxLevel - bloodLevel));
		bloodLevel = MathHelper.clamp(bloodLevel += amount, 0, maxLevel);
		return ret;
	}
	
	public int remove(int amount)
	{
		int ret = bloodLevel - amount >= 0 ? amount : bloodLevel;
		bloodLevel = MathHelper.clamp(bloodLevel -= amount, 0, maxLevel);
		return ret;
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
	
	public int getPossibleRemove()
	{
		return bloodLevel;
	}
}
