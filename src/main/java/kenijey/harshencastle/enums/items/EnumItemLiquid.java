package kenijey.harshencastle.enums.items;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.enums.blocks.CauldronLiquid;
import kenijey.harshencastle.interfaces.IIDSet;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public enum EnumItemLiquid implements IStringSerializable, IIDSet
{
	Coalite("coalite", EnumGlassContainer.COAL.getType()),
	Diamondite("diamondite", EnumGlassContainer.DIAMOND.getType());
	
	private int meta;
	private String name;
	private final CauldronLiquid fluid;
	
	private EnumItemLiquid(String name, CauldronLiquid fluid)
	{
		this.name = name;
		this.fluid = fluid;
	}
	
	public static EnumItemLiquid getFromMeta(int meta)
	{
		for(EnumItemLiquid stick : EnumItemLiquid.values())
			if(stick.meta == meta)
				return stick;
		return null;
	}
	
	public CauldronLiquid getFluid() {
		return fluid;
	}

	@Override
	public String getName() {
		return name;
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
		for(EnumItemLiquid l : EnumItemLiquid.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
	
	public ItemStack getStack()
	{
		return new ItemStack(HarshenItems.item_liquid, 1, meta);
	}

	@Override
	public void setId(int id) {
		this.meta = id;
	}
}
