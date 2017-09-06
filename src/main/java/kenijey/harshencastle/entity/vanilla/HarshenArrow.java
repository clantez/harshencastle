package kenijey.harshencastle.entity.vanilla;

import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class HarshenArrow extends EntityTippedArrow
{

	private boolean isRipper;
	
	public HarshenArrow(EntityArrow arrow, boolean isRipper) {
		super(arrow.world, (EntityLivingBase) arrow.shootingEntity);
		this.isRipper = isRipper;
		setPotionEffect(new ItemStack(Items.ARROW));
	}

	private float chanceThatArrowBreaks = 0.3f;
	
	@Override
	protected ItemStack getArrowStack() {
		if(isRipper)
			return ItemStack.EMPTY;
		return new ItemStack(new Random().nextFloat() < chanceThatArrowBreaks ? HarshenItems.broken_arrow : Items.ARROW);
	}
	
	
	@Override
	public void playSound(SoundEvent soundIn, float volume, float pitch) {
		if(soundIn == SoundEvents.ENTITY_ARROW_HIT && playCustomSound)	
			soundIn = HarshenSounds.customArrowHit;
		playCustomSound = false;
		super.playSound(soundIn, volume, pitch);
	}
	
	private boolean playCustomSound;
	
	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		if(raytraceResultIn.entityHit != null || world.getTileEntity(raytraceResultIn.getBlockPos()) != null)
			playCustomSound = true;
		super.onHit(raytraceResultIn);
	}
	
	@Override
	protected void arrowHit(EntityLivingBase living) {
		if(isRipper
				&&!(Lists.newArrayList(living.getArmorInventoryList().iterator()).get(3).getItem() == HarshenArmors.harshen_jaguar_armor_helmet
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(2).getItem() == HarshenArmors.harshen_jaguar_armor_chestplate
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(1).getItem() == HarshenArmors.harshen_jaguar_armor_leggings
				&& Lists.newArrayList(living.getArmorInventoryList().iterator()).get(0).getItem() == HarshenArmors.harshen_jaguar_armor_boots))
			living.addPotionEffect(new PotionEffect(MobEffects.WITHER, 150, 1));
			
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
	}
	
}
