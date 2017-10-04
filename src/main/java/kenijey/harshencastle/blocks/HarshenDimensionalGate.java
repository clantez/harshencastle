package kenijey.harshencastle.blocks;

import java.util.List;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalGate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class HarshenDimensionalGate extends Block implements ITileEntityProvider
{

	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	public static final PropertyBool FOREVER = PropertyBool.create("ignore_countdown");

	
	public HarshenDimensionalGate() {
		super(Material.ROCK);
		setRegistryName("harshen_dimensional_gate");
		setUnlocalizedName("harshen_dimensional_gate");
		setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, false).withProperty(FOREVER, false));
		setHardness(10f);
		setResistance(50f);
	}
	
	public void deactivate(World worldIn, BlockPos pos)
	{
		worldIn.setBlockState(pos, this.getDefaultState(), 3);
		//sound?
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		if(entityIn instanceof EntityLivingBase)
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
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(!worldIn.isRemote)
			if(((Boolean)state.getValue(ACTIVE)).booleanValue())
			{
				Boolean goHome = playerIn.dimension == DimensionPontus.DIMENSION_ID;
				if(playerIn instanceof EntityPlayerMP)
					if(goHome) 
						transferPlayerToDimension((EntityPlayerMP) playerIn, 0, false, pos);
					else
						transferPlayerToDimension((EntityPlayerMP) playerIn, DimensionPontus.DIMENSION_ID, true, pos);
			}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
	}
	
	public void transferPlayerToDimension(EntityPlayerMP player, int dimensionIn, boolean placeBlock, BlockPos pos)
    {
        int i = player.dimension;
        WorldServer worldserver = player.mcServer.getWorld(player.dimension);
        player.dimension = dimensionIn;
        WorldServer worldserver1 = player.mcServer.getWorld(player.dimension);
        player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        player.mcServer.getPlayerList().updatePermissionLevel(player);
        worldserver.removeEntityDangerously(player);
        player.isDead = false;
        transferPlayerToWorld(player, i, worldserver, worldserver1, placeBlock, pos);
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
	
	public void transferPlayerToWorld(Entity entityIn, int lastDimension, WorldServer oldWorldIn, WorldServer toWorldIn, boolean placeBlock, BlockPos pos)
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

            if (entityIn.isEntityAlive())
            {
            	int y = toWorldIn.getTopSolidOrLiquidBlock(pos).getY();
            	BlockPos p = new BlockPos(pos.getX(), y, pos.getZ());
                entityIn.setLocationAndAngles(p.getX(), p.getY(), p.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
                if(placeBlock && !toWorldIn.getBlockState(p.add(0, -1, 0)).equals(HarshenBlocks.HARSHEN_DIMENSIONAL_GATE.getDefaultState().withProperty(HarshenDimensionalGate.ACTIVE, true)))
                	toWorldIn.setBlockState(p.add(0, -1, 0), getStateFromMeta(3), 3);
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
        return this.getDefaultState().withProperty(ACTIVE, Boolean.valueOf(Math.floorDiv(meta, 2) == 1)).withProperty(FOREVER, meta % 2 == 1);
    }

    public int getMetaFromState(IBlockState state)
    {
        return (((Boolean)state.getValue(FOREVER)).booleanValue() ? 1 : 0) + (((Boolean)state.getValue(ACTIVE)).booleanValue() ? 0 : 2);
    }
    

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {ACTIVE, FOREVER});
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityHarshenDimensionalGate();
	}

}
