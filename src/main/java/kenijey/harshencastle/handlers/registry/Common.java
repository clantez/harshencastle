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
		HarshenBlocks.reg();
		HarshenItems.preInit();
		HarshenItems.reg();
		ArmorInit.init();
		ArmorInit.register();
	}
}
