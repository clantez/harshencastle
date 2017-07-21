package kenijey.harshencastle.entity;

import kenijey.harshencastle.entity.AI.AIEntityFlyRandomly;
import kenijey.harshencastle.entity.AI.AIEntityFlyingTowardsPlayer;
import kenijey.harshencastle.entity.movehelper.MoveHelperSoulPart;
import kenijey.harshencastle.entity.pathnavigator.PathNavigateorSoulPart;
import kenijey.harshencastle.entityrender.RenderSoulPart;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySoulPart extends EntityFlying
{

	public EntitySoulPart(World worldIn) {
		super(worldIn);
		this.navigator = new PathNavigateorSoulPart(this, worldIn);
		this.moveHelper = new MoveHelperSoulPart(this);
	}
	
	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new AIEntityFlyingTowardsPlayer(this, 2));
		this.tasks.addTask(1, new AIEntityFlyRandomly(this));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	
        this.targetTasks.addTask(1, new EntityAIFindEntityNearestPlayer(this));
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
