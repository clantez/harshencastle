package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.handlers.server.HandlerCauldronLoadOnWorldCreate;
import kenijey.harshencastle.handlers.server.HandlerSyncConfig;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;

public class ServerProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event) {
		HarshenUtils.registerHandlers(new HandlerSyncConfig(),
				new HandlerCauldronLoadOnWorldCreate());
		super.init(event);
	}
	
	@Override
	public void onLoad(FMLLoadCompleteEvent event) {
		HarshenAPIHandler.register();
		HarshenCastle.LOGGER.info("All recipes loaded");
		super.onLoad(event);
	}
}
