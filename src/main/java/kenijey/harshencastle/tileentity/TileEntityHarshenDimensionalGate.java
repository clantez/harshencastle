package kenijey.harshencastle.tileentity;

import akka.actor.FSM.State;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseHarshenTileEntity;
import kenijey.harshencastle.blocks.HarshenDimensionalGate;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityHarshenDimensionalGate extends BaseHarshenTileEntity implements ITickable
{
	
	private int ticksLeft = 12000;

	@Override
	public void update() {
		if(!world.getBlockState(pos).getValue(HarshenDimensionalGate.ACTIVE) && !world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))).isEmpty())
			for(EntityItem entityitem : world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))))
				if(entityitem.getItem().getItem() == HarshenItems.ritual_crystal && entityitem.getItem().getMetadata() == 1)
				{
					world.setBlockState(pos, world.getBlockState(pos).getBlock().getDefaultState().withProperty(HarshenDimensionalGate.ACTIVE, true)
							.withProperty(HarshenDimensionalGate.FOREVER, world.getBlockState(pos).getValue(HarshenDimensionalGate.FOREVER)), 3);
					entityitem.getItem().setCount(entityitem.getItem().getCount() - 1);
				}
					
			
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
