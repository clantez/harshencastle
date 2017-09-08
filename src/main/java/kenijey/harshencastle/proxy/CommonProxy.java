package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.WorldGen;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.dimensions.HarshenDimensions;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import kenijey.harshencastle.entity.HarshenEntities;
import kenijey.harshencastle.enums.SetIds;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.enums.items.EnumBloodCollector;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawner;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawnerParts;
import kenijey.harshencastle.enums.items.EnumProp;
import kenijey.harshencastle.enums.items.EnumRitualStick;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.handlers.HandlerBloodOnHurt;
import kenijey.harshencastle.handlers.HandlerGlassContainer;
import kenijey.harshencastle.handlers.HandlerHarshenArmourEffects;
import kenijey.harshencastle.handlers.HandlerHarshenInventory;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryEffects;
import kenijey.harshencastle.handlers.HandlerPlayerInventoryOverDeath;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.handlers.HandlerPotionEffects;
import kenijey.harshencastle.handlers.HandlerRaptorScythe;
import kenijey.harshencastle.handlers.HandlerSoulHarsherSword;
import kenijey.harshencastle.handlers.HandlerZombieEyeDrop;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.models.ModelArmour;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.potions.HarshenPotions;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import kenijey.harshencastle.tileentity.TileEntityFlatPlate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalGate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldronTop;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {
    	HarshenPotions.preInit();//dont mess with order
		HarshenPotions.register();
		
    	HarshenFluids.register();
		
    	setUpEnumValues();
    	
		HarshenBlocks.preInit();
		HarshenItems.preInit();
				
		HarshenBlocks.reg();
		HarshenItems.reg();
		
		HarshenConfigs.preInit();
		
		HarshenBlocks.register();
		HarshenItems.register();
		
		HarshenArmors.preInit();
		HarshenArmors.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
		
		HarshenSounds.preInit();
		
		HarshenNetwork.preInit();
		
						
    }
    
    private void setUpEnumValues()
    {
		SetIds.setup(EnumGlassContainer.values());
		SetIds.setup(EnumBloodCollector.values());
		SetIds.setup(EnumPontusGateSpawner.values());
		SetIds.setup(EnumPontusGateSpawnerParts.values());
		SetIds.setup(EnumProp.values());
		SetIds.setup(EnumRitualStick.values());
		SetIds.setup(EnumInventorySlots.values());	
    }

    public void init(FMLInitializationEvent event) 
    {
    	HarshenEntities.init();
    	
		NetworkRegistry.INSTANCE.registerGuiHandler(HarshenCastle.instance, new GuiHandler());

    	
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalPedestal.class, HarshenCastle.MODID + "HarshenDimensionalPedestalTileEntity");
    	GameRegistry.registerTileEntity(TileEntityHereticCauldron.class, HarshenCastle.MODID + "TileEntityHereticCauldron");
    	GameRegistry.registerTileEntity(TileEntityHereticCauldronTop.class, HarshenCastle.MODID + "TileEntityHereticCauldronTop");
    	GameRegistry.registerTileEntity(TileEntityHarshenDisplayBlock.class, HarshenCastle.MODID + "TileEntityHarshenDisplayBlock");
    	GameRegistry.registerTileEntity(TileEntityHarshenSpawner.class, HarshenCastle.MODID + "TileEntityHarshenSpawner");
    	GameRegistry.registerTileEntity(TileEntityHarshenDimensionalGate.class, HarshenCastle.MODID + "TileEntityHarshenDimensionalGate");
    	GameRegistry.registerTileEntity(TileEntityPedestalSlab.class, HarshenCastle.MODID + "TileEntityPedestalSlab");
    	GameRegistry.registerTileEntity(TileEntityBloodVessel.class, HarshenCastle.MODID + "TileEntityBloodVessel");
    	GameRegistry.registerTileEntity(TileEntityBloodFactory.class, HarshenCastle.MODID + "TileEntityBloodFactory");
    	GameRegistry.registerTileEntity(TileEntityFlatPlate.class, HarshenCastle.MODID + "TileEntityFlatPlate");

    	
    	GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    	
    	HarshenRecipes.register();
    	
    	Object[] handlers = {
    			new HandlerRaptorScythe(),
    			new HandlerSoulHarsherSword(), 
    			new HandlerHarshenInventory(), 
    			new HandlerBloodOnHurt(), 
    			new HandlerPotionEffects(), 
    			new HandlerHarshenArmourEffects(),
    			new HandlerGlassContainer(), 
    			new HandlerPontusAllowed(), 
    			new HandlerHarshenInventoryEffects(), 
    			new HandlerZombieEyeDrop(), 
    			new HandlerPlayerInventoryOverDeath()};
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

	public void openGui(EnumGuiTypes gui, Object... info){}

	public void setWorldRenderer(PontusWorldProvider prov) {		
	}
	
    public Particle spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, Object...info){return null;};
	
	public ModelArmour getArmorModel(int id)
	{
		return null;
	};
}
