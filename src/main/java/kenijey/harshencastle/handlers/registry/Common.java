package kenijey.harshencastle.handlers.registry;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.ArmorInit;
import kenijey.harshencastle.fluids.HarshenFluids;

public class Common 
{
	public static void preInit()
	{
		HarshenFluids.register();
		
		HarshenBlocks.preInit();
		HarshenItems.preInit();
		
		HarshenBlocks.reg();
		HarshenItems.reg();
		
		ArmorInit.init();
		ArmorInit.register();
	}
}
