package kenijey.harshencastle.blocks;

import java.util.List;

import kenijey.harshencastle.base.BaseHarshenLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PontusFarLeaves extends BaseHarshenLeaves {

	@Override
	protected String getName() {
		return "pontus_far_leaves";
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumType getWoodType(int meta) {
		// TODO Auto-generated method stub
		return null;
	}

}
