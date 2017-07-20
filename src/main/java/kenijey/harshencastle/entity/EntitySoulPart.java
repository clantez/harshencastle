package kenijey.harshencastle.entity;

import kenijey.harshencastle.entityrender.RenderSoulPart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWaterFlying;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySoulPart extends EntityMob
{

	public EntitySoulPart(World worldIn) {
		super(worldIn);
		setNoGravity(true);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWanderAvoidWaterFlying(this, 1));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityVex.class}));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}
	
	@Override
    protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VEX_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return SoundEvents.ENTITY_VEX_DEATH;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return SoundEvents.ENTITY_VEX_HURT;
    }
    
    public static Factory FACTORY = new Factory();
    
    public static int id()
    {
    	return 183;
    }
    
    public static class Factory implements IRenderFactory<EntitySoulPart> 
    {

        @Override
        public Render<? super EntitySoulPart> createRenderFor(RenderManager manager) {
          return new RenderSoulPart(manager);
        }
    }
}
