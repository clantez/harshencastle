package kenijey.harshencastle.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityHereticCauldronTop extends TileEntity
{
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return (T) world.getTileEntity(pos.down()).getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return world.getTileEntity(pos.down()).hasCapability(capability, facing);
	}
}
