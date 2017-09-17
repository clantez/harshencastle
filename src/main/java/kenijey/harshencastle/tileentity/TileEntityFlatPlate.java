package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.handlers.client.HandlerFlatPlateRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityFlatPlate extends TileEntity implements ITickable
{

	@Override
	public void update() {
		if(HarshenConfigs.GENERAL.renderPlates)
		HandlerFlatPlateRenderer.addPosition(pos);
	}
}
