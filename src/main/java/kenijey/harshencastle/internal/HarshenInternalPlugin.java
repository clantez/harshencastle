package kenijey.harshencastle.internal;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.HarshenPlugin;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenPlugin;
import kenijey.harshencastle.api.IHarshenRegistry;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

@HarshenPlugin
public class HarshenInternalPlugin implements IHarshenPlugin {

	@Override
	public void register(IHarshenRegistry registry) 
	{
		registry.registerMagicTableRecipe(HarshenUtils.getMixupBook(), new HarshenStack(EnumGlassContainer.DIAMOND.getStack()), new HarshenStack(EnumGlassContainer.LAVA.getStack()), new HarshenStack(new ItemStack(Items.BOOK), new ItemStack(HarshenBlocks.ARCHIVE)), new HarshenStack(EnumGlassContainer.MAGIC.getStack()));
	}

}
