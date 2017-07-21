package kenijey.harshencastle.entity.AI;

import java.util.Random;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class AIEntityFlyingTowardsPlayer extends EntityAIBase
	{
	    private final EntityLiving parentEntity;
	    private EntityLivingBase targetEntity;
		private double speed, x, y, z;

	    public AIEntityFlyingTowardsPlayer(EntityLiving living, double speed)
	    {
	    	this.speed = speed;
	        this.parentEntity = living;
	        this.setMutexBits(1);
	    }

	    /**
	     * Returns whether the EntityAIBase should begin execution.
	     */
	    public boolean shouldExecute()
	    {
	    	 this.targetEntity = this.parentEntity.getAttackTarget();
	         if (this.targetEntity == null)
	         {
	             return false;
	         }
	         else if (this.targetEntity.getDistanceSqToEntity(this.parentEntity) > 2500)
	         {
	             return false;
	         }
	         else
	         {
	             Vec3d vec3d = targetEntity.getForward();

	             if (vec3d == null)
	             {
	                 return false;
	             }
	             else
	             {
	                 this.x = vec3d.x;
	                 this.y = vec3d.y;
	                 this.z = vec3d.z;
	                 return true;
	             }
	         }
	    }

	    /**
	     * Returns whether an in-progress EntityAIBase should continue executing
	     */
	    public boolean shouldContinueExecuting()
	    {
	        return !this.parentEntity.getNavigator().noPath() && this.targetEntity.isEntityAlive() && this.targetEntity.getDistanceSqToEntity(this.parentEntity) < 2500;
	    }

	    /**
	     * Execute a one shot task or start executing a continuous task
	     */
	    public void startExecuting()
	    {
	    	this.parentEntity.getNavigator().tryMoveToEntityLiving(this.targetEntity, 3);
	    }
	    
	    @Override
	    public void resetTask() {
	    	this.targetEntity = null;
	    }
	}
