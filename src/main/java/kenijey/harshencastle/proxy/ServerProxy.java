package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.handlers.server.HandlerSyncConfig;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ServerProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event) {
		HarshenUtils.registerHandlers(new HandlerSyncConfig());
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		HarshenAPIHandler.register();
		HarshenCastle.LOGGER.info("All recipes loaded");
	}
}
