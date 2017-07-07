package kenijey.harshencastle.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class DimensionalFluid extends Fluid {
    
    public static final String name = "dimensional_liquid";
    public static final DimensionalFluid instance = new DimensionalFluid();

    public DimensionalFluid()
    {
        super(name, new ResourceLocation("harshencastle:blocks/dimensional_liquid_still"), new ResourceLocation("harshencastle:blocks/dimensional_liquid_flowing"));
    }

}