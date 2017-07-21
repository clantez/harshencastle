package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseHarshenTileEntity;
import kenijey.harshencastle.blocks.HarshenDimensionalGate;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityHarshenDimensionalGate extends BaseHarshenTileEntity implements ITickable
{
	
	private int ticksLeft = 12000;

	@Override
	public void update() {
		if(getBlockMetadata() == 2)
			if(ticksLeft-- == 0)
				((HarshenDimensionalGate)world.getBlockState(pos).getBlock()).deactivate(world, pos);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("TicksLeft", ticksLeft);
		return super.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		ticksLeft = compound.getInteger("TicksLeft");
		super.readFromNBT(compound);
	}
	
	public int getTick()
	{
		return ticksLeft;
	}
}
