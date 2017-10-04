package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.interfaces.IIDSet;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumGlassContainer implements IStringSerializable, IIDSet
{
	EMPTY("empty", -1, (PotionEffect)null),
	VOID("void", 0, new PotionEffect(HarshenPotions.potionSoulless, 600)),
	REGEN("regen", 0xF40D09, new PotionEffect(MobEffects.REGENERATION, 100, 200)),
	CURE("cure", 0xEFEDA2, new PotionEffect(HarshenPotions.potionPure, 1)),
	
	HARSHING_WATER("harshing_water", HarshenFluids.harshing_water, 0x613A63),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", HarshenFluids.harshen_dimensional_fluid, 0x324B64),
	BLOOD("blood", 0x870705),
	LAVA("lava", Blocks.LAVA, 0xD96415),
	MILK("milk", -1),
	WATER("water", Blocks.WATER, 0x598fe5),
	EARTH("earth", Blocks.DIRT, 0xc6854d),
	SAND("sand", Blocks.SAND, 0xf4cf60),
	COAL("coal", Blocks.COAL_BLOCK, 0x605a5a),
	DIAMOND("diamond", Blocks.DIAMOND_BLOCK, 0x67dbc7);
		
	private int meta;
	private String name;
	private PotionEffect[] effects;
	private CauldronLiquid type;
	private boolean isSubContainer = false;
	private boolean removePaste = false;
	public int color;
	
	private EnumGlassContainer(CauldronLiquid type, int color)
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
	
	private EnumGlassContainer(String name, int color) 
	{
		this(new CauldronLiquid(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png")), color);
	}
	
	private EnumGlassContainer(String name, int color, boolean action) 
	{
		this(new CauldronLiquid(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png")), color);
		removePaste = action;
	}
	
	private EnumGlassContainer(String name, Block block, int color, boolean action) 
	{
		this(new CauldronLiquid(name, block.getDefaultState()), color);
		removePaste = action;
	}
	
	private EnumGlassContainer(String name, Block block, int color) 
	{
		this(new CauldronLiquid(name, block.getDefaultState()), color);
	}
	
	private EnumGlassContainer(String name, Fluid fluid, int color) 
	{
		this(new CauldronLiquid(name, fluid.getBlock().getDefaultState()), color);
	}
	
	@Override
	public void setId(int meta) {
		this.meta = meta;
	}
	
	public boolean isSubContainer() {
		return isSubContainer;
	}
	
	public CauldronLiquid getType() {
		return type;
	}
	 
	public ItemStack getStack()
	{
		return new ItemStack(HarshenItems.GLASS_CONTAINER, 1, this.meta);
	}

	@Override
	public String getName() {
		return name;
	}
	
	public boolean isPaste() {
		return !removePaste;
	}
	
	public static EnumGlassContainer getContainerFromMeta(int meta)
	{
		for(EnumGlassContainer cycleEnu : EnumGlassContainer.values())
			if(cycleEnu.getMeta() == meta)
				return cycleEnu;
		return EnumGlassContainer.EMPTY;
	}
	
	public static CauldronLiquid getTypeFromMeta(int meta)
	{
		return getContainerFromMeta(meta).getType();
	}
	
	public static int getMetaFromType(CauldronLiquid type)
	{
		return getContainerFromType(type).meta;
	}
	
	public static EnumGlassContainer getContainerFromType(CauldronLiquid type)
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
