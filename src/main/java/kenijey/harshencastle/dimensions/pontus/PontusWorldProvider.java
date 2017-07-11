package kenijey.harshencastle.dimensions.pontus;

import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.dimensions.DimensionPontus;
import net.minecraft.entity.Entity;
import net.minecraft.init.Biomes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PontusWorldProvider extends WorldProvider 
{
	
	public static String worldPreset = "{\"coordinateScale\":676.94366,\"heightScale\":684.412,\"lowerLimitScale\":512.0,\""
			+ "upperLimitScale\":512.0,\"depthNoiseScaleX\":200.0,\"depthNoiseScaleZ\":200.0,\"depthNoiseScaleExponent\""
			+ ":0.5,\"mainNoiseScaleX\":80.0,\"mainNoiseScaleY\":300.0,\"mainNoiseScaleZ\":80.0,\"baseSize\":8.5,\"stretchY\""
			+ ":24.0,\"biomeDepthWeight\":1.0,\"biomeDepthOffset\":0.0,\"biomeScaleWeight\":1.0,\"biomeScaleOffset\":0.0,\""
			+ "seaLevel\":56,\"useCaves\":true,\"useDungeons\":false,\"dungeonChance\":1,\"useStrongholds\":false,\"useVillages\""
			+ ":false,\"useMineShafts\":false,\"useTemples\":false,\"useMonuments\":false,\"useMansions\":true,\"useRavines\""
			+ ":true,\"useWaterLakes\":true,\"waterLakeChance\":1,\"useLavaLakes\":false,\"lavaLakeChance\":10,\"useLavaOceans\""
			+ ":false,\"fixedBiome\":-1,\"biomeSize\":1,\"riverSize\":4,\"dirtSize\":33,\"dirtCount\":10,\"dirtMinHeight\":44,\""
			+ "dirtMaxHeight\":256,\"gravelSize\":1,\"gravelCount\":0,\"gravelMinHeight\":0,\"gravelMaxHeight\":0,\"graniteSize\""
			+ ":33,\"graniteCount\":10,\"graniteMinHeight\":0,\"graniteMaxHeight\":255,\"dioriteSize\":1,\"dioriteCount\":0,\""
			+ "dioriteMinHeight\":0,\"dioriteMaxHeight\":0,\"andesiteSize\":1,\"andesiteCount\":0,\"andesiteMinHeight\":0,\""
			+ "andesiteMaxHeight\":0,\"coalSize\":1,\"coalCount\":1,\"coalMinHeight\":0,\"coalMaxHeight\":61,\"ironSize\":1,\""
			+ "ironCount\":0,\"ironMinHeight\":0,\"ironMaxHeight\":0,\"goldSize\":1,\"goldCount\":0,\"goldMinHeight\":0,\""
			+ "goldMaxHeight\":0,\"redstoneSize\":1,\"redstoneCount\":0,\"redstoneMinHeight\":0,\"redstoneMaxHeight\":0,\""
			+ "diamondSize\":1,\"diamondCount\":0,\"diamondMinHeight\":0,\"diamondMaxHeight\":0,\"lapisSize\":1,\"lapisCount\":0,\""
			+ "lapisCenterHeight\":0,\"lapisSpread\":0}";
	
	@Override
	protected void init() {
		this.hasSkyLight = true;
		NBTTagCompound nbttagcompound = this.world.getWorldInfo().getDimensionData(DimensionType.OVERWORLD);
		this.biomeProvider = new BiomeProviderSingle(HarshenBiomes.pontus_dimensional_biome);

	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new PontusChunkProvider(this.world, this.getSeed(), worldPreset);
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)
	{
		return 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return true;
	}

	@Override
	public boolean canRespawnHere()
	{
		return false;
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight()
	{
		return 8.0F;
	}

	@Override
	public boolean canCoordinateBeSpawn(int x, int z)
	{
		return false;
	}

	@Override
	public int getAverageGroundLevel()
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int x, int z)
	{
		return false;
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionPontus.PONTUS_DIMENSION;
	}
	
	@Override
	public void onWorldSave() {
		
	}

	@Override
	public void onWorldUpdateEntities() {
		
	}

	@Override
	public int getHeight()
	{
		return 256;
	}

	@Override
	public int getActualHeight()
	{
		return 256;
	}
    
    @Override
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
    	return new Vec3d(0.5, 0, 0);
    }

}