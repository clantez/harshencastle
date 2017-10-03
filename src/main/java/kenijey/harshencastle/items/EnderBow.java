package kenijey.harshencastle.items;

import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.base.BaseHarshenStaff;
import kenijey.harshencastle.entity.EntityThrown;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnderBow extends BaseHarshenStaff
{
	public EnderBow()
	{
		setRegistryName("ender_bow");
		setUnlocalizedName("ender_bow");
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() instanceof EnderBow? (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 15.0F : 0.0F;
                }
            }
        });
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow2").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow3").getFormattedText());
		tooltip.add("");
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow4").getFormattedText());
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {return stack;}
		
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		float f = ItemBow.getArrowVelocity(getMaxItemUseDuration(stack) - timeLeft);
		spawnThrownEntity(worldIn, entityLiving, 3f * f, 1, new EntityThrown.HitResult() {
			@Override
			public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) 
			{
				if(result.entityHit != null && result.entityHit != entity.getThrower())
				{
					entity.setDead();
					if(result.entityHit.attackEntityFrom(new EntityDamageSourceIndirect("ender_bow", entity, entity.getThrower() == null ? entity : entity.getThrower()).setProjectile(), f * 20f))
						entity.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (itemRand.nextFloat() * 0.2F + 0.9F));
				}
			}
		}).setIgnoreBlocks(true);
        worldIn.playSound((EntityPlayer)null, entityLiving.posX, entityLiving.posY, entityLiving.posZ, SoundEvents.ENTITY_ENDERMITE_AMBIENT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
		stack.damageItem(1, entityLiving);
	}
}
