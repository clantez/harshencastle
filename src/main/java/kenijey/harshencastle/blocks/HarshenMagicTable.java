package kenijey.harshencastle.blocks;

import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.handlers.GuiHandler;
import kenijey.harshencastle.tileentity.TileEntityHarshenMagicTable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenMagicTable extends Block implements ITileEntityProvider
{
	public HarshenMagicTable()
	{
        super(Material.WOOD);
		setRegistryName("harshen_magic_table");
		setUnlocalizedName("harshen_magic_table");
		setHardness(5.0F);
		setResistance(5.0F);
	}	
	
	@Override
	 public boolean isOpaqueCube(IBlockState state)
	 {
	  return false;
	 }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(HarshenCastle.instance, GuiHandler.MAGICTABLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		for(ItemStack stack : ((TileEntityHarshenMagicTable)worldIn.getTileEntity(pos)).getHandler().getStacks())
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}
	
	@Override
	 public boolean isFullCube(IBlockState state) 
	 {
		return false;
	 }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A76" + new TextComponentTranslation("magic_table1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override	
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHarshenMagicTable();
	}

}
