package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.entityrender.RenderSoullessKnight;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.itemrenderer.RendererHarshenDisplayBlock;
import kenijey.harshencastle.itemrenderer.RendererHarshenSpawner;
import kenijey.harshencastle.itemrenderer.RendererHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy extends CommonProxy 
{
	@Override
    public void preInit(FMLPreInitializationEvent event) 
    {
    	super.preInit(event);
    }
    
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) 
    {
    	super.postInit(event);
    }
    
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
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoullessKnight.class, new IRenderFactory<Entity>() 
		{

			@Override
			public Render<? super Entity> createRenderFor(RenderManager manager) {
				return new RenderSoullessKnight(manager);
			}
	
		});
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
}