package kenijey.harshencastle.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class HarshenDimensionalFluid extends Fluid {
    
    public static final String name = "harshen_dimensional_fluid";
    public static final HarshenDimensionalFluid instance = new HarshenDimensionalFluid();

    public HarshenDimensionalFluid()
    {
        super(name, new ResourceLocation("harshencastle:blocks/harshen_dimensional_fluid_still"), new ResourceLocation("harshencastle:blocks/harshen_dimensional_fluid_flowing"));
    }

}