package kenijey.harshencastle.entity;

import java.util.Random;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.entity.AI.AIEntityWanderNoWater;
import kenijey.harshencastle.entityrender.RenderSoullessKnight;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EntitySoullessKnight extends EntityMob
{

	public EntitySoullessKnight(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIAttackMelee(this, 1d, true));
		this.tasks.addTask(1, new EntityAIAvoidEntity(this, EntityEnderman.class, 50f, 1d, 2d));
		this.tasks.addTask(0, new AIEntityWanderNoWater(this, 1d, 0));
		this.tasks.addTask(0, new EntityAIMoveTowardsTarget(this, 1d, 100));
		
		this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
		
		this.experienceValue = 50;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4d);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(2d);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6d);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3d);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		setItemStackToSlot(this.isLeftHanded() ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND, new ItemStack(HarshenItems.props, 1, 0));
		setItemStackToSlot(this.isLeftHanded() ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, new ItemStack(Items.SHIELD));
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
        this.setLeftHanded(false);
		return livingdata;
	}
	
    
    
    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
    	for(int i = 0; i < lootingModifier + 1; i ++)
    		if(new Random().nextBoolean())
    			this.dropItem(HarshenItems.blood_essence, 1);
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
    	return null;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
    	return null;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
    	return null;
    }
    
    public static Factory FACTORY = new Factory();
    
    public static int id()
    {
    	return 182;
    }
    
    public static class Factory implements IRenderFactory<EntitySoullessKnight> 
    {

        @Override
        public Render<? super EntitySoullessKnight> createRenderFor(RenderManager manager) {
          return new RenderSoullessKnight(manager);
        }
    }

}
