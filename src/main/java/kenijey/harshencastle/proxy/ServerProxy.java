package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.handlers.server.HandlerSyncConfig;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy
{	
	@Override
	public void init(FMLInitializationEvent event) {
		HarshenUtils.registerHandlers(new HandlerSyncConfig());
		super.init(event);
	}
	
	@Override
	public void onLoad(FMLLoadCompleteEvent event) {
		HarshenRecipes.register();
		HarshenCastle.LOGGER.info("All recipes loaded");
		super.onLoad(event);
	}
}
