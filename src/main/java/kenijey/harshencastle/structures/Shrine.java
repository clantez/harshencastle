package kenijey.harshencastle.structures;

import java.util.Random;

import kenijey.harshencastle.HarshenLootTables;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.HarshenStructure;
import net.minecraft.block.BlockChest;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Shrine extends HarshenStructure {

	public Shrine() {
		super("overworld/resource", "shrine",  0.001f, false, 0);
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) {
		pos = pos.subtract(originAddition);
		world.setBlockState(pos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.HORIZONTALS[random.nextInt(4)]), 3);
		if(world instanceof WorldServer && world.getTileEntity(pos) != null)
		{
			TileEntityChest chest = (TileEntityChest)world.getTileEntity(pos);
			chest.setInventorySlotContents(13, HarshenUtils.getItemsFromLootTable(world, HarshenLootTables.shrine).get(0));
			for(ItemStack stack : HarshenUtils.getItemsFromLootPool(world, HarshenLootTables.shrine, "extras"))
				for(int count = 0; count < stack.getCount(); count++)
				{
					int slot = new Random().nextInt(27);
					while(chest.getStackInSlot(slot).getItem() != Item.getItemFromBlock(Blocks.AIR))
						slot = new Random().nextInt(27);
					ItemStack stack1 = stack.copy();
					stack1.setCount(1);
					chest.setInventorySlotContents(slot, stack1);
				}
		}
	}
}
