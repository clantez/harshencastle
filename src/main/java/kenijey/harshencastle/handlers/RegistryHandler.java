package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class RegistryHandler 
{
	public static void Client()
	{
		HarshenBlocks.regRenders();
		HarshenItems.regRenders();
		HarshenArmors.regRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(HarshenDimensionalPedestalTileEntity.class, new RendererDimensionalPedestal());
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
