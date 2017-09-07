package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.enums.blocks.EnumHereticCauldronFluidType;
import kenijey.harshencastle.interfaces.IIDSet;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;

public enum EnumGlassContainer implements IStringSerializable, IIDSet
{
	EMPTY("empty", -1, (PotionEffect)null),
	VOID("void", 0, new PotionEffect(HarshenPotions.potionSoulless, 600)),
	REGEN("regen", 0xF40D09, new PotionEffect(MobEffects.REGENERATION, 100, 200)),
	CURE("cure", 0xEFEDA2, new PotionEffect(HarshenPotions.potionPure, 1)),
	
	HARSHING_WATER(EnumHereticCauldronFluidType.HARSHING_WATER, 0x613A63),
	HARSHEN_DIMENSIONAL_FLUID(EnumHereticCauldronFluidType.HARSHEN_DIMENSIONAL_FLUID, 0x324B64),
	BLOOD(EnumHereticCauldronFluidType.BLOOD, 0x870705),
	LAVA(EnumHereticCauldronFluidType.LAVA, 0xD96415),
	MILK(EnumHereticCauldronFluidType.MILK, -1);
		
	private int meta;
	private String name;
	private PotionEffect[] effects;
	private EnumHereticCauldronFluidType type;
	private boolean isSubContainer = false;
	public int color;
	
	private EnumGlassContainer(EnumHereticCauldronFluidType type, int color)
	{
		this.color = color;
		this.type = type;
		this.name = "containing." + type.getName();
		this.effects = null;
		isSubContainer = true;
	}
	
	private EnumGlassContainer(String name, int color, PotionEffect... effects)
	{
		this.color = color;
		this.name = name;
		this.effects = effects;
	}
	
	@Override
	public void setId(int meta) {
		this.meta = meta;
	}
	
	public boolean isSubContainer() {
		return isSubContainer;
	}
	
	 public EnumHereticCauldronFluidType getType() {
		return type;
	}
	 
	public ItemStack getStack()
	{
		return new ItemStack(HarshenItems.glass_container, 1, this.meta);
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
	
	public static EnumGlassContainer getContainerFromType(EnumHereticCauldronFluidType type)
	{
		for(EnumGlassContainer cycleEnu : EnumGlassContainer.values())
			if(cycleEnu.getType() == type)
				return cycleEnu;
		return EnumGlassContainer.EMPTY;
	}
	
	public PotionEffect[] getEffects() 
	{
		if(effects == null || effects[0] == null)
			return null;
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
