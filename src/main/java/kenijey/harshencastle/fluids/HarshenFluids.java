package kenijey.harshencastle.fluids;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.blocks.HarshenDimensionalFluidBlock;
import kenijey.harshencastle.fluids.blocks.HarshingWaterBlock;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenFluids {

	
	public final static Fluid HARSHEN_DIMENSIONAL_FLUID = registerFluid(new HarshenDimensionalFluid());
	public final static Block HARSHEN_DIMENSIONAL_FLUID_BLOCK = registerFluidBlock(HARSHEN_DIMENSIONAL_FLUID, new HarshenDimensionalFluidBlock(), HarshenDimensionalFluid.NAME);
	
	public final static Fluid HARSHING_WATER = registerFluid(new HarshingWater());
	public final static Block HARSHING_WATER_BLOCK = registerFluidBlock(HARSHING_WATER, new HarshingWaterBlock(), HarshingWater.NAME);

	private static Fluid registerFluid(Fluid fluid)
	{
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
	}
	
	private static Block registerFluidBlock(Fluid fluid, Block fluidBlock, String name)
    {
        fluidBlock.setRegistryName(new ResourceLocation(HarshenCastle.MODID, name));
        ForgeRegistries.BLOCKS.register(fluidBlock);
        HarshenCastle.proxy.registerFluidBlockRendering(fluidBlock, name);
        fluid.setBlock(fluidBlock);
        return fluidBlock;
    }

}
