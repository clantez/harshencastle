package kenijey.harshencastle.blocks;

import java.util.Arrays;

import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.UniversalBucket;

public class HereticCauldron extends BlockCauldron
{
	
	public static PropertyEnum<EnumLiquid> LIQUID =  PropertyEnum.<EnumLiquid>create("liquid_type", EnumLiquid.class);
	
	public HereticCauldron() {
		setRegistryName("heretic_cauldron");
		setUnlocalizedName("heretic_cauldron");
		this.setDefaultState(this.blockState.getBaseState().withProperty(LIQUID, EnumLiquid.NONE));
	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        int i = ((Integer)state.getValue(LEVEL)).intValue();
        if (itemstack.isEmpty())
        {
            return false;
        }
        if(item instanceof UniversalBucket)
        {
                 String[] stList = itemstack.serializeNBT().getTag("tag").toString().split(":");
                 int occurance = 0;
                 String s = "";
                 for(char c : stList[1].toCharArray())
                 {
                 	if(c == '"'){
                 		occurance++; continue;}
                 	if(occurance == 2)
                 		break;
                 	s += c;
                 }
                 if (Arrays.asList("harshen_dimensional_fluid harshing_water".split(" ")).contains(s))
                 {
                     if (i < 3 && !worldIn.isRemote)
                     {
                         if (!playerIn.capabilities.isCreativeMode)
                         {
                             playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
                         }

                         this.setWaterLevel(worldIn, pos, state, 3);
                         worldIn.setBlockState(pos, state.withProperty(LIQUID, EnumLiquid.getMatch(s)).withProperty(LEVEL, 3), 2);
                         worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                     }
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

//                        if (itemstack.isEmpty())//TODO
//                        {
//                            playerIn.setHeldItem(hand, new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket));
//                        }
//                        else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket)))
//                        {
//                            playerIn.dropItem(new ItemStack(HarshenItems.harshen_dimensional_fluid_bucket), false);
//                        }
                    }
                    this.setWaterLevel(worldIn, pos, state, 0);
                    worldIn.setBlockState(pos, state.withProperty(LIQUID, EnumLiquid.NONE), 2);
                    worldIn.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }

                return true;
            }
            else
            	return false;
    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		if(state.getValue(LIQUID) == EnumLiquid.NONE || state.getValue(LEVEL) == 0)
			return 0;
		return (state.getValue(LIQUID).getId() * 3) - 3 + super.getMetaFromState(state);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		if(meta == 0)
			return this.getDefaultState();
		return this.getDefaultState().withProperty(LEVEL, ((meta - 1) % 3) + 1).withProperty(LIQUID, EnumLiquid.getMatch((int) Math.ceil(meta / 3)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LIQUID, LEVEL});
	}
	
	public static enum EnumLiquid implements IStringSerializable
	{
		NONE("none", 0),
		HARSHING_WATER("harshing_water", 1),
		HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", 2);
		
		private final String name;
		private final int id;
		
		EnumLiquid(String name, int id)
		{
			this.name = name;
			this.id = id;
		}
		
		@Override
		public String getName() {
			return this.name;
		}
		
		public int getId(){
			return this.id;
		}
		
		public static EnumLiquid getMatch(int id)
		{
			for(EnumLiquid liquid : EnumLiquid.values())
				if(liquid.getId() == id)
					return liquid;
			return NONE;
		}
		
		public static EnumLiquid getMatch(String name)
		{
			for(EnumLiquid liquid : EnumLiquid.values())
				if(liquid.getName().equals(name))
					return liquid;
			return NONE;
		}
		
		
	}
	
	
}
