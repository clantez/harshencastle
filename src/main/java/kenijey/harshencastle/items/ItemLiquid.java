package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.ItemLiquidTypeset;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemLiquid extends BaseItemMetaData
{

	public ItemLiquid() {
		setRegistryName("item_liquid");
		setUnlocalizedName("item_liquid");
	}
	
	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}
	
	public CauldronLiquid getLiquid(ItemStack stack)
	{
		return ItemLiquidTypeset.getFromMeta(stack.getMetadata()).getType();
	}

	@Override
	protected String[] getNames() {
		return ItemLiquidTypeset.getNames();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		stack.setStackDisplayName(getName(stack));
		return super.getUnlocalizedName(stack);
	}
	
	private String getName(ItemStack stack)
	{
		return TextFormatting.RESET + new TextComponentTranslation(getUnlocalizedName() + ".name", 
				GlassContainer.getGlassContaining(ItemLiquidTypeset.getFromMeta(stack.getMetadata()).getType())).getUnformattedText();
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation("itemliquid.rightclick").getFormattedText());
		tooltip.add(new TextComponentTranslation("itemliquid.rightliquid", GlassContainer.getGlassContaining(ItemLiquidTypeset.getFromMeta(stack.getMetadata()).getType())).getUnformattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
