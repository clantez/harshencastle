package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import kenijey.harshencastle.entity.EntityFactories;
import kenijey.harshencastle.entity.EntitySoulPart;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.gui.GuiBookScreen;
import kenijey.harshencastle.handlers.client.HandlerGameOverlay;
import kenijey.harshencastle.handlers.client.HandlerGuiEvent;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.itemrenderer.RendererBloodFactory;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.itemrenderer.RendererHarshenDisplayBlock;
import kenijey.harshencastle.itemrenderer.RendererHarshenSpawner;
import kenijey.harshencastle.itemrenderer.RendererHereticCauldron;
import kenijey.harshencastle.itemrenderer.RendererPedestalSlab;
import kenijey.harshencastle.models.ModelArmour;
import kenijey.harshencastle.particle.ParticleBlood;
import kenijey.harshencastle.particle.ParticleCauldron;
import kenijey.harshencastle.skyrenders.WeatherPontus;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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

		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoullessKnight.class, new EntityFactories.FactorySoullessKnight());
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulPart.class, new EntityFactories.FactorySoulPart());
    }
	
	 @Override
	public int getrenderDistance() {
		return Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
	}
	
	@Override
    public void preInit(FMLPreInitializationEvent event) 
    {
    	super.preInit(event);
    }
	
	@Override
	public void book()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiBookScreen());
		System.out.println(new ItemColors().getColorFromItemstack(Minecraft.getMinecraft().player.getHeldItemOffhand(), 1));
	}
    
	@Override
	public void setWorldRenderer(PontusWorldProvider prov) {
		prov.setWeatherRenderer(new WeatherPontus());
	}
	
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	super.init(event);
    	
		NetworkRegistry.INSTANCE.registerGuiHandler(HarshenCastle.instance, new GuiHandler());

    	
    	Object[] handlers = {new HandlerGameOverlay(), new HandlerGuiEvent()};
    	for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
    	
    	ItemColors itemcolors = Minecraft.getMinecraft().getItemColors();
    	itemcolors.registerItemColorHandler(new IItemColor() {
			
			@Override
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				return tintIndex == 1 ? -1 : EnumGlassContainer.getContainerFromMeta(stack.getMetadata()).color;
			}
		}, HarshenItems.glass_container);
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
    public void spawnParticle(EnumHarshenParticle type, Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving, Object...info) {
    	Minecraft minecraft = Minecraft.getMinecraft();
        Particle entityFx = null;
        if(minecraft.world !=  null)
	        switch (type)
	        {
		        case BLOOD:
		            entityFx = new ParticleBlood(minecraft.world, position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale, disableMoving);
		            break;
		        case CAULDRON:
		        	entityFx = new ParticleCauldron(minecraft.world, (ResourceLocation) info[0], position.x, position.y, position.z, directionSpeed.x, directionSpeed.y, directionSpeed.z, scale / 5f, disableMoving);
		            break;
		        default:
		            break;
	        }
        if (entityFx != null) {minecraft.effectRenderer.addEffect(entityFx);}
    }
    
    @Override
    public ModelBiped getArmorModel(int id) {
    	switch (id) {
    	 case 0:
    	 return new ModelArmour(1.0f);
    	 case 1:
    	 return new ModelArmour(0.5f);
    	}
    	return null;
    }
}