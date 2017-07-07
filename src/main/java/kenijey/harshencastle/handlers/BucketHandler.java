package kenijey.harshencastle.handlers;

import kenijey.harshencastle.fluids.DimensionalFluid;
import kenijey.harshencastle.fluids.blocks.BlockDimensionalFluid;
import kenijey.harshencastle.items.HarshenItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketHandler 
{
	@SubscribeEvent
	public void onRightClickHoldingBucket(FillBucketEvent event)
    {

        if (event.getEmptyBucket().getItem() != Items.BUCKET)
        	return;
        if (event.getTarget() == null || event.getTarget().typeOfHit != RayTraceResult.Type.BLOCK)
        	return;
        BlockPos blockpos = event.getTarget().getBlockPos();
        if (!event.getWorld().isBlockModifiable(event.getEntityPlayer(), blockpos)) 
        	return;
        if (!event.getEntityPlayer().canPlayerEdit(blockpos.offset(event.getTarget().sideHit), event.getTarget().sideHit, event.getEmptyBucket())) 
        	return;
        
        IBlockState iblockstate = event.getWorld().getBlockState(blockpos);
        Fluid filled_fluid = null;
        ItemStack bucket = null;
        if (iblockstate.getBlock() instanceof BlockDimensionalFluid && ((Integer)iblockstate.getValue(BlockDimensionalFluid.LEVEL)).intValue() == 0)
        {
            filled_fluid = DimensionalFluid.instance;
            bucket = new ItemStack(HarshenItems.bucket_dimensional_liquid);
        }
        if(filled_fluid == null || bucket == null)
        	return;
        event.setResult(Result.ALLOW);
        event.setFilledBucket(bucket);
        event.getWorld().setBlockToAir(blockpos);
    }
}
