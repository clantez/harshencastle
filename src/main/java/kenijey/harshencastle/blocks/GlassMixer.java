package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.enums.blocks.EnumGlassState;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

public class GlassMixer extends BaseBlockHarshenSingleInventory
{

	public static final PropertyEnum<EnumGlassState> STATE = PropertyEnum.create("state", EnumGlassState.class);

	
	public GlassMixer() {
		super(Material.IRON);
		setRegistryName("glass_mixer");
		setUnlocalizedName("glass_mixer");
		this.setDefaultState(this.blockState.getBaseState().withProperty(STATE, EnumGlassState.OFF));
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return null;
	}
	
	@Override
	protected boolean isBreakNBT() {
		return true;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STATE, EnumGlassState.getFromMeta(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STATE).getId();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {STATE}) ;
	}

}
