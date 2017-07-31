package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenKeybinding;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.entity.EntitySoulPart;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.gui.GuiBookScreen;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.itemrenderer.RendererHarshenDisplayBlock;
import kenijey.harshencastle.itemrenderer.RendererHarshenSpawner;
import kenijey.harshencastle.itemrenderer.RendererHereticCauldron;
import kenijey.harshencastle.itemrenderer.RendererPedestalSlab;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import kenijey.harshencastle.tileentity.TileEntityHarshenSpawner;
import kenijey.harshencastle.tileentity.TileEntityHereticCauldron;
import kenijey.harshencastle.tileentity.TileEntityPedestalSlab;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy 
{
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

		
		RenderingRegistry.registerEntityRenderingHandler(EntitySoullessKnight.class, EntitySoullessKnight.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySoulPart.class, EntitySoulPart.FACTORY);
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
	public void bookClicked() {
		Minecraft.getMinecraft().displayGuiScreen(new GuiBookScreen());
	}
    
    @Override
    public void init(FMLInitializationEvent event) 
    {
    	super.init(event);
    	
    	Object[] handlers = {new HarshenKeybinding()};
    	for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
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
}