package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HereticCauldron extends BlockCauldron
{
	public HereticCauldron() {
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);

        if (itemstack.isEmpty())
        {
            return true;
        }
        else
        {
            int i = ((Integer)state.getValue(LEVEL)).intValue();
            Item item = itemstack.getItem();

            if (item == HarshenItems.harshen_dimensional_fluid_bucket)
            {
                if (i < 3 && !worldIn.isRemote)
                {
                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                    }

                    this.setWaterLevel(worldIn, pos, state, 3);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            }
            else if (item == Items.BUCKET)
            {
                if (i == 3 && !worldIn.isRemote)
                {
                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        itemstack.shrink(1);

                        if (itemstack.isEmpty())
                        {
                            playerIn.setHeldItem(hand, new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket));
                        }
                        else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket)))
                        {
                            playerIn.dropItem(new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket), false);
                        }
                    }
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            }
            else
            	return false;
        }
    }
}
