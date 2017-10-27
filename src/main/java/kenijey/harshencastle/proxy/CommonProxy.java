package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenCraftingRecipes;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.HarshenStructures;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.HarshenVillagers;
import kenijey.harshencastle.WorldGen;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.dimensions.HarshenDimensions;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import kenijey.harshencastle.enchantment.HarshenEnchantmetns;
import kenijey.harshencastle.entity.HarshenEntities;
import kenijey.harshencastle.enums.SetIds;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import kenijey.harshencastle.enums.items.EnumBloodCollector;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawner;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawnerParts;
import kenijey.harshencastle.enums.items.EnumProp;
import kenijey.harshencastle.enums.items.EnumRitualStick;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.handlers.GuiHandler;
import kenijey.harshencastle.handlers.HandlerBlockBurn;
import kenijey.harshencastle.handlers.HandlerBloodOnHurt;
import kenijey.harshencastle.handlers.HandlerBurnInDaylight;
import kenijey.harshencastle.handlers.HandlerExtraRange;
import kenijey.harshencastle.handlers.HandlerGlassContainer;
import kenijey.harshencastle.handlers.HandlerHarshenArmourEffects;
import kenijey.harshencastle.handlers.HandlerHarshenInventory;
import kenijey.harshencastle.handlers.HandlerIronHeartDrop;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.handlers.HandlerPotionEffects;
import kenijey.harshencastle.handlers.HandlerRaptorScythe;
import kenijey.harshencastle.handlers.HandlerSoulHarsherSword;
import kenijey.harshencastle.handlers.HandlerVillagerSpawn;
import kenijey.harshencastle.handlers.HandlerZombieEyeDrop;
import kenijey.harshencastle.interfaces.ICommandStructure;
import kenijey.harshencastle.internal.HarshenAPIHandler;
import kenijey.harshencastle.models.ModelArmour;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import kenijey.harshencastle.potions.HarshenPotions;
import kenijey.harshencastle.structures.faux.FauxCauldronStructure;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import kenijey.harshencastle.tileentity.TileEntityBloodVessel;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import kenijey.harshencastle.tileentity.TileEntityFlatPlate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalGate;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldronTop;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {    	    	
    	HarshenConfigs.IDS.preInit(); //dont mess with order
    	HarshenConfigs.GENERAL.preInit();
    	
    	HarshenPotions.preInit();
		HarshenPotions.register();
				
    	setUpEnumValues();
    	
    	HarshenEnchantmetns.preInit();
				
		HarshenBlocks.preInit();
		HarshenItems.preInit();
		HarshenStructures.preInit();
		
		HarshenConfigs.BLOCKS.preInit();
		HarshenConfigs.ITEMS.preInit();
		HarshenConfigs.STRUCTURES.preInit();
		
		HarshenBlocks.register();
		HarshenItems.register();
		HarshenStructures.register();
		
    	HarshenConfigs.ACCESSORIES.preInit();
    	
    	HarshenStructure.load();
		
		HarshenArmors.preInit();
		HarshenArmors.register();
		
		HarshenDimensions.register();
		
		HarshenBiomes.register();
				
		HarshenNetwork.preInit();
		
		HarshenSounds.register();
		
		HarshenVillagers.preInit();
				
		HarshenAPIHandler.loadPlugins(event.getAsmData());
							
		HarshenCastle.LOGGER.info("HarshenCastle loaded correctly");
    }	
    
    
    private void setUpEnumValues()
    {
		SetIds.setup(EnumBloodCollector.values());
		SetIds.setup(EnumPontusGateSpawner.values());
		SetIds.setup(EnumPontusGateSpawnerParts.values());
		SetIds.setup(EnumProp.values());
		SetIds.setup(EnumRitualStick.values());
		SetIds.setup(EnumInventorySlots.values());	
    }

    public void init(FMLInitializationEvent event) 
    {
    	
		HarshenCraftingRecipes.register();

    	HarshenEntities.init();
    	
		NetworkRegistry.INSTANCE.registerGuiHandler(HarshenCastle.instance, new GuiHandler());

    	Class[] tileEntityClasses = {
    			TileEntityHarshenDimensionalPedestal.class,
    			TileEntityHereticCauldron.class,
    			TileEntityHereticCauldronTop.class,
    			TileEntityHarshenDisplayBlock.class,
    			TileEntityHarshenSpawner.class,
    			TileEntityHarshenDimensionalGate.class,
    			TileEntityPedestalSlab.class,
    			TileEntityBloodVessel.class,
    			TileEntityBloodFactory.class,
    			TileEntityFlatPlate.class,
    			TileEntityHarshenMagicTable.class,
    			TileEntityCaulronBlock.class
    	};
    	for(Class clas : tileEntityClasses)
    		GameRegistry.registerTileEntity(clas, HarshenCastle.MODID + clas.getSimpleName());	
    	
    	HarshenUtils.registerHandlers(new HandlerRaptorScythe(),
    			new HandlerSoulHarsherSword(), 
    			new HandlerHarshenInventory(), 
    			new HandlerBloodOnHurt(), 
    			new HandlerPotionEffects(), 
    			new HandlerHarshenArmourEffects(),
    			new HandlerGlassContainer(), 
    			new HandlerPontusAllowed(), 
    			new HandlerZombieEyeDrop(),
    			new HandlerIronHeartDrop(),
    			new HandlerBlockBurn(),
    			new HandlerVillagerSpawn(),
    			new HandlerExtraRange(),
    			new HandlerBurnInDaylight());
    	
    	GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    	
    	ICommandStructure[] customStructures = {
    			new FauxCauldronStructure()
    	};
    	
    	ICommandStructure.ALL_STRUCTURES.addAll(HarshenUtils.toArray(customStructures));
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
	
	public void addErroredPosition(FaceRenderer renderer){}
	
	public void resetErroredPositions(){}


}
