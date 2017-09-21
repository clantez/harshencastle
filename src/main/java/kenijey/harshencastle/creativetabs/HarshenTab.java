package kenijey.harshencastle.creativetabs;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class HarshenTab extends CreativeTabs
{
	public HarshenTab()
	{
		super("harshenTab");
		this.setBackgroundImageName("harshen_tab_background.png");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(HarshenItems.pontus_world_gate_spawner);
	}

}
