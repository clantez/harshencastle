package kenijey.harshencastle.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenPotions 
{
	
	public static Potion potionSoulless;
	public static Potion potionHarshed;
	
	public static void preInit()
	{
		potionSoulless = new PotionSoulless();
		potionHarshed = new PotionHarshed();
	}
	
	public static void register()
	{
		reg(potionSoulless);
		reg(potionHarshed);
	}
	
	private static void reg(Potion potion)
	{
		ForgeRegistries.POTIONS.register(potion);
	}
}
