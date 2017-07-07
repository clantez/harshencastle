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

public class ItiumOre extends Block
{
	public ItiumOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("itium_ore");
        setRegistryName("itium_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(31f);
		setResistance(100f);
		setCreativeTab(HarshenCastle.harshenTab);
    }

	private BlockPos pos;
	private World world;
	
	
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
			((WorldServer)world).spawnParticle(EnumParticleTypes.SMOKE_NORMAL, false, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 6,  0.5, 0.5, 0.6, 0, new int[EnumParticleTypes.SMOKE_NORMAL.getArgumentCount()]);
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
}
