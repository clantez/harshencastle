package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.creativetabs.HarshenTab;
import kenijey.harshencastle.enums.items.EnumPontusGateSpawnerParts;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PontusWorldGatePart extends BaseItemMetaData
{
	public PontusWorldGatePart()
	{
		setUnlocalizedName("pontus_world_gate_part");
		setRegistryName("pontus_world_gate_part");		
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("gatepart1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
	}

	@Override
	protected String[] getNames() {
		return EnumPontusGateSpawnerParts.getNames();
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}

}
