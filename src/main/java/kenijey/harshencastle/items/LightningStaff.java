package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseHarshenStaff;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightningStaff extends BaseHarshenStaff
{
	public LightningStaff()
	{
		setUnlocalizedName("lightning_staff");
		setRegistryName("lightning_staff");
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 5;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		System.out.println("ss");
		stack.damageItem(1, entityLiving);
		return stack;
	}
}
