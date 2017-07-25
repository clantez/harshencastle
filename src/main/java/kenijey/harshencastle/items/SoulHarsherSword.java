package kenijey.harshencastle.items;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class SoulHarsherSword extends ItemSword
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("soul_harsher_sword", 3, 2000, 13.5f, 13f, 30);
	public SoulHarsherSword()
	{
		super(toolMaterial);
		setUnlocalizedName("soul_harsher_sword");
		setRegistryName("soul_harsher_sword");
		setNoRepair();
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(playerIn.isElytraFlying() && !worldIn.isRemote)
		{
			playerIn.getHeldItem(handIn).damageItem(25, playerIn);
			worldIn.spawnEntity(new EntityFireworkRocket(worldIn, new ItemStack(Items.FIREWORKS), playerIn));
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));

		}
			
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("sword1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("sword2").getFormattedText());
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00a74" + new TextComponentTranslation("sword3").getFormattedText());
		tooltip.add("\u00a74" + new TextComponentTranslation("sword4").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
