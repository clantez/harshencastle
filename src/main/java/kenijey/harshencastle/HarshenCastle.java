package kenijey.harshencastle;

import java.util.Arrays;

import kenijey.harshencastle.commands.CommandAccessPontusOuter;
import kenijey.harshencastle.commands.CommandDelFlatPlate;
import kenijey.harshencastle.commands.CommandFlatPlate;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.proxy.CommonProxy;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = HarshenCastle.MODID, name = HarshenCastle.MODNAME, version = HarshenCastle.VERSION, useMetadata = true/*, dependencies = "required-after:Forge@[14.21.1.2400,)"*/)
public class HarshenCastle {

    public static final String MODID = "harshencastle";
    public static final String MODNAME = "Harshen Castle";
    public static final String VERSION = "0.8.0";

    @SidedProxy(clientSide = "kenijey.harshencastle.proxy.ClientProxy", serverSide = "kenijey.harshencastle.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public static final CreativeTabs harshenTab = new HarshenTab("harshenTab");
    
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
		for(ICommand command : Arrays.asList(new CommandFlatPlate(), new CommandDelFlatPlate(), new CommandAccessPontusOuter()))
			event.registerServerCommand(command);
	}	
    
    public World getPontusWorld() {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(DimensionPontus.DIMENSION_ID);
	}
}