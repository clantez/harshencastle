package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.blocks.CauldronLiquid;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class GlassContainer extends BaseItemMetaData
{
	public GlassContainer() {
		setRegistryName("glass_container");
		setUnlocalizedName("glass_container");
		setHasSubtypes(true);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.getHeldItem(hand).getMetadata() != 0)
			return EnumActionResult.PASS;
		if(pos.getY() == 0)
			player.setHeldItem(hand, new ItemStack(this, 1, 1));
		return EnumActionResult.SUCCESS;

	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String args = getGlassContaining(EnumGlassContainer.getContainerFromMeta(stack.getMetadata()).getType());
		if(args != null)
			stack.setStackDisplayName(TextFormatting.RESET + new TextComponentTranslation(stack.getItem().getUnlocalizedName() + 
					".container", args).getFormattedText());
		return super.getUnlocalizedName(stack);
	}
	
	public static String getGlassContaining(CauldronLiquid liquid)
	{
		if(liquid == null)
			return null;
		return liquid.hasState() ? 
				((IBlockState)liquid.getStateOrLoc()).getBlock().getLocalizedName() : 
					new TextComponentTranslation("fluid." + liquid.getName()).getUnformattedText(); 

	}
	
	@Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(!hasDrinkEffect(playerIn, handIn))
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	private boolean hasDrinkEffect(EntityPlayer playerIn, EnumHand handIn)
	{
		return hasDrinkEffect(playerIn.getHeldItem(handIn).getMetadata());
	}
	
	private boolean hasDrinkEffect(int meta)
	{
		return EnumGlassContainer.getContainerFromMeta(meta).getEffects() != null;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EnumGlassContainer enu = EnumGlassContainer.getContainerFromMeta(stack.getMetadata());
		if(enu.getEffects() != null)
			for(PotionEffect effect : enu.getEffects())
				entityLiving.addPotionEffect(effect);
		return new ItemStack(this);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return hasDrinkEffect(stack.getMetadata()) ? EnumAction.DRINK : EnumAction.NONE;
	}

	@Override
	protected List<Integer> getMetaForTab() {
		return null;
	}

	@Override
	protected String[] getNames() {
		return EnumGlassContainer.getNames();
	}
	
	
	
}
