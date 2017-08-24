package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenLootTables;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.WorldGen;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.dimensions.HarshenDimensions;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import kenijey.harshencastle.entity.HarshenEntities;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.handlers.HandlerBloodOnHurt;
import kenijey.harshencastle.handlers.HandlerGlassContainer;
import kenijey.harshencastle.handlers.HandlerHarshenArmourEffects;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryCommon;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.handlers.HandlerPotionEffects;
import kenijey.harshencastle.handlers.HandlerSoulHarsherSword;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.potions.HarshenPotions;
import kenijey.harshencastle.recipies.HarshenRecipes;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalGate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
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
    	HarshenPotions.preInit();
		HarshenPotions.register();
		
    	HarshenFluids.register();
		
		HarshenBlocks.preInit();
		HarshenItems.preInit();
		
		HarshenBlocks.reg();
		HarshenItems.reg();
		
		HarshenArmors.preInit();
		HarshenArmors.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
		
		HarshenSounds.preInit();
		
		HarshenNetwork.preInit();
		
		HarshenLootTables.preInit();
		
    }

    public void init(FMLInitializationEvent event) 
    {
    	HarshenEntities.init();
    	
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalPedestal.class, HarshenCastle.MODID + "HarshenDimensionalPedestalTileEntity");
    	GameRegistry.registerTileEntity(TileEntityHereticCauldron.class, HarshenCastle.MODID + "TileEntityHereticCauldron");
    	GameRegistry.registerTileEntity(TileEntityHarshenDisplayBlock.class, HarshenCastle.MODID + "TileEntityHarshenDisplayBlock");
    	GameRegistry.registerTileEntity(TileEntityHarshenSpawner.class, HarshenCastle.MODID + "TileEntityHarshenSpawner");
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalGate.class, HarshenCastle.MODID + "TileEntityHarshenDimensionalGate");
    	GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, HarshenCastle.MODID + "TileEntityPedestalSlab");
    	GameRegistry.registerTileEntity(TileEntityBloodVessel.class, HarshenCastle.MODID + "TileEntityBloodVessel");
    	GameRegistry.registerTileEntity(TileEntityBloodFactory.class, HarshenCastle.MODID + "TileEntityBloodFactory");

    	
    	GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    	
    	HarshenRecipes.register();
    	
    	Object[] handlers = {new HandlerSoulHarsherSword(), new HandlerHarshenInventoryCommon(), new HandlerBloodOnHurt(), new HandlerPotionEffects(), new HandlerHarshenArmourEffects(),
    			new HandlerGlassContainer(), new HandlerPontusAllowed()};
    	for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
    	
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }
    
    public int getrenderDistance()
    {
    	return 0;
    }

	public void registerFluidBlockRendering(Block block, String name) {}

	public void regRenders(FMLPreInitializationEvent event)
	{
		
	}

	public EntityPlayer getPlayer() 
	{
		return null;
	}

	public void book() {
		
	}

	public void setWorldRenderer(PontusWorldProvider prov) {		
	}
	
	public void spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, Object... info){}
	
	public ModelBiped getArmorModel(int id)
	{
		return null;
	};
}
