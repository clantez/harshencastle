package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.fluids.HarshenFluids;

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
