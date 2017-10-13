package kenijey.harshencastle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kenijey.harshencastle.commands.CommandHarshenCastleLoader;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
		modid = HarshenCastle.MODID,
		name = HarshenCastle.MODNAME,
		version = HarshenCastle.VERSION, 
		useMetadata = true, 
		acceptedMinecraftVersions = "[1.12,1.13]")

public class HarshenCastle {

    public static final String MODID = "harshencastle";
    public static final String MODNAME = "Harshen Castle";
    public static final String VERSION = "0.11.0";
    public static final String UPDATE_URL = "http://www.wynprice.com/moddedUpdateCheckers/harshencastle.json";

    @SidedProxy(clientSide = "kenijey.harshencastle.proxy.ClientProxy", serverSide = "kenijey.harshencastle.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public static final CreativeTabs harshenTab = new HarshenTab();
    
    public static final Logger LOGGER = LogManager.getLogger(MODID); 

    
    @Instance(MODID)
    public static HarshenCastle instance;
    
	static 
	{
		FluidRegistry.enableUniversalBucket();
	}


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	proxy.preInit(event);
    	proxy.regRenders(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postInit(event);
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
    	event.registerServerCommand(new CommandHarshenCastleLoader());
	}
    
    @EventHandler
    public void onLoad(FMLLoadCompleteEvent event)
    {
		proxy.onLoad(event);
    }
}
