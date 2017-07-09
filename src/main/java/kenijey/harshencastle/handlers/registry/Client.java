package kenijey.harshencastle.handlers.registry;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.ArmorInit;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class Client 
{
	
	public static void preInit()
	{
		HarshenBlocks.regRenders();
		HarshenItems.regRenders();
		ArmorInit.regRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(HarshenDimensionalPedestalTileEntity.class, new RendererDimensionalPedestal());
	}
}
