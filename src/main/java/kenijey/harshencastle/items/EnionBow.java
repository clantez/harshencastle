package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseHarshenBow;
import kenijey.harshencastle.enums.entities.EnumHarshenArrowTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EnionBow extends BaseHarshenBow
{
	public EnionBow()
	{
		super(EnumHarshenArrowTypes.NORMAL);
		setUnlocalizedName("enion_bow");
		setRegistryName("enion_bow");
	}

	@Override
	public int getMaxDamage() {
		return 1837;
	}

	@Override
	public double additionDamage() {
		return 2.0D;
	}
}
