package kenijey.harshencastle.config;

import java.util.HashMap;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseConfig;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3d;

public class AccessoryConfig extends BaseConfig
{
	
	public static String[] blackListedXrays;
	public static int xrayAreaX, xrayAreaY, xrayAreaZ, xrayListSize;
	public static int enderPendantDistance;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() 
	{
		blackListedXrays = get("xray.blacklist", HarshenItems.xray_pendant, HarshenUtils.listOf("minecraft:stone"));
		xrayAreaX = get("xray.distance.x", HarshenItems.xray_pendant, 20);
		xrayAreaY = get("xray.distance.y", HarshenItems.xray_pendant, 20);
		xrayAreaZ = get("xray.distance.z", HarshenItems.xray_pendant, 20);
		xrayListSize = get("xray.listsize", HarshenItems.xray_pendant, 50); 
		enderPendantDistance = get("enderpendant.distance", HarshenItems.ender_pendant, 100);
	}

	@Override
	public void save() 
	{
		set("xray.blacklist", blackListedXrays);
		set("enderpendant.distance", enderPendantDistance);
		set("xray.distance.x", xrayAreaX);
		set("xray.distance.y", xrayAreaY);
		set("xray.distance.z", xrayAreaZ);
		set("xray.listsize", xrayListSize);
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
