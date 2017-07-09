package kenijey.harshencastle.proxy;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.handlers.itemenum.EnumBloodCollectorHandler;
import kenijey.harshencastle.handlers.itemenum.EnumBloodCollectorHandler.BloodLevels;
import kenijey.harshencastle.handlers.registry.Common;
import kenijey.harshencastle.items.BloodCollector;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
    public void preInit(FMLPreInitializationEvent event) 
    {
    	Common.preInit();
    }

    public void init(FMLInitializationEvent event) 
    {
    	registerModelBakeryVarients();
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    }

	public void registerFluidBlockRendering(Block block, String name) {}
	
	
	public void registerModelBakeryVarients()
	{
		ModelBakery.registerItemVariants(HarshenItems.blood_collector, new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_0"), 
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_1"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_2"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_3"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_4"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_5"), new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_6"),
				new ResourceLocation(HarshenCastle.MODID, "harshen_blood_collector_7"));
	}
}
