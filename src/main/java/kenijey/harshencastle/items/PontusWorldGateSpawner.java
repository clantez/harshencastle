package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.blocks.HarshenDimensionalGate;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawner;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PontusWorldGateSpawner extends BaseItemMetaData
{
	public PontusWorldGateSpawner()
	{
		setUnlocalizedName("pontus_world_gate_spawner");
		setRegistryName("pontus_world_gate_spawner");
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab instanceof HarshenTab)
			for(EnumPontusGateSpawner l : EnumPontusGateSpawner.values())
				items.add(new ItemStack(this, 1, l.getMeta()));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String prefix = stack.getMetadata() == 0? "gatespawner" : "gatespawnerenhanced";
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation(prefix + "1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation(prefix + "2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isAirBlock(pos.offset(facing)))
			return EnumActionResult.FAIL;
		worldIn.setBlockState(pos.offset(facing), HarshenBlocks.harshen_dimensional_gate.getStateFromMeta(player.getHeldItem(hand).getMetadata() == 0? 2 : 3), 3);
		if(!player.capabilities.isCreativeMode)
			player.setHeldItem(hand, ItemStack.EMPTY);
		return EnumActionResult.SUCCESS;
	}

	@Override
	protected String[] getNames() {
		return EnumPontusGateSpawner.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}

}
