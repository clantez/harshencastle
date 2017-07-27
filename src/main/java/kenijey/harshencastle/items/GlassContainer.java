package kenijey.harshencastle.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import kenijey.harshencastle.base.BaseItemMetaData;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlassContainer extends BaseItemMetaData
{
	
	public static HashMap<Integer, List<List<Object>>> effects = new HashMap<Integer, List<List<Object>>>();
	public GlassContainer() {
		setRegistryName("glass_container");
		setUnlocalizedName("glass_container");
		setHasSubtypes(true);
	}
	
	public static void initEffects()
	{
		effects.put(1, Arrays.asList(Arrays.asList(HarshenPotions.potionHarshed), Arrays.asList(600)));
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
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		if(playerIn.getHeldItem(handIn).getMetadata() == 0)
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));

        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		System.out.println(new PotionEffect((Potion)effects.get(stack.getMetadata()).get(0).get(0), (Integer)effects.get(stack.getMetadata()).get(1).get(0)));
		if(effects.containsKey(stack.getMetadata()))
			for(int i = 0; i < effects.get(stack.getMetadata()).get(0).size(); i++)
				entityLiving.addPotionEffect(new PotionEffect((Potion)effects.get(stack.getMetadata()).get(0).get(i), (Integer)effects.get(stack.getMetadata()).get(1).get(i)));
		return ItemStack.EMPTY;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
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
