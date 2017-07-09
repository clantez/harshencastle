package kenijey.harshencastle.proxy;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.armor.ArmorInit;
import kenijey.harshencastle.itemrenderer.RendererDimensionalPedestal;
import kenijey.harshencastle.tileentity.HarshenDimensionalPedestalTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    public void registerModelBakeryVarients() 
    {
    	ModelBakery.registerItemVariants(HarshenItems.blood_collector, new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_0"), 
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_1"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_2"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_3"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_4"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_5"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_6"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_7"));

    }
    
    @Override
    public void regRenders(FMLPreInitializationEvent event) {
    	super.regRenders(event);
    	
    	HarshenBlocks.regRenders();
		HarshenItems.regRenders();
		ArmorInit.regRenders();
		ClientRegistry.bindTileEntitySpecialRenderer(HarshenDimensionalPedestalTileEntity.class, new RendererDimensionalPedestal());
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