package kenijey.harshencastle;

import kenijey.harshencastle.commands.CommandDelFlatPlate;
import kenijey.harshencastle.commands.CommandFlatPlate;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.items.Recipes;
import kenijey.harshencastle.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = HarshenCastle.MODID, name = HarshenCastle.MODNAME, version = HarshenCastle.VERSION/*, dependencies = "required-after:Forge@[14.21.1.2387,)", useMetadata = true*/)
public class HarshenCastle {

    public static final String MODID = "harshencastle";
    public static final String MODNAME = "Harshen Castle";
    public static final String VERSION = "0.2.1";

    @SidedProxy(clientSide = "kenijey.harshencastle.proxy.ClientProxy", serverSide = "kenijey.harshencastle.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs harshenTab = new HarshenTab("harshenTab");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) 
    {
    	Recipes.init();
    	GameRegistry.registerWorldGenerator(new WorldGen(100), 0);
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
		event.registerServerCommand(new CommandFlatPlate());
		event.registerServerCommand(new CommandDelFlatPlate());
	}	
}