package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseHarshenBow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class IronBow extends BaseHarshenBow
{
	public IronBow()
	{
		setUnlocalizedName("iron_bow");
		setRegistryName("iron_bow");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@Override
	public int getMaxDamage() {
		return 1388;
	}

	@Override
	public double additionDamage() {
		return 1.5D;
	}
}
