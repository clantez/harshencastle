package kenijey.harshencastle.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenRecipes;
import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.recipies.RitualRecipes;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PontusRitual extends HarshenStructure
{
	public PontusRitual() {
		super("pontus/resource", "ritual", new BlockPos(-5, 0, -5), 0.0005f, false, DimensionPontus.DIMENSION_ID);
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) 
	{
		RitualRecipes recipe = HarshenRecipes.allRitualRecipes.get(random.nextInt(HarshenRecipes.allRitualRecipes.size()));
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for(ItemStack stack : recipe.getInputs())
			stacks.add(stack.copy());
		stacks.remove(random.nextInt(stacks.size()));
		BlockPos position = pos.add(-originAddition.getX(), -originAddition.getY() + 1, -originAddition.getZ());
		ArrayList<EnumFacing> shuffledFacing = new ArrayList<>();
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
			shuffledFacing.add(facing);
		Collections.shuffle(shuffledFacing);
		for(EnumFacing facing : shuffledFacing)
			if(world.getBlockState(position.offset(facing)).getBlock() == HarshenBlocks.harshen_dimensional_pedestal
			&& facing.getHorizontalIndex() < stacks.size() && random.nextBoolean())
				((TileEntityHarshenDimensionalPedestal)world.getTileEntity(position.offset(facing))).setItem(stacks.get(facing.getHorizontalIndex()));	
	}
	
	@Override
	public boolean addPositionOnGenerate() {
		return true;
	}

}
