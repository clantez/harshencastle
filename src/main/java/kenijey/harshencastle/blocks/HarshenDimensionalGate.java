package kenijey.harshencastle.blocks;

import java.util.List;

import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.dimensions.pontus.PontusTeleporter;
import kenijey.harshencastle.items.PontusWorldGateSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class HarshenDimensionalGate extends Block
{

	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public HarshenDimensionalGate() {
		super(Material.ROCK);
		setRegistryName("harshen_dimensional_gate");
		setUnlocalizedName("harshen_dimensional_gate");
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, Boolean.valueOf(false)));
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0625f, 0.0625f, 0.0625f, 0.9375f, 0.9375f, 0.9375f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 1, 0.0625, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 0f, 0f, 0.0625, 0.0625, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 0f, 0.9375f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 0f, 0f, 1f, 0.0625, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0f, 1,0.9375f, 0.0625));
		addCollisionBoxToList(pos, entityBox, collidingBoxes,  new AxisAlignedBB(0f, 1f, 0f, 0.0625, 0.9375f, 1));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0f, 1f, 0.9375f, 1f, 0.9375f, 1f));
		addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.9375f, 1f, 0f, 1f, 0.9375f, 1f));
								
	}
		
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(state == getStateFromMeta(1))
		{
			Boolean goHome = playerIn.dimension == DimensionPontus.DIMENSION_ID;
			if(playerIn instanceof EntityPlayerMP)
				if(goHome) 
					transferPlayerToOverWorld((EntityPlayerMP) playerIn, 0);
				else
					((EntityPlayerMP)playerIn).mcServer.getPlayerList().transferPlayerToDimension((EntityPlayerMP) playerIn, DimensionPontus.DIMENSION_ID, new PontusTeleporter(Minecraft.getMinecraft().getIntegratedServer().getServer().getWorld(DimensionPontus.DIMENSION_ID)));
		}
		else if(playerIn.getHeldItemMainhand().getItem() instanceof PontusWorldGateSpawner || (playerIn.getHeldItemMainhand().getItem().equals(Item.getItemFromBlock(Blocks.AIR)) && playerIn.getHeldItemOffhand().getItem() instanceof PontusWorldGateSpawner))
			worldIn.setBlockState(pos, getStateFromMeta(1), 3);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	public void transferPlayerToOverWorld(EntityPlayerMP player, int dimensionIn)
    {
        int i = player.dimension;
        WorldServer worldserver = player.mcServer.getWorld(player.dimension);
        player.dimension = dimensionIn;
        WorldServer worldserver1 = player.mcServer.getWorld(player.dimension);
        player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        player.mcServer.getPlayerList().updatePermissionLevel(player);
        worldserver.removeEntityDangerously(player);
        player.isDead = false;
        transferEntityToHome(player, i, worldserver, worldserver1);
        player.mcServer.getPlayerList().preparePlayer(player, worldserver);
        player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.interactionManager.setWorld(worldserver1);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
        player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldserver1);
        player.mcServer.getPlayerList().syncPlayerInventory(player);

        for (PotionEffect potioneffect : player.getActivePotionEffects())
        {
            player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
        }
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, i, dimensionIn);
    }
	
	public void transferEntityToHome(Entity entityIn, int lastDimension, WorldServer oldWorldIn, WorldServer toWorldIn)
    {
        net.minecraft.world.WorldProvider pOld = oldWorldIn.provider;
        net.minecraft.world.WorldProvider pNew = toWorldIn.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = entityIn.posX * moveFactor;
        double d1 = entityIn.posZ * moveFactor;
        double d2 = 8.0D;
        float f = entityIn.rotationYaw;
        oldWorldIn.profiler.startSection("moving");

        if (entityIn.dimension == 1)
        {
            BlockPos blockpos;

            if (lastDimension == 1)
            {
                blockpos = toWorldIn.getSpawnPoint();
            }
            else
            {
                blockpos = toWorldIn.getSpawnCoordinate();
            }

            d0 = (double)blockpos.getX();
            entityIn.posY = (double)blockpos.getY();
            d1 = (double)blockpos.getZ();
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, 90.0F, 0.0F);

            if (entityIn.isEntityAlive())
            {
                oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
        }

        oldWorldIn.profiler.endSection();

        if (lastDimension != 1)
        {
            oldWorldIn.profiler.startSection("placing");
            d0 = (double)MathHelper.clamp((int)d0, -29999872, 29999872);
            d1 = (double)MathHelper.clamp((int)d1, -29999872, 29999872);

            if (entityIn.isEntityAlive())
            {
                entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
                toWorldIn.spawnEntity(entityIn);
                toWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }

            oldWorldIn.profiler.endSection();
        }

        entityIn.setWorld(toWorldIn);
    }
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(ACTIVE, Boolean.valueOf(meta == 1));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((Boolean)state.getValue(ACTIVE)).booleanValue() ? 1 : 0;
    }
    

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE});
    }

}
