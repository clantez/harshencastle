package kenijey.harshencastle.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class HarshingWater extends Fluid {
    
    public static final String name = "harshing_water";
    public static final HarshingWater instance = new HarshingWater();

    public HarshingWater()
    {
        super(name, new ResourceLocation("harshencastle:blocks/harshing_water_still"), new ResourceLocation("harshencastle:blocks/harshing_water_flowing"));
        setViscosity(200);
		setDensity(5);
    }

}