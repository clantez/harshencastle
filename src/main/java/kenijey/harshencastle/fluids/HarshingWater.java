package kenijey.harshencastle.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class HarshingWater extends Fluid {
    
    public static final String NAME = "harshing_water";

    public HarshingWater()
    {
        super(NAME, new ResourceLocation("harshencastle:blocks/harshing_water_still"), new ResourceLocation("harshencastle:blocks/harshing_water_flowing"));
        setViscosity(200);
		setDensity(5);
    }

}