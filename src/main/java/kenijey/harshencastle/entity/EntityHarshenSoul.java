package kenijey.harshencastle.entity;

import kenijey.harshencastle.interfaces.IBurnInDay;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityHarshenSoul extends EntityMob implements IBurnInDay {

	public EntityHarshenSoul(World worldIn) {
		super(worldIn);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackMelee(this, 1d, true));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEnderman.class, 10f, 1d, 1.2d));
		this.tasks.addTask(0, new EntityAIWanderAvoidWater(this, 0.35));
		this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this, 0.5d, 100));
		
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
}
