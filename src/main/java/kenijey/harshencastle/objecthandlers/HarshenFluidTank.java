package kenijey.harshencastle.objecthandlers;

import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class HarshenFluidTank extends FluidTank
{

	private TileEntityHereticCauldron tileEntity;
	
	public HarshenFluidTank(int capacity) {
		super(capacity);
		tileEntity = (TileEntityHereticCauldron) tile;
	}
	
	@Override
	public boolean canFillFluidType(FluidStack fluid) {
		return fluid.getFluid() == tileEntity.getFluid().getFromBucket() && tileEntity.getLevel() != 1 && canFill();
	}

}
