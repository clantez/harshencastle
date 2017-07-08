package kenijey.harshencastle.fluids;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.blocks.HarshenDimensionalFluidBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenFluids {

	
	public static Fluid harshen_dimensional_fluid;
	public static Block harshen_dimensional_fluid_block;

	public static void register()
	{
		harshen_dimensional_fluid = HarshenDimensionalFluid.instance;
		harshen_dimensional_fluid.setViscosity(1000);
		harshen_dimensional_fluid.setDensity(500);
        FluidRegistry.addBucketForFluid(harshen_dimensional_fluid);
        harshen_dimensional_fluid_block = registerFluidBlock(harshen_dimensional_fluid, new HarshenDimensionalFluidBlock(harshen_dimensional_fluid), "harshen_dimensional_fluid");
	}
	
	public static Block registerFluidBlock(Fluid fluid, Block fluidBlock, String name)
    {
        fluidBlock.setRegistryName(new ResourceLocation(HarshenCastle.MODID, name));
        ForgeRegistries.BLOCKS.register(fluidBlock);
        HarshenCastle.proxy.registerFluidBlockRendering(fluidBlock, name);
        fluid.setBlock(fluidBlock);
        return fluidBlock;
    }

}
