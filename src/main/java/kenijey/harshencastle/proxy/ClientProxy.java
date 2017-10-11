package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import kenijey.harshencastle.entity.EntityFactories;
import kenijey.harshencastle.entity.EntitySoulPart;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.entityrender.RenderEntityThrown;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import kenijey.harshencastle.enums.gui.EnumGuiTypes;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.items.EnumRitualStick;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.gui.GuiBookScreen;
import kenijey.harshencastle.gui.GuiXrayPendantScreen;
import kenijey.harshencastle.handlers.client.HandlerFlatPlateRenderer;
import kenijey.harshencastle.handlers.client.HandlerGameOverlay;
import kenijey.harshencastle.handlers.client.HandlerGuiEvent;
import kenijey.harshencastle.handlers.client.HandlerKeyBinding;
import kenijey.harshencastle.handlers.client.HandlerRenderError;
import kenijey.harshencastle.handlers.client.HandlerRendererGuiInventory;
import kenijey.harshencastle.handlers.client.HandlerUpdateChecker;
import kenijey.harshencastle.itemrenderer.RendererBloodFactory;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.itemrenderer.RendererHarshenDisplayBlock;
import kenijey.harshencastle.itemrenderer.RendererHarshenSpawner;
import kenijey.harshencastle.itemrenderer.RendererHereticCauldron;
import kenijey.harshencastle.itemrenderer.RendererMagicTable;
import kenijey.harshencastle.itemrenderer.RendererPedestalSlab;
import kenijey.harshencastle.models.ModelArmour;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import kenijey.harshencastle.particle.ParticleBlood;
import kenijey.harshencastle.particle.ParticleCauldron;
import kenijey.harshencastle.particle.ParticleCauldronTop;
import kenijey.harshencastle.particle.ParticleItem;
import kenijey.harshencastle.skyrenders.WeatherPontus;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{
    public static ResourceLocation particleTexturesLocation = new ResourceLocation(HarshenCastle.MODID, "textures/particles/particles.png");
	
	@Override
    public void regRenders(FMLPreInitializationEvent event) {
    	super.regRenders(event);
    	
    	HarshenBlocks.regRenders();
    	
		HarshenItems.regRenders();
		
		HarshenArmors.regRenders();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenDimensionalPedestal.class, new RendererDimensionalPedestal());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHereticCauldron.class, new RendererHereticCauldron());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenDisplayBlock.class, new RendererHarshenDisplayBlock());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenSpawner.class, new RendererHarshenSpawner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestalSlab.class, new RendererPedestalSlab());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBloodFactory.class, new RendererBloodFactory());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHarshenMagicTable.class, new RendererMagicTable());

		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoullessKnight.class, new EntityFactories.FactorySoullessKnight());
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulPart.class, new EntityFactories.FactorySoulPart());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrown.class, new EntityFactories.FactoryEntityThrown());
	}
	
	 @Override
	public int getrenderDistance() {
		return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
	}
	
	@Override
    public void preInit(FMLPreInitializationEvent event) 
    {
    	super.preInit(event);
    	new HarshenClientUtils();
    }
	
	@Override
	public void openGui(EnumGuiTypes gui, Object... info) {
		GuiScreen screen = null;
		switch (gui) {
		case BOOK:
			screen = new GuiBookScreen();
			break;
		case XRAY_PENDANT:
			if(info[0] instanceof ItemStack)
				screen = new GuiXrayPendantScreen(((ItemStack)info[0]));
			break;
		default:
			break;
		}
		Minecraft.getMinecraft().displayGuiScreen(screen);
	}
    
	@Override
	public void setWorldRenderer(PontusWorldProvider prov) {
		prov.setWeatherRenderer(new WeatherPontus());
	}
	
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	super.init(event);
    	
    	HarshenUtils.registerHandlers(new HandlerKeyBinding(),
    			new HandlerGameOverlay(),
    			new HandlerGuiEvent(),
    			new HandlerRendererGuiInventory(), 
    			new HandlerFlatPlateRenderer(),
    			new HandlerUpdateChecker(),
    			new HandlerRenderError(),
    			new RenderEntityThrown(null));
    	
    	
    	
    	ItemColors itemcolors = Minecraft.getMinecraft().getItemColors();
    	itemcolors.registerItemColorHandler(new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 ? -1 : EnumGlassContainer.getContainerFromMeta(stack.getMetadata()).color;
			}
		}, HarshenItems.GLASS_CONTAINER);
    	
    	itemcolors.registerItemColorHandler(new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return EnumRitualStick.getColorFromMeta(stack.getMetadata());
			}
		}, HarshenItems.RITUAL_STICK);
    	
    	itemcolors.registerItemColorHandler(new IItemColor(){

			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return ItemLiquidTypeset.getFromMeta(stack.getMetadata()) == null ? -1 : EnumGlassContainer.getContainerFromType(ItemLiquidTypeset.getFromMeta(stack.getMetadata()).getType()).color;
			}
    	}, HarshenItems.ITEM_LIQUID);
    }
    
    @Override
    public EntityPlayer getPlayer() {
    	return Minecraft.getMinecraft().player;
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) 
    {
    	super.postInit(event);
    }
    
    
    @Override
    public void registerFluidBlockRendering(Block block, String name) 
    {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(HarshenCastle.MODID.toLowerCase() + ":fluids", name);

        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return fluidLocation;
            }
        });
    }
    
    @Override
    public Particle spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, Object...info) {
    	Minecraft minecraft = Minecraft.getMinecraft();
        Particle entityFx = null;
        if(minecraft.world !=  null)
	        switch (type)
	        {
		        case BLOOD:
		            entityFx = new ParticleBlood(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale, disableMoving);
		            break;
		        case CAULDRON:
			        if(info.length > 0 )
			        	if(info[0] instanceof ResourceLocation)
			        		entityFx = new ParticleCauldron(minecraft.world, (ResourceLocation) info[0], position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving);
			        	if(info[0] instanceof IBlockState)
			        		entityFx = new ParticleCauldron(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving, ((IBlockState)info[0]));
			        break;
		        case ITEM:
		        	if(info.length > 0 && info[0] instanceof ItemStack)
		        		entityFx = new ParticleItem(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving, (ItemStack) info[0]);
		        	break;
		        case PORTAL:
		        	entityFx = new ParticlePortal.Factory().createParticle(EnumParticleTypes.PORTAL.getParticleID(), minecraft.world, 
	        				position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z);
		        	entityFx.setMaxAge((int)(Math.random() * 20.0D) + 100);
		        case CAULDRON_LIQUID:
		        	if(info.length > 0)
		        		if(info[0] instanceof ResourceLocation)
		        			entityFx = new ParticleCauldronTop(minecraft.world, position.x, position.y, position.z, scale, (ResourceLocation) info[0]);
		        		else if(info[0] instanceof IBlockState)
		        			entityFx = new ParticleCauldronTop(minecraft.world, position.x, position.y, position.z, scale, ((IBlockState) info[0]));
		        	break;
		        default:
		            break;
	        }
        if (entityFx != null) {minecraft.effectRenderer.addEffect(entityFx);}
        return entityFx;
    }
    
    @Override
    public ModelArmour getArmorModel(int id) {
    	switch (id) {
    	 case 0:
    	 return new ModelArmour(1.0f);
    	 case 1:
    	 return new ModelArmour(0.5f);
    	}
    	return null;
    }
    
    @Override
    public void addErroredPosition(FaceRenderer renderer) {
    	HandlerRenderError.erroredPositions.add(renderer);
    }
    
    @Override
    public void resetErroredPositions() {
    	HandlerRenderError.erroredPositions.clear();
    }
    
    @Override
    public void onLoad(FMLLoadCompleteEvent event) {
    	((IReloadableResourceManager)  Minecraft.getMinecraft().getResourceManager()).registerReloadListener(resourceManager -> {
			HarshenRecipes.register();
			HarshenCastle.LOGGER.info("All recipes loaded");
		});
    }
    
}