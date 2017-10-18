package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.items.EnumRitualStick;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class HandlerItemColors 
{
	public static class ItemColorGlassContainer implements IItemColor
	{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return tintIndex == 1 ? -1 : EnumGlassContainer.getContainerFromMeta(stack.getMetadata()).color;
		}	
	}
	
	public static class ItemColorRitualStick implements IItemColor
	{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return EnumRitualStick.getColorFromMeta(stack.getMetadata());
		}
	}
	
	public static class ItemColorItemLiquid implements IItemColor
	{
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) {
			return ItemLiquidTypeset.getFromMeta(stack.getMetadata()) == null ? -1 : EnumGlassContainer.getContainerFromType(ItemLiquidTypeset.getFromMeta(stack.getMetadata()).getType()).color;
		}
		
	}
}
