package kenijey.harshencastle.proxy;

import kenijey.harshencastle.handlers.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {
    	RegistryHandler.PreInitCommon();
    }

    public void init(FMLInitializationEvent event) 
    {
    	
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }

	public void registerFluidBlockRendering(Block block, String name) {}

}
