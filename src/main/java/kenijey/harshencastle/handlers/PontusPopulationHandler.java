package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.blocks.PontusDeadWood;
import kenijey.harshencastle.dimensions.pontus.PontusChunkProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PontusPopulationHandler 
{

	
	@SubscribeEvent
	public void pop(PopulateChunkEvent.Post event)
	{
		if(!(event.getGenerator() instanceof PontusChunkProvider))
			return;
		
		new Thread("Pontus World Generator")
		{
			@Override
			public void run() {
				World world = event.getWorld();
				System.out.println(world.getBlockState(new BlockPos(-45, 63, 409)));
				int chunkX = event.getChunkX() * 16;
				int chunkZ = event.getChunkZ() * 16;
				for(int x = 0; x < 16; x ++)
					for(int z = 0; z < 16; z ++)
						for(int y = 54; y < 125; y ++)
						{
							BlockPos pos = new BlockPos(chunkX + x, y, chunkZ + z);
							IBlockState state = world.getBlockState(pos);
							Block block = state.getBlock();
							if(block instanceof BlockLog)
								world.setBlockState(pos, HarshenBlocks.pontus_dead_wood.getStateFromMeta(getMetaFromStateLog(state)));	
							if(block instanceof BlockTallGrass)
								world.setBlockToAir(pos);
						}
			}
		}.start();
		
	}
	
	private void setBlockState(BlockPos pos, IBlockState state)
	{
		
	}
	
	private int getMetaFromStateLog(IBlockState state)
    {
        switch ((BlockLog.EnumAxis)state.getValue(BlockLog.LOG_AXIS))
        {
            case X: return 0b0100;
            case Y: return 0b0000;
            case Z: return 0b1000;
            case NONE: return 0b1100;
        }
        return 0;
    }
	
	
}
