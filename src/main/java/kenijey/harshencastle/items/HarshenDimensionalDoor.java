package kenijey.harshencastle.items;

import java.util.Arrays;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.blocks.HarshenBlocks;
import kenijey.harshencastle.blocks.HarshenDimensionalPlate;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalDoor extends Item
{
	ItemDoor itemDoor = new ItemDoor(HarshenBlocks.harshen_dimensional_door);
	Block block = HarshenBlocks.harshen_dimensional_door;
	
	public HarshenDimensionalDoor()
	{
		setUnlocalizedName("item_harshen_dimensional_door");
		setRegistryName(HarshenCastle.MODID, "item_harshen_dimensional_door");
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumActionResult a = itemDoor.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		worldIn.setBlockState(pos, worldIn.getBlockState(pos), 3);
		return a;
	}
}
