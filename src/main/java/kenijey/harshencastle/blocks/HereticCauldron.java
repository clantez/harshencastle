package kenijey.harshencastle.blocks;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldron extends BaseBlockHarshenSingleInventory
{
	private static HashMap<BlockPos, Boolean> creativeBreakMap = new HashMap<>(HarshenUtils.HASH_LIMIT);

	
	public static final PropertyEnum<EnumHereticCauldronFluidType> LIQUID =  PropertyEnum.<EnumHereticCauldronFluidType>create("liquid_type", EnumHereticCauldronFluidType.class);
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 1, 3);
	
	protected static final AxisAlignedBB AABB_LEGS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_NORTH = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.125D);
	protected static final AxisAlignedBB AABB_WALL_SOUTH = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_EAST = new AxisAlignedBB(0.875D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_WALL_WEST = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.125D, 1.0D, 1.0D);

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_)
    {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_LEGS);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_WEST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_NORTH);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_EAST);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_WALL_SOUTH);
    }
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if(getTile(worldIn, pos) == null)
			return this.getDefaultState();
		getTile(worldIn, pos).setLevel(MathHelper.clamp(getTile(worldIn, pos).getLevel(), 1, 3));
		return getTile(worldIn, pos) != null ? this.getDefaultState().withProperty(LIQUID, getTile(worldIn, pos).getFluid()).withProperty(LEVEL, getTile(worldIn, pos).getLevel()) : this.getDefaultState();
	}
	 
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public HereticCauldron() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIQUID, EnumHereticCauldronFluidType.NONE).withProperty(LEVEL, 1));
		TileEntityHereticCauldron.fluidMap.put(EnumHereticCauldronFluidType.LAVA, Items.LAVA_BUCKET);
		TileEntityHereticCauldron.fluidMap.put(EnumHereticCauldronFluidType.MILK, Items.MILK_BUCKET);
		setHardness(5.0F);
		setResistance(5.0F);
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHereticCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		boolean flag = getTile(worldIn, pos).onActivated(playerIn, hand);
		if(flag)
		{
			TileEntityHereticCauldron cauldron = getTile(worldIn, pos);
			worldIn.setBlockState(pos, getActualState(state, worldIn, pos), 3);
			cauldron.reactivate(0);
			return true;
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;//state.getValue(LIQUID).getMetaId() + state.getValue(LEVEL);
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState();//this.getDefaultState().withProperty(LEVEL, (meta % 3) + 1).withProperty(LIQUID, EnumHereticCauldronFluidType.getMatch(Math.floorDiv(meta, 3)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LIQUID, LEVEL});
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if(!(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop) && worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()))
			worldIn.setBlockState(pos.up(), HarshenBlocks.heretic_cauldron_top.getDefaultState(), 3);
		else
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
//		BaseTileEntityHarshenSingleItemInventory te = (BaseTileEntityHarshenSingleItemInventory) worldIn.getTileEntity(pos);
//		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//		worldIn.removeTileEntity(pos);
//		ItemStackHandler handlerStack = new ItemStackHandler(1);
//		handlerStack.setStackInSlot(0, handler.getStackInSlot(0));
//		ItemStack stack = new ItemStack(this);
//		if(handlerStack.getStackInSlot(0).getItem() != Item.getItemFromBlock(Blocks.AIR))
//		{
//			NBTTagCompound nbttagcompound = new NBTTagCompound();
//	        nbttagcompound.setTag("ItemStackHandler", handlerStack.serializeNBT());
//	        stack.setTagCompound(nbttagcompound);
//	        stack.setStackDisplayName("§r" + getLocalizedName() + " (" + I18n.translateToLocal(handlerStack.getStackInSlot(0).getItem().getUnlocalizedName() + ".name") + ")");
//		}
//		if(!creativeBreakMap.get(pos))
//			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack));
//		creativeBreakMap.remove(pos);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop)
			worldIn.setBlockToAir(pos.up());
		((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).killRitual();
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return blockState.getValue(LEVEL).intValue();
    }
	
	private TileEntityHereticCauldron getTile(World worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
	
	private TileEntityHereticCauldron getTile(IBlockAccess worldIn, BlockPos pos)
	{
		return ((TileEntityHereticCauldron)worldIn.getTileEntity(pos));
	}
	
	@Override
	protected boolean isBreakNBT() {
		return true;
	}
	
	@Override
	protected void addNBT(NBTTagCompound nbt, World worldIn, BlockPos pos) {
		nbt.setInteger("FluidValue", getTile(worldIn, pos).getFluid().getId());
		nbt.setInteger("FluidLevel", getTile(worldIn, pos).getLevel());
	}
	
	@Override
	protected String extraName(NBTTagCompound nbt, boolean isItem) {
		return EnumHereticCauldronFluidType.getFromId(nbt.getInteger("FluidValue")) == EnumHereticCauldronFluidType.NONE? "" : 
			(isItem? " & " : "") + new TextComponentTranslation("fluid." + EnumHereticCauldronFluidType.getFromId(nbt.getInteger("FluidValue")).getName()).getFormattedText();
	}
	
	@Override
	protected void readNBT(BaseTileEntityHarshenSingleItemInventory tileEntity, ItemStack stack)
	{
		((TileEntityHereticCauldron)tileEntity).setFluid(EnumHereticCauldronFluidType.getFromId(stack.serializeNBT().getCompoundTag("tag").getInteger("FluidValue")));
		((TileEntityHereticCauldron)tileEntity).setLevel(stack.serializeNBT().getCompoundTag("tag").getInteger("FluidLevel"));

	}
}
