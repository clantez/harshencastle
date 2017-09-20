package kenijey.harshencastle.config;

import java.util.HashMap;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseConfig;
import net.minecraft.item.Item;

public class AccessoryConfig extends BaseConfig
{
	
	public static String[] blackListedXrays;
	public static int enderPendantDistance;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() 
	{
		blackListedXrays = get("xray.blacklist", HarshenItems.xray_pendant, HarshenUtils.listOf("minecraft:stone"));
		enderPendantDistance = get("enderpendant.distance", HarshenItems.ender_pendant, 100);
	}

	@Override
	public void save() 
	{
		set("xray.blacklist", blackListedXrays);
		set("enderpendant.distance", enderPendantDistance);
	}
	
	private HashMap<String, String> keyMap = new HashMap<>();
	
	private <T> T get(String name, Item item, T normal) 
	{
		keyMap.put(name, item.getRegistryName().getResourcePath());
		return super.get(name, item.getRegistryName().getResourcePath(), normal);
	}
	
	@Override
	protected <T> void set(String name, T set) {
		super.set(name, keyMap.get(name), set);
	}

}
