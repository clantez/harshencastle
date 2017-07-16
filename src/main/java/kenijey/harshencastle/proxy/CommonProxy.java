package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.WorldGen;
import kenijey.harshencastle.armor.ArmorInit;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.dimensions.HarshenDimensions;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.items.Recipes;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.Block;
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
		
		ArmorInit.init();
		ArmorInit.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
		
		HarshenSounds.preInit();
    }

    public void init(FMLInitializationEvent event) 
    {
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalPedestal.class, HarshenCastle.MODID + "HarshenDimensionalPedestalTileEntity");
    	GameRegistry.registerTileEntity(TileEntityHereticCauldron.class, HarshenCastle.MODID + "TileEntityHereticCauldron");

    	
    	Recipes.init();
    	
    	GameRegistry.registerWorldGenerator(new WorldGen(100), 0);
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }

	public void registerFluidBlockRendering(Block block, String name) {}

	public void regRenders(FMLPreInitializationEvent event)
	{
		
	}
}
