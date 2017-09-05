package kenijey.harshencastle.entity.vanilla;

import java.util.Random;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;

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
	public void onUpdate() {
		super.onUpdate();
	}
	
}
