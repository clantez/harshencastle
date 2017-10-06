package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.handlers.server.HandlerSyncConfig;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event) {
		HarshenUtils.registerHandlers(new HandlerSyncConfig());
		super.init(event);
	}
}
