package kenijey.harshencastle.blocks;

import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockRedFlower;
import net.minecraftforge.common.util.EnumHelper;

public class HarshenDestroyedPlant extends BlockFlower
{
	
	public HarshenDestroyedPlant() {
		setUnlocalizedName("harshen_destroyed_flower");
        setRegistryName("harshen_destroyed_flower");
	}

	@Override
	public EnumFlowerColor getBlockType() {
		return EnumFlowerColor.RED;
	}

}
