package kenijey.harshencastle.entity;

import com.google.common.base.Predicate;

import kenijey.harshencastle.entity.AI.AIEntityFlyingTowardsPlayer;
import kenijey.harshencastle.entity.damagesource.DamageSourceSoulPart;
import kenijey.harshencastle.entity.movehelper.MoveHelperSoulPart;
import kenijey.harshencastle.entityrender.RenderSoulPart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySoulPart extends EntityMob
{
	
	private int cooldownTicks = 0;
	
	public EntitySoulPart(World worldIn) {
		super(worldIn);
		this.isImmuneToFire = true;
        this.moveHelper = new MoveHelperSoulPart(this);
        this.experienceValue = 3;
        
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(8, new AIEntityFlyingTowardsPlayer(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(11, new EntityAILookIdle(this));

        
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false));

	}
	
	public void applyEntityAttributes()
	{
	        super.applyEntityAttributes();
	        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
	        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(1000D);
	}  
	
	public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
        this.setNoGravity(true);
        if(this.getAttackTarget() != null && this.getPosition().distanceSq(this.getAttackTarget().getPosition()) < 10)
        	this.getAttackTarget().attackEntityFrom(DamageSourceSoulPart.getSource(this),  4f);
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
    
    public static class Factory implements IRenderFactory<EntitySoulPart> 
    {

        @Override
        public Render<? super EntitySoulPart> createRenderFor(RenderManager manager) {
          return new RenderSoulPart(manager);
        }
    }
}
