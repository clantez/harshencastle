package kenijey.harshencastle.fluids.blocks;

import java.util.List;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class HarshenDimensionalFluidBlock extends BlockFluidClassic
{
    public HarshenDimensionalFluidBlock(Fluid fluid)
    {
        super(fluid, Material.WATER);
        this.setLightOpacity(3);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.onBlockAdded(worldIn, pos, state);
        this.checkForMixing(worldIn, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos neighborPos)
    {
    	super.neighborChanged(state, worldIn, pos, blockIn, neighborPos);
        this.checkForMixing(worldIn, pos, state);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    	List<Entity> playersWithin = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, new  AxisAlignedBB(pos, pos.add(1, 1, 1)));
		if(!playersWithin.isEmpty())
			for(Object entity: playersWithin.toArray())
			{
				if(!((EntityLivingBase)entity).isInWater())
					continue;
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(9), 250));
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(2), 250));
			}
				
    }
    
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state)
    {
        boolean flag = false;

        for (EnumFacing enumfacing : EnumFacing.values())
        {
        	if(worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockDirt ||
        			worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockGrass ||
        			worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockLeaves ||
        			(enumfacing != EnumFacing.DOWN && (
        					worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockDirt ||
                			worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockGrass ||
                			worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockLeaves)))
        			
        		worldIn.setBlockState(pos.offset(enumfacing).add(0, -1, 0), HarshenBlocks.harshen_dimensional_dirt.getDefaultState(), 3);
        		
            if (enumfacing != EnumFacing.DOWN && (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial().isLiquid() == true))
            {
            	if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this.getBlockState().getBlock())
            	{
	                flag = true;
	                break;
            	}
            }
        }

        if (flag)
        {
            Integer integer = (Integer)state.getValue(LEVEL);

            if (integer.intValue() == 0)
            {
                worldIn.setBlockState(pos, HarshenBlocks.harshen_dimensional_dirt.getDefaultState(), 3);
                return true;
            }

            if (integer.intValue() <= 4)
            {
                worldIn.setBlockState(pos, HarshenBlocks.harshen_dimensional_dirt.getDefaultState(), 3);
                return true;
            }
        }

        return false;
    }
    
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing)
    {
        return BlockFaceShape.UNDEFINED;
    }
}
