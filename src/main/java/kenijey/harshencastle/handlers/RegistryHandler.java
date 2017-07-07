package kenijey.harshencastle.handlers;

import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.blocks.HarshenBlocks;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.items.HarshenItems;

public class RegistryHandler 
{
	public static void Client()
	{
		HarshenBlocks.regRenders();
		HarshenItems.regRenders();
		HarshenArmors.regRenders();
	}
	
	public static void PreInitCommon()
	{
		HarshenFluids.register();
		HarshenBlocks.preInit();
		HarshenBlocks.reg();
		HarshenItems.preInit();
		HarshenItems.reg();
		HarshenArmors.init();
		HarshenArmors.register();
	}

}
