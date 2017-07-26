package kenijey.harshencastle.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenPotions 
{
	
	public static Potion effectSoulless;
	
	public static void preInit()
	{
		effectSoulless = new PotionSoulless();
	}
	
	public static void register()
	{
		reg(effectSoulless);
	}
	
	private static void reg(Potion potion)
	{
		ForgeRegistries.POTIONS.register(potion);
	}
}
