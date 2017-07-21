package kenijey.harshencastle.base;

import kenijey.harshencastle.enums.items.EnumProp;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public abstract class BaseItemMetaData extends Item
{
	
	public BaseItemMetaData() {
		setHasSubtypes(true);
	}
	
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < getNames().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + getNames()[i];
		return this.getUnlocalizedName() + "." + getNames()[0];
	}
	
	protected abstract String[] getNames();
}
