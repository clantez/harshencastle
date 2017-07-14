package kenijey.harshencastle.items;
import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.potion.PotionEffect;
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
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(20), 150, 1));
		return super.onLeftClickEntity(stack, player, entity);
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
