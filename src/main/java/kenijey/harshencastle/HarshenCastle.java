package kenijey.harshencastle;

import kenijey.harshencastle.biomes.debugworldtype.DebugWorldType;
import kenijey.harshencastle.commands.CommandDelFlatPlate;
import kenijey.harshencastle.commands.CommandFlatPlate;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.handlers.BucketHandler;
import kenijey.harshencastle.items.Recipes;
import kenijey.harshencastle.proxy.CommonProxy;
import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = HarshenCastle.MODID, name = HarshenCastle.MODNAME, version = HarshenCastle.VERSION, useMetadata = true/*, dependencies = "required-after:Forge@[14.21.1.2400,)"*/)
public class HarshenCastle {

    public static final String MODID = "harshencastle";
    public static final String MODNAME = "Harshen Castle";
    public static final String VERSION = "0.3.1";

    @SidedProxy(clientSide = "kenijey.harshencastle.proxy.ClientProxy", serverSide = "kenijey.harshencastle.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs harshenTab = new HarshenTab("harshenTab");

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
    	MinecraftForge.EVENT_BUS.register(new BucketHandler());
    	Recipes.init();
    	GameRegistry.registerWorldGenerator(new WorldGen(100), 0);
    	proxy.registerModelBakeryVarients();
    	GameRegistry.registerTileEntity(HarshenDimensionalPedestalTileEntity.class, MODID + "HarshenDimensionalPedestalTileEntity");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	proxy.postInit(event);
    	WorldType Pontus = new DebugWorldType();
    }
    
    @EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandFlatPlate());
		event.registerServerCommand(new CommandDelFlatPlate());
	}	
    
    public World getPontusWorld() {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(DimensionPontus.DIMENSION_ID);
	}
}