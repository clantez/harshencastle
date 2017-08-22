package kenijey.harshencastle.blocks;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.recipies.CauldronRecipes;
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
import scala.reflect.reify.phases.Calculate;

public class HereticCauldron extends BaseBlockHarshenSingleInventory
{
	
	public static final PropertyEnum<EnumHetericCauldronFluidType> LIQUID =  PropertyEnum.<EnumHetericCauldronFluidType>create("liquid_type", EnumHetericCauldronFluidType.class);
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
        return new AxisAlignedBB(0, 0, 0, 1, 1.001, 1);
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
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIQUID, EnumHetericCauldronFluidType.NONE).withProperty(LEVEL, 0));
	}
	
	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHereticCauldron();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        int i = ((Integer)state.getValue(LEVEL)).intValue();
        if(((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).isActive)
        	return true;
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
                         setState(worldIn, pos, state.withProperty(LIQUID, EnumHetericCauldronFluidType.getMatch(s)).withProperty(LEVEL, 3));
                         worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                     }
                 }
                 return true;
        	}
            else if (item == Items.BUCKET && state.getValue(LIQUID) != EnumHetericCauldronFluidType.BLOOD)
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
                    setState(worldIn, pos, state.withProperty(LIQUID, EnumHetericCauldronFluidType.NONE).withProperty(LEVEL, 0));
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            }
            else if(item instanceof BloodCollector && (state.getValue(LIQUID) ==  EnumHetericCauldronFluidType.BLOOD || i == 0))
            {
            	if(i != 3 && !playerIn.isSneaking() && !worldIn.isRemote)
            		if (playerIn.capabilities.isCreativeMode || (!playerIn.capabilities.isCreativeMode && ((BloodCollector)item).remove(playerIn, hand, 3)))
                    {
            			worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        				setState(worldIn, pos, this.blockState.getBaseState().withProperty(LIQUID, EnumHetericCauldronFluidType.BLOOD).withProperty(LEVEL, i + 1));

                    }
            	return true;
            }
            else if(item == HarshenItems.ladle && i == 3)
            {
            	ItemStack stack = ((BaseTileEntityHarshenSingleItemInventory) worldIn.getTileEntity(pos)).getItem();
            	if(CauldronRecipes.getRecipe(stack) != null && CauldronRecipes.getRecipe(stack).getCatalyst() == state.getValue(LIQUID))
                {
                	((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).isActive = true;
                	((TileEntityHereticCauldron)worldIn.getTileEntity(pos)).setSwitchedItem(CauldronRecipes.getRecipe(stack).getOutput());
                	return true;
                }
    		}		
    		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitZ, hitZ, hitZ);

    }
	
	
	private void setState(World world, BlockPos pos, IBlockState state)
	{
		ItemStack stack = ((TileEntityHereticCauldron)world.getTileEntity(pos)).getItem();
		world.setBlockState(pos, state, 3);
		((TileEntityHereticCauldron)world.getTileEntity(pos)).setItem(stack);
        world.updateComparatorOutputLevel(pos, this);

	}
	
	public IBlockState removeLayer(IBlockState state)
	{ 
		return HarshenBlocks.heretic_cauldron.getDefaultState().withProperty(LEVEL, Integer.valueOf(MathHelper.clamp(state.getValue(LEVEL) - 1, 0, 3)))
				.withProperty(LIQUID, Integer.valueOf(MathHelper.clamp(state.getValue(LEVEL) - 1, 0, 3))!= 0 ? state.getValue(LIQUID) : EnumHetericCauldronFluidType.NONE);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if(state.getValue(LIQUID) == EnumHetericCauldronFluidType.NONE || state.getValue(LEVEL) == 0)
			return 0;
		return (state.getValue(LIQUID).getId() * 3) - 3 + ((Integer)state.getValue(LEVEL)).intValue();
	}
	
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0)
			return this.getDefaultState();
		return this.getDefaultState().withProperty(LEVEL, ((meta - 1) % 3) + 1).withProperty(LIQUID, EnumHetericCauldronFluidType.getMatch((int) Math.ceil(meta / 3)));
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
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(worldIn.getBlockState(pos.up()).getBlock() instanceof HereticCauldronTop)
			worldIn.setBlockToAir(pos.up());
	}
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
    {
        return true;
    }
	
	@Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return ((Integer)blockState.getValue(LEVEL)).intValue();
    }
}
