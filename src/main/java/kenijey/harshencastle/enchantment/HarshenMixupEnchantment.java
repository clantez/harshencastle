package kenijey.harshencastle.enchantment;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.enchantment.Enchantment;

public class HarshenMixupEnchantment extends Enchantment
{

	public HarshenMixupEnchantment() {
		super(Enchantment.Rarity.VERY_RARE, HarshenUtils.HARSHEN_ITEMS_ONLY, HarshenUtils.listOf());
		setRegistryName(HarshenCastle.MODID, "mixup"); 
		setName("mixup");
	}
}
