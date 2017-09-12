package kenijey.harshencastle.objecthandlers;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class FaceRenderer 
{
	private final EnumFacing face;
	private final BlockPos position;
	
	public FaceRenderer(BlockPos position, EnumFacing face) {
		this.position = position;
		this.face = face;
	}
	
	public EnumFacing getFace() {
		return face;
	}

	public BlockPos getPosition() {
		return position;
	}
}
