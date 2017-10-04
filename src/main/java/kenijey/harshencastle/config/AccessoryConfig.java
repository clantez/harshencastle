package kenijey.harshencastle.config;

import java.util.HashMap;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseConfig;
import net.minecraft.item.Item;

public class AccessoryConfig extends BaseConfig
{
	
	public static String[] blackListedXrays;
	public static int xrayAreaX, xrayAreaY, xrayAreaZ, xrayListSize;
	public static int enderPendantDistance;
	public static double reachPendantLength;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() 
	{
		blackListedXrays = get("xray.blacklist", HarshenItems.XRAY_PENDANT, HarshenUtils.listOf("minecraft:stone"));
		xrayAreaX = get("xray.distance.x", HarshenItems.XRAY_PENDANT, 20);
		xrayAreaY = get("xray.distance.y", HarshenItems.XRAY_PENDANT, 20);
		xrayAreaZ = get("xray.distance.z", HarshenItems.XRAY_PENDANT, 20);
		xrayListSize = get("xray.listsize", HarshenItems.XRAY_PENDANT, 50); 
		enderPendantDistance = get("enderpendant.distance", HarshenItems.ENDER_PENDANT, 100);
		reachPendantLength = get("reach.length", HarshenItems.REACH_PENDANT, 12.5d);
	}
	
	private HashMap<String, String> keyMap = new HashMap<>();
	
	private <T> T get(String name, Item item, T normal) 
	{
		return super.get(name, item.getRegistryName().getResourcePath(), normal);
	}

}
