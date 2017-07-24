package kenijey.harshencastle.entity.AI;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AIEntityFlyingTowardsPlayer extends EntityAIBase
{
	private EntityLiving entity;
    public AIEntityFlyingTowardsPlayer(EntityLiving entity)
    {
    	this.entity = entity;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return !entity.getMoveHelper().isUpdating() && entity.getRNG().nextInt(7) == 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return false;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        BlockPos blockpos = new BlockPos(entity);
        for (int i = 0; i < 3; ++i)
        {
        	boolean flag = false;
        	BlockPos blockpos1 = BlockPos.ORIGIN;
        	while(!flag)
        	{
        		flag = true;
                blockpos1 = blockpos.add(entity.getRNG().nextInt(15) - 7, entity.getRNG().nextInt(11) - 5, entity.getRNG().nextInt(15) - 7);
                if(entity.getAttackTarget() == null || blockpos1.distanceSq(entity.getAttackTarget().getPosition()) < 625 || entity.getAttackTarget().getDistanceSq(blockpos) > 600)
                	flag = true;
        	}

            if (entity.world.isAirBlock(blockpos1))
            {
            	entity.getMoveHelper().setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 1D);
                if (entity.getAttackTarget() == null)
                {
                	entity.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                }

                break;
            }
        }
    }
}