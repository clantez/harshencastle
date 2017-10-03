package kenijey.harshencastle.base;

import java.util.List;

import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.objecthandlers.EntityThrowLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public abstract class BaseHarshenStaff extends Item
{
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		boolean flag = canItemBeUsed(worldIn, playerIn, handIn);
		if(flag && getMaxItemUseDuration(playerIn.getHeldItem(handIn)) > 0)
			playerIn.setActiveHand(handIn);
		if(getMaxItemUseDuration(playerIn.getHeldItem(handIn)) < 0)
			onItemUseFinish(playerIn.getHeldItem(handIn), worldIn, playerIn);
		return new ActionResult<ItemStack>(flag ? EnumActionResult.SUCCESS : EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	@Override
	public abstract int getMaxItemUseDuration(ItemStack stack);
	
	protected int getToolTipLines(ItemStack stack)
	{
		return 2;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		for(int i = 0; i < getToolTipLines(stack); i++)
			tooltip.add("\u00A7b" + new TextComponentTranslation(getRegistryName().getResourcePath() + i).getFormattedText());
	}

	
	@Override
	public abstract ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving);
	
	protected EntityThrown spawnThrownEntity(World worldIn, EntityLivingBase entityLiving, float speed, ItemStack stack, EntityThrown.HitResult hitResult)
	{
		return spawnInWorld(worldIn, new EntityThrown(worldIn, entityLiving, hitResult, stack), entityLiving, speed);
	}
	
	protected EntityThrown spawnThrownEntity(World worldIn, EntityLivingBase entityLiving, float speed, int resourceID, EntityThrown.HitResult hitResult)
	{
		return spawnInWorld(worldIn, new EntityThrown(worldIn, entityLiving, hitResult, new EntityThrowLocation(resourceID)), entityLiving, speed);
	}
	
	private EntityThrown spawnInWorld(World worldIn, EntityThrown thrown, EntityLivingBase entityLiving, float speed)
	{
		thrown.setHeadingFromThrower(entityLiving, entityLiving.rotationPitch, entityLiving.rotationYaw, 0.0F, speed, 0f);
		worldIn.spawnEntity(thrown);
		return thrown;
	}
	
	protected boolean canItemBeUsed(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		return true;
	}
}
