package kenijey.harshencastle.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PontusWorldGatePart3 extends Item
{
	public PontusWorldGatePart3()
	{
		setUnlocalizedName("pontus_world_gate_part_3");
		setRegistryName("pontus_world_gate_part_3");
		
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("gatepart1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("gatepart2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
	}

}
