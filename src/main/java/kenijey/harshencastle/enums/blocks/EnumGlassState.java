package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.interfaces.IIDSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumGlassState implements IStringSerializable, IIDSet
{
	OFF,
	UP,
	DOWN;
	
	private int id;

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return toString().toLowerCase();
	}
	
	public int getId() {
		return id;
	}
	
	public static EnumGlassState getFromMeta(int meta)
	{
		for(EnumGlassState state : EnumGlassState.values())
			if(state.id == meta)
				return state;
		return OFF;
	}
	
}
