package kenijey.harshencastle.handlers;

import kenijey.harshencastle.blocks.HarshenBlocks;
import kenijey.harshencastle.items.HarshenItems;

public class RegistryHandler 
{
	public static void Client()
	{
		HarshenBlocks.regRenders();
		HarshenItems.regRenders();
	}
	
	public static void PreInitCommon()
	{
		HarshenBlocks.preInit();
		HarshenBlocks.reg();
		HarshenItems.preInit();
		HarshenItems.reg();
	}

}
