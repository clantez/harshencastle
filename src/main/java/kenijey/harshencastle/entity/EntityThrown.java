package kenijey.harshencastle.entity;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseHarshenParticle;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.objecthandlers.EntityThrowLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityThrown extends EntityThrowable
{
	private EntityThrowLocation location;
	private ItemStack stack;
	private final HitResult hitResult;
	
	private boolean ignoreBlocks = false;
	
	public EntityThrown(World worldIn) {
		super(worldIn);
		hitResult = new HitResult() {@Override public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer) {}};
	}
	
	public EntityThrown(World worldIn, EntityLivingBase throwerIn, HitResult hitResult, EntityThrowLocation location) {
		super(worldIn, throwerIn);
		this.location = location;
		this.hitResult = hitResult;
	}
	
	public EntityThrown(World worldIn, EntityLivingBase throwerIn, HitResult hitResult, ItemStack stack) {
		super(worldIn, throwerIn);
		this.stack = stack;
		this.hitResult = hitResult;
	}
	
	public boolean isLocation()
	{
		return this.location != null;
	}
	
	public EntityThrowLocation getLocation() {
		return location;
	}
	
	public ItemStack getStack() {
		return stack == null ? ItemStack.EMPTY : stack;
	}
	
	public EntityThrown setIgnoreBlocks(boolean ignoreBlocks) {
		this.ignoreBlocks = ignoreBlocks;
		return this;
	}
	
	public boolean isIgnoreBlocks() {
		return ignoreBlocks;
	}

	@Override
	protected void onImpact(RayTraceResult result) { 
		hitResult.onHit(this, result, !world.isRemote);
		if(!ignoreBlocks)
			this.setDead();
	}
	
	@Override
	public void setDead() {
		if(world.isRemote)
		for(int i = 0; i < 16; i++)
			try
			{
				((BaseHarshenParticle)HarshenCastle.proxy.spawnParticle(isLocation() ? EnumHarshenParticle.CAULDRON : EnumHarshenParticle.ITEM, new Vec3d(this.posX, this.posY, this.posZ),
						new Vec3d(((double)this.rand.nextFloat() - 0.5D) / 30D, ((double)this.rand.nextFloat() - 0.5D) / 30D, ((double)this.rand.nextFloat() - 0.5D) / 30D),
						3f, false, isLocation() ? location : stack)).setParticleGravity(1f);
			}
			catch (NullPointerException e) {
				continue;
			}
		super.setDead();
	}
	
	@Override
	public void onUpdate() {
		noClip = ignoreBlocks;
		super.onUpdate();
		noClip = false;
	}
	
	public interface HitResult {
		public void onHit(EntityThrown entity, RayTraceResult result, boolean isServer);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.ignoreBlocks = compound.getBoolean("ignore_blocks");
		this.location = compound.getInteger("location_id") > 0 ? new EntityThrowLocation(compound.getInteger("location_id")) : null;
		this.stack = new ItemStack(compound.getCompoundTag("inner_stack"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setBoolean("ignore_blocks", this.ignoreBlocks);
		compound.setInteger("location_id", isLocation() ? location.getId() : -1);
		compound.setTag("inner_stack", (this.stack == null ? ItemStack.EMPTY : this.stack).serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public boolean isInRangeToRender3d(double x, double y, double z) {
		return true;
	}
	
	@Override
	public boolean shouldRenderInPass(int pass) {
		return true;
	}
}
