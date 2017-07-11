package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.fluids.HarshenDimensionalFluid;
import kenijey.harshencastle.fluids.blocks.HarshenDimensionalFluidBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
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
        if (iblockstate.getBlock() instanceof HarshenDimensionalFluidBlock && ((Integer)iblockstate.getValue(HarshenDimensionalFluidBlock.LEVEL)).intValue() == 0)
        {
            filled_fluid = HarshenDimensionalFluid.instance;
            bucket = new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket);
        }
        if(filled_fluid == null || bucket == null)
        	return;
        event.setResult(Result.ALLOW);
        event.setFilledBucket(bucket);
        event.getWorld().setBlockToAir(blockpos);
    }
}
