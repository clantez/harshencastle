package kenijey.harshencastle.base;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public abstract class BaseItemCustomInvEffect extends Item
{
	
	public PotionEffect effect()
	{
		return new PotionEffect(getPotion(), 1, 0, false, false);
	}
	
	public abstract Potion getPotion();
}
