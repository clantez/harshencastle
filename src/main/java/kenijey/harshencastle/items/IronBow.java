package kenijey.harshencastle.items;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IronBow extends ItemBow
{
	public IronBow()
	{
		setUnlocalizedName("iron_bow");
		setRegistryName("iron_bow");
		this.setMaxDamage(1388);
		//this.getMaxItemUseDuration(1100.0F);
		
		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    return entityIn.getActiveItemStack().getItem() != HarshenItems.iron_bow ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 15.0F;
                }
            }
        });
	}
	

}
