package kenijey.harshencastle.blocks;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType.EnumLiquid;
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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HereticCauldron extends BaseBlockHarshenSingleInventory
{
	
	public static final PropertyEnum<EnumLiquid> LIQUID =  PropertyEnum.<EnumLiquid>create("liquid_type", EnumLiquid.class);
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 3);
	
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
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }    
	 
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	public HereticCauldron() {
		super(Material.IRON);
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIQUID, EnumLiquid.NONE).withProperty(LEVEL, 0));
	}
	
	@Override
	public TileEntity getTile() {
		return new TileEntityHereticCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        int i = ((Integer)state.getValue(LEVEL)).intValue();
        if (itemstack.isEmpty())
        	return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);
        if(item instanceof UniversalBucket)
        {
                 String[] stList = itemstack.serializeNBT().getTag("tag").toString().split(":");
                 int occurance = 0;
                 String s = "";
                 for(char c : stList[1].toCharArray())
                 {
                 	if(c == '"'){
                 		occurance++; continue;}
                 	if(occurance == 2)
                 		break;
                 	s += c;
                 }
                 if (Arrays.asList("harshen_dimensional_fluid harshing_water".split(" ")).contains(s))
                 {
                     if (i < 3 && !worldIn.isRemote)
                     {
                         if (!playerIn.capabilities.isCreativeMode)
                         {
                             playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                         }

                         this.setWaterLevel(worldIn, pos, state, 3);
                         worldIn.setBlockState(pos, state.withProperty(LIQUID, EnumLiquid.getMatch(s)).withProperty(LEVEL, 3), 2);
                         worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                     }
                 }
                 return true;
        	}
            else if (item == Items.BUCKET)
            {
                if (i == 3 && !worldIn.isRemote)
                {
                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            playerIn.setHeldItem(hand, FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid(state.getValue(LIQUID).getName()), 1000)));
                        }
                        else if (!playerIn.inventory.addItemStackToInventory(FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid(state.getValue(LIQUID).getName()), 1000))))
                        {
                            playerIn.dropItem(FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid(state.getValue(LIQUID).getName()), 1000)), false);
                        }
                    }
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.setBlockState(pos, state.withProperty(LIQUID, EnumLiquid.NONE).withProperty(LEVEL, 0), 2);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            }
            else
            	return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);
    }
	
	private void setWaterLevel(World worldIn, BlockPos pos, IBlockState state, int level)
    {
        worldIn.setBlockState(pos, state.withProperty(LEVEL, Integer.valueOf(MathHelper.clamp(level, 0, 3))), 2);
        worldIn.updateComparatorOutputLevel(pos, this);
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if(state.getValue(LIQUID) == EnumLiquid.NONE || state.getValue(LEVEL) == 0)
			return 0;
		return (state.getValue(LIQUID).getId() * 3) - 3 + ((Integer)state.getValue(LEVEL)).intValue();
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0)
			return this.getDefaultState();
		return this.getDefaultState().withProperty(LEVEL, ((meta - 1) % 3) + 1).withProperty(LIQUID, EnumLiquid.getMatch((int) Math.ceil(meta / 3)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LIQUID, LEVEL});
	}
}
