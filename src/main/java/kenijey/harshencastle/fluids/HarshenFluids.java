package kenijey.harshencastle.fluids;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.blocks.BlockDimensionalFluid;
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

	
	public static Fluid dimensional_fluid;
	public static Block dimensional_fluid_block;

	public static void register()
	{
		dimensional_fluid = DimensionalFluid.instance;
		dimensional_fluid.setViscosity(1000);
		dimensional_fluid.setDensity(500);
        FluidRegistry.addBucketForFluid(dimensional_fluid);
        dimensional_fluid_block = registerFluidBlock(dimensional_fluid, new BlockDimensionalFluid(dimensional_fluid), "dimensional_fluid");
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
