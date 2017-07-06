package kenijey.harshencastle.blocks;

import java.util.Random;

import kenijey.harshencastle.items.HarshenItems;
import kenijey.harshencastle.items.SoulHarsherPickaxe;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs.EnumHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

public class HarshenDimensionalDoor extends BlockDoor
{
	public HarshenDimensionalDoor()
	{
		super(Material.ROCK);
		setUnlocalizedName("harshen_dimensional_door");
        setRegistryName("harshen_dimensional_door");
        setHarvestLevel("pickaxe", 3);
		setHardness(2500.0F);
		setResistance(2500.0F);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{FACING,HALF,HINGE,OPEN,POWERED});
	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return HarshenItems.item_harshen_dimensional_door;
	}
	
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(HarshenItems.item_harshen_dimensional_door);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
			player.attackEntityFrom(DamageSource.MAGIC, 21);
			if(!worldIn.isRemote)
			{
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.broken"));
			}


	}

}
