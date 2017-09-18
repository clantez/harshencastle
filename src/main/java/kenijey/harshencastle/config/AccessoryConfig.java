package kenijey.harshencastle.config;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseConfig;

public class AccessoryConfig extends BaseConfig
{
	
	public static String[] blackListedXrays;

	@Override
	public String getName() {
		return "Accessories";
	}

	@Override
	public void read() {
		blackListedXrays = get("xray.blacklist", HarshenUtils.listOf("minecraft:stone"));
	}

	@Override
	public void save() {
		set("xray.blacklist", blackListedXrays);
		System.out.println(blackListedXrays);
	}

}
