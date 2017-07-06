package kenijey.harshencastle.blocks;

import java.util.List;
import java.util.Random;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.items.HarshenItems;
import kenijey.harshencastle.items.SoulHarsherPickaxe;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class HarshenSoulOre extends Block
{
	public HarshenSoulOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("harshen_soul_ore");
        setRegistryName("harshen_soul_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(31f);
		setResistance(100f);
		setCreativeTab(HarshenCastle.harshenTab);
		meta=0;
		least_quantity=1;
		most_quantity=1;
    }
	private Item drop;
	private int meta;
	private int least_quantity;
	private int most_quantity;
	private BlockPos pos;
	private World world;
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) 
	{
		ItemStack item = player.inventory.getCurrentItem();
		if(item.getItem() instanceof SoulHarsherPickaxe)
		{
			EntityItem ei = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(HarshenItems.harshen_soul_fragment, 1));
			if(!worldIn.isRemote)
			{
				worldIn.spawnEntity(ei);
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.success"));
			}	
		}
		else
		{
			player.attackEntityFrom(DamageSource.MAGIC, 21);
			if(!worldIn.isRemote)
			{
				player.sendMessage((ITextComponent) new TextComponentTranslation("message.failed"));
			}
		}

	}
	
	@Override
	public Item getItemDropped(IBlockState blockstate, Random random, int fortune) 
	{
	    return null;
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		this.pos = pos;
		this.world = worldIn;
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		if(world != null && pos != null)
		if (world instanceof WorldServer)
			((WorldServer)world).spawnParticle(EnumParticleTypes.CLOUD, false, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4,  0.7, 0.8, 0.6, 0, new int[EnumParticleTypes.CLOUD.getArgumentCount()]);
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("ore1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("ore2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
}
