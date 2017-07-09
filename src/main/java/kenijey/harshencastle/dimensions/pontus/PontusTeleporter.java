package kenijey.harshencastle.dimensions.pontus;

import java.util.Random;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.blocks.HarshenDimensionalGate;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class PontusTeleporter extends Teleporter{
	
	private final WorldServer worldServerInstance;
    private final Random random;
    private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap(4096);
	
	public PontusTeleporter(WorldServer worldIn) {
		super(worldIn);
		this.worldServerInstance = worldIn;
		this.random = new Random(worldIn.getSeed());
	}
	
	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw)
    {
        if (this.worldServerInstance.provider.getDimensionType().getId() != 1)
        {
            if (!this.placeInExistingPortal(entityIn, rotationYaw))
            {
                this.makePortal(entityIn);
                this.placeInExistingPortal(entityIn, rotationYaw);
            }
        }
        else
        {
            int i = MathHelper.floor(entityIn.posX);
            int j = MathHelper.floor(entityIn.posY) - 1;
            int k = MathHelper.floor(entityIn.posZ);

            entityIn.setLocationAndAngles((double)i, (double)j, (double)k, entityIn.rotationYaw, 0.0F);
            entityIn.motionX = 0.0D;
            entityIn.motionY = 0.0D;
            entityIn.motionZ = 0.0D;
        }
    }
	
	@Override
	public boolean placeInExistingPortal(Entity entityIn, float p_180620_2_) {
		boolean flag = true;
		double d0 = -1.0D;
		int i = MathHelper.floor(entityIn.posX);
		int j = MathHelper.floor(entityIn.posZ);
		boolean flag1 = true;
		BlockPos object = BlockPos.ORIGIN;
		long k = ChunkPos.asLong(i, j);

		if (this.destinationCoordinateCache.containsKey(k)) {
			Teleporter.PortalPosition portalposition = (Teleporter.PortalPosition) this.destinationCoordinateCache.get(k);
			d0 = 0.0D;
			object = portalposition;
			portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			flag1 = false;
		} else {
			BlockPos blockpos4 = new BlockPos(entityIn);

			for (int l = -128; l <= 128; ++l) {
				BlockPos blockpos1;

				for (int i1 = -128; i1 <= 128; ++i1) {
					for (BlockPos blockpos = blockpos4.add(l, this.worldServerInstance.getActualHeight() - 1 - blockpos4.getY(), i1); blockpos
							.getY() >= 0; blockpos = blockpos1) {
						blockpos1 = blockpos.down();

						if (this.worldServerInstance.getBlockState(blockpos).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
							while (this.worldServerInstance.getBlockState(blockpos1 = blockpos.down()).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
								blockpos = blockpos1;
							}

							double d1 = blockpos.distanceSq(blockpos4);

							if (d0 < 0.0D || d1 < d0) {
								d0 = d1;
								object = blockpos;
							}
						}
					}
				}
			}
		}

		if (d0 >= 0.0D) {
			if (flag1) {
				this.destinationCoordinateCache.put(k, new Teleporter.PortalPosition(object, this.worldServerInstance.getTotalWorldTime()));
			}

			double d4 = (double) ((BlockPos) object).getX() + 0.5D;
			double d5 = (double) ((BlockPos) object).getY() + 0.5D;
			double d6 = (double) ((BlockPos) object).getZ() + 0.5D;
			EnumFacing enumfacing = null;

			if (this.worldServerInstance.getBlockState(((BlockPos) object).west()).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
				enumfacing = EnumFacing.NORTH;
			}

			if (this.worldServerInstance.getBlockState(((BlockPos) object).east()).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
				enumfacing = EnumFacing.SOUTH;
			}

			if (this.worldServerInstance.getBlockState(((BlockPos) object).north()).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
				enumfacing = EnumFacing.EAST;
			}

			if (this.worldServerInstance.getBlockState(((BlockPos) object).south()).getBlock() == HarshenBlocks.harshen_dimensional_gate) {
				enumfacing = EnumFacing.WEST;
			}

			// func_181012_aH = getTeleportDirection
			// EnumFacing enumfacing1 =
			// EnumFacing.getHorizontal(entityIn.func_181012_aH());
			EnumFacing enumfacing1 = entityIn.getTeleportDirection();

			if (enumfacing != null) {
				EnumFacing enumfacing2 = enumfacing.rotateYCCW();
				BlockPos blockpos2 = ((BlockPos) object).offset(enumfacing);
				boolean flag2 = this.func_180265_a(blockpos2);
				boolean flag3 = this.func_180265_a(blockpos2.offset(enumfacing2));

				if (flag3 && flag2) {
					object = ((BlockPos) object).offset(enumfacing2);
					enumfacing = enumfacing.getOpposite();
					enumfacing2 = enumfacing2.getOpposite();
					BlockPos blockpos3 = ((BlockPos) object).offset(enumfacing);
					flag2 = this.func_180265_a(blockpos3);
					flag3 = this.func_180265_a(blockpos3.offset(enumfacing2));
				}

				float f6 = 0.5F;
				float f1 = 0.5F;

				if (!flag3 && flag2) {
					f6 = 1.0F;
				} else if (flag3 && !flag2) {
					f6 = 0.0F;
				} else if (flag3) {
					f1 = 0.0F;
				}

				d4 = (double) ((BlockPos) object).getX() + 0.5D;
				d5 = (double) ((BlockPos) object).getY() + 0.5D;
				d6 = (double) ((BlockPos) object).getZ() + 0.5D;
				d4 += (double) ((float) enumfacing2.getFrontOffsetX() * f6 + (float) enumfacing.getFrontOffsetX() * f1);
				d6 += (double) ((float) enumfacing2.getFrontOffsetZ() * f6 + (float) enumfacing.getFrontOffsetZ() * f1);
				float f2 = 0.0F;
				float f3 = 0.0F;
				float f4 = 0.0F;
				float f5 = 0.0F;

				if (enumfacing1 != null && enumfacing == enumfacing1) {
					f2 = 1.0F;
					f3 = 1.0F;
				} else if (enumfacing1 != null && enumfacing == enumfacing1.getOpposite()) {
					f2 = -1.0F;
					f3 = -1.0F;
				} else if (enumfacing1 != null && enumfacing == enumfacing1.rotateY()) {
					f4 = 1.0F;
					f5 = -1.0F;
				} else {
					f4 = -1.0F;
					f5 = 1.0F;
				}

				double d2 = entityIn.motionX;
				double d3 = entityIn.motionZ;
				entityIn.motionX = d2 * (double) f2 + d3 * (double) f5;
				entityIn.motionZ = d2 * (double) f4 + d3 * (double) f3;
				if (enumfacing1 != null)
					entityIn.rotationYaw = p_180620_2_ - (float) (enumfacing1.getHorizontalIndex() * 90)
							+ (float) (enumfacing.getHorizontalIndex() * 90);
			} else {
				entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0D;
			}

			entityIn.setLocationAndAngles(d4, d5, d6, entityIn.rotationYaw, entityIn.rotationPitch);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean makePortal(Entity entityIn)
    {
		int j = 0;
		for(int i = -3; i < 4; i ++)
			for(int k = -3; k < 4; k ++)
				for(int l = -3; l < -1; l++)
					this.worldServerInstance.setBlockState(entityIn.getPosition().add(i, l, k), HarshenBlocks.harshen_dimensional_dirt.getDefaultState(), 2);
		this.worldServerInstance.setBlockState(entityIn.getPosition().add(0, -2, 0), HarshenBlocks.harshen_dimensional_gate.getDefaultState().withProperty(HarshenDimensionalGate.ACTIVE, true), 2);
        return true;
    }
	
	private boolean func_180265_a(BlockPos pos) {
		return !this.worldServerInstance.isAirBlock(pos) || !this.worldServerInstance.isAirBlock(pos.up());
	}
	
	public void removeStalePortalLocations(long worldTime)
    {
        if (worldTime % 100L == 0L)
        {
            long i = worldTime - 300L;
            ObjectIterator<Teleporter.PortalPosition> objectiterator = this.destinationCoordinateCache.values().iterator();

            while (objectiterator.hasNext())
            {
                Teleporter.PortalPosition teleporter$portalposition = (Teleporter.PortalPosition)objectiterator.next();

                if (teleporter$portalposition == null || teleporter$portalposition.lastUpdateTime < i)
                {
                    objectiterator.remove();
                }
            }
        }
    }
	
	public class PortalPosition extends BlockPos
    {
        public long lastUpdateTime;

        public PortalPosition(BlockPos pos, long lastUpdate)
        {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.lastUpdateTime = lastUpdate;
        }
    }
}
