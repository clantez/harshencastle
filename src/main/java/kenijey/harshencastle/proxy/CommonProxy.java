package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenKeybinding;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.Recipes;
import kenijey.harshencastle.WorldGen;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.dimensions.HarshenDimensions;
import kenijey.harshencastle.entity.HarshenEntities;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryCommon;
import kenijey.harshencastle.handlers.HandlerSoulHarsherSword;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalGate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {
    	HarshenFluids.register();
		
		HarshenBlocks.preInit();
		HarshenItems.preInit();
		
		HarshenBlocks.reg();
		HarshenItems.reg();
		
		HarshenArmors.init();
		HarshenArmors.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
		
		HarshenSounds.preInit();
		
		HarshenNetwork.preInit();
    }

    public void init(FMLInitializationEvent event) 
    {
    	HarshenEntities.init();
    	
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalPedestal.class, HarshenCastle.MODID + "HarshenDimensionalPedestalTileEntity");
    	GameRegistry.registerTileEntity(TileEntityHereticCauldron.class, HarshenCastle.MODID + "TileEntityHereticCauldron");
    	GameRegistry.registerTileEntity(TileEntityHarshenDisplayBlock.class, HarshenCastle.MODID + "TileEntityHarshenDisplayBlock");
    	GameRegistry.registerTileEntity(TileEntityHarshenSpawner.class, HarshenCastle.MODID + "TileEntityHarshenSpawner");
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalGate.class, HarshenCastle.MODID + "TileEntityHarshenDimensionalGate");
    	
    	Recipes.init();
    	
    	GameRegistry.registerWorldGenerator(new WorldGen(100), 0);
    	
    	Object[] handlers = {new HandlerSoulHarsherSword(), new HandlerHarshenInventoryCommon()};
    	for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
    	
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }

	public void registerFluidBlockRendering(Block block, String name) {}

	public void regRenders(FMLPreInitializationEvent event)
	{
		
	}

	public EntityPlayer getPlayer() {return null;}
}
