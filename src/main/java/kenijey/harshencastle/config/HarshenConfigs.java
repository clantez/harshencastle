package kenijey.harshencastle.config;

public class HarshenConfigs 
{
	public static final ItemsEnabled ITEM = new ItemsEnabled();
	public static final BlocksEnabled BLOCK = new BlocksEnabled();
	public static final GeneralConfig GENERAL = new GeneralConfig();
	public static final IdConfig ID = new IdConfig();
	
	public static void preInit()
	{
		ITEM.preInit();
		BLOCK.preInit();
		GENERAL.preInit();
		ID.preInit();
	}
		
}
