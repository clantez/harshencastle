package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.items.GlassContainerValue;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
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
		if(!HarshenCastle.hasLoaded) return "item.glasscontainer"; //Used to prevent the game from crashing during startup
		String args = getGlassContaining(GlassContainerValue.getContainerFromMeta(stack.getMetadata()).getType());
		if(args != null)
			stack.setStackDisplayName(TextFormatting.RESET + new TextComponentTranslation(stack.getItem().getUnlocalizedName() + 
					".container", args).getFormattedText());
		for(int i = 0; i < getNames().length; i ++)
			if(stack.getItemDamage() == i)
				return this.getUnlocalizedName() + "." + getNames()[i];
		return this.getUnlocalizedName() + "." + "illegal";
	}
	
	public static String getGlassContaining(CauldronLiquid liquid)
	{
		if(liquid == null)
			return null;
		String s = liquid.hasState() && !((IBlockState)liquid.getStateOrLoc()).getBlock().getLocalizedName().equals("tile.null.name") ? 
				((IBlockState)liquid.getStateOrLoc()).getBlock().getLocalizedName() :  new TextComponentTranslation("fluid." + liquid.getName().split(":")[1]).getUnformattedText(); 
		if(s.split(" ").length > 2 && s.split(" ")[0].equalsIgnoreCase("block") && s.split(" ")[1].equalsIgnoreCase("of"))
		{
			String s1 = "";
			for(int i1 = 2; i1 < s.split(" ").length; i1++)
				s1 += s.split(" ")[i1];
			s = new TextComponentTranslation("liquid").getUnformattedText() + " " + s1;
		}
		return s;
	}
	
	@Override
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(!hasDrinkEffect(playerIn, handIn))
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return stack.getMetadata() == 0 ? 64 : this.maxStackSize;
	}
	
	private boolean hasDrinkEffect(EntityPlayer playerIn, EnumHand handIn)
	{
		return hasDrinkEffect(playerIn.getHeldItem(handIn).getMetadata());
	}
	
	private boolean hasDrinkEffect(int meta)
	{
		return GlassContainerValue.getContainerFromMeta(meta).getEffects() != null;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		GlassContainerValue enu = GlassContainerValue.getContainerFromMeta(stack.getMetadata());
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
		return GlassContainerValue.getNames();
	}
	
}
