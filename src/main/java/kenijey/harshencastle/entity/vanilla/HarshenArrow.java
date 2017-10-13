package kenijey.harshencastle.entity.vanilla;

import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.entities.EnumHarshenArrowTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;

public class HarshenArrow extends EntityTippedArrow
{

	private final EnumHarshenArrowTypes arrowType;
	
	public HarshenArrow(EntityArrow arrow, EnumHarshenArrowTypes arrowType) {
		super(arrow.world, (EntityLivingBase) arrow.shootingEntity);
		this.arrowType = arrowType;
		setPotionEffect(new ItemStack(Items.ARROW));
	}

	private float chanceThatArrowBreaks = 0.3f;
	
	@Override
	protected ItemStack getArrowStack() {
		if(arrowType == EnumHarshenArrowTypes.RIPPER)
			return ItemStack.EMPTY;
		return new ItemStack(new Random().nextFloat() < chanceThatArrowBreaks ? HarshenItems.BROKEN_ARROW : Items.ARROW);
	}
	
	
	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch) {
		if(soundIn == SoundEvents.ENTITY_ARROW_HIT && playCustomSound)	
			soundIn = HarshenSounds.CUSTOM_ARROW_HIT;
		playCustomSound = false;
		super.playSound(soundIn, volume, pitch);
	}
	
	private boolean playCustomSound;
	
	@Override
	protected void onHit(RayTraceResult ray) {
		if(ray.entityHit != null || world.getTileEntity(ray.getBlockPos()) != null)
			playCustomSound = true;
		super.onHit(ray);
	}
	
	@Override
	protected void arrowHit(EntityLivingBase living) {
		if(arrowType == EnumHarshenArrowTypes.RIPPER
				&&!(Lists.newArrayList(living.getArmorInventoryList().iterator()).get(3).getItem() == HarshenArmors.harshen_jaguar_armor_helmet
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(2).getItem() == HarshenArmors.harshen_jaguar_armor_chestplate
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(1).getItem() == HarshenArmors.harshen_jaguar_armor_leggings
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(0).getItem() == HarshenArmors.harshen_jaguar_armor_boots))
			living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 150, 1));
			
	}
}
