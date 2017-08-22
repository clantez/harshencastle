package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;

public enum EnumGlassContainer implements IStringSerializable
{
	EMPTY("empty", 0, (PotionEffect)null),
	VOID("void", 1, new PotionEffect(HarshenPotions.potionSoulless, 600)),
	REGEN("regen", 2, new PotionEffect(MobEffects.REGENERATION, 100, 200));
	
	private int meta;
	private String name;
	private PotionEffect[] effects;
	
	private EnumGlassContainer(String name, int meta, PotionEffect... effects)
	{
		this.name = name;
		this.meta = meta;
		this.effects = effects;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static EnumGlassContainer getContainerFromMeta(int meta)
	{
		for(EnumGlassContainer cycleEnu : EnumGlassContainer.values())
			if(cycleEnu.getMeta() == meta)
				return cycleEnu;
		return EnumGlassContainer.EMPTY;
	}
	
	public PotionEffect[] getEffects() 
	{
		PotionEffect[] returnEffects = new PotionEffect[effects.length];
		for(int i = 0; i < effects.length; i++)
			returnEffects[i] = new PotionEffect(effects[i].getPotion(), effects[i].getDuration(), effects[i].getAmplifier(), effects[i].getIsAmbient(), effects[i].doesShowParticles());
		return returnEffects;
	}
	
	public int getMeta()
	{
		return this.meta;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumGlassContainer l : EnumGlassContainer.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}
