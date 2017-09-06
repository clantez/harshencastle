package kenijey.harshencastle.config;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.base.BaseConfig;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.config.Property;

public class ItemsEnabled extends BaseConfig
{
	
	public static ArrayList<Item> allItems = new ArrayList<>();
	private static HashMap<Item, Property> propertyMap = new HashMap<>();
	private static HashMap<Item, Boolean> enabledMap = new HashMap<>();

	
	private static final String CATEGORY_ITEM = "Items enables";

	@Override
	public String getName() {
		return "Items Enabled";
	}

	@Override
	public void read() {
		for(Item item: allItems)
		{
			String itemPath = item.getRegistryName().getResourcePath();
			Property property = config.get(CATEGORY_ITEM, itemPath, true);
			property.setComment(new TextComponentTranslation("config.isEnabled", new TextComponentTranslation(item.getUnlocalizedName() + ".name").getUnformattedText()).getUnformattedText());
			propertyMap.put(item, property);
			enabledMap.put(item, property.getBoolean());
		}
	}

	@Override
	public void save() {
		for(Item item : allItems)
			propertyMap.get(item).set(enabledMap.get(item));
	}
	
	public static boolean isItemEnabled(Item item)
	{
		if(!enabledMap.containsKey(item))
			return true;
		return enabledMap.get(item);
	}

}
