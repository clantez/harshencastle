package kenijey.harshencastle.internal;

import java.util.HashMap;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.BlockItem;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenHelper;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.api.IHarshenRegistry;
import kenijey.harshencastle.objecthandlers.VanillaProviderToInterface;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import kenijey.harshencastle.recipies.MagicTableRecipe;
import kenijey.harshencastle.recipies.PedestalSlabRecipes;
import kenijey.harshencastle.recipies.RitualRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

public class HarshenRegistry implements IHarshenRegistry
{
	
	private static final HashMap<ItemStack, CauldronLiquid> INPUT_MAP = new HashMap<>();
	private static final HashMap<CauldronLiquid, ItemStack> OUTPUT_MAP = new HashMap<>();
	private static final HashMap<ItemStack, CauldronLiquid> ITEMLIQUID_MAP = new HashMap<>();
	
	private static final HashMap<Fluid, CauldronLiquid> FORGE_BUCKET_MAP = new HashMap<>();
	private static final HashMap<Fluid, Integer> FORGE_BUCKET_AMOUNT_MAP = new HashMap<>();

	private final String modID;
	
	public HarshenRegistry(String modID) 
	{
		this.modID = modID;
	}
	
	@Override
	public void registerRitualRecipe(ItemStack output, boolean useLightning, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4) {
		RitualRecipes.addRecipe(HarshenUtils.toArray(input1, input2, input3, input4), output, useLightning);
	}

	@Override
	public void registerCauldronRecipe(HarshenStack input, ItemStack output, CauldronLiquid liquid) {
		CauldronRecipes.addRecipe(input, output, liquid);
	}

	@Override
	public void registerHereticRecipe(HarshenStack cauldronItem, ItemStack output, CauldronLiquid catalyst,  HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4, 
			 HarshenStack input5, HarshenStack input6, HarshenStack input7, HarshenStack input8) {
		HereticRitualRecipes.addRecipe(cauldronItem, output, catalyst, HarshenUtils.listOf(input1, input2, input3, input4, input5, input6, input7, input8));
	}

	@Override
	public void registerPedestalSlabRecipe(HarshenStack input, ItemStack output) {
		PedestalSlabRecipes.addRecipe(input, output);
	}

	@Override
	public void registerMagicTableRecipe(ItemStack output, HarshenStack input1, HarshenStack input2, HarshenStack input3, HarshenStack input4) {
			MagicTableRecipe.addRecipe(HarshenUtils.toArray(input1, input2, input3, input4), output);
	}

	@Override
	public CauldronLiquid registerCauldronLiquid(ItemStack fullItem, ItemStack emptyItem, CauldronLiquid liquid, int fillBy) {
		fillBy = MathHelper.clamp(fillBy, 1, 3);
		liquid.setModID(modID);
		if(emptyItem == null || emptyItem.isEmpty())
			return registerItemLiquid(fullItem, liquid).setFillBy(fillBy);
		liquid.setFillBy(fillBy);
		INPUT_MAP.put(fullItem, liquid);
		OUTPUT_MAP.put(liquid, emptyItem);
		return liquid;
	}
	
	@Override
	public CauldronLiquid registerCauldronLiquid(FluidStack fluid, CauldronLiquid liquid, int fillBy) {
		liquid.setModID(modID);
		liquid.setFillBy(fillBy);
		FORGE_BUCKET_MAP.put(fluid.getFluid(), liquid);
		FORGE_BUCKET_AMOUNT_MAP.put(fluid.getFluid(), fluid.amount);
		return liquid;
	}
	
	private CauldronLiquid registerItemLiquid(ItemStack inputItem, CauldronLiquid liquid)
	{
		CauldronLiquid l = liquid.hasState() ? new CauldronLiquid(liquid.getName().split(":")[1], (IBlockState)liquid.getStateOrLoc()).setModID(liquid.getName().split(":")[0])
				: new CauldronLiquid(liquid.getName().split(":")[1], (ResourceLocation) liquid.getStateOrLoc()).setModID(liquid.getName().split(":")[0]);
		ITEMLIQUID_MAP.put(inputItem, l);
		return l;
	}

	public static CauldronLiquid getLiquidFromStack(ItemStack key)
	{
		if(key.getItem() instanceof UniversalBucket && FluidStack.loadFluidStackFromNBT(key.getTagCompound()) != null &&
				FORGE_BUCKET_MAP.containsKey(FluidStack.loadFluidStackFromNBT(key.getTagCompound()).getFluid()))
			return FORGE_BUCKET_MAP.get(FluidStack.loadFluidStackFromNBT(key.getTagCompound()).getFluid());
		return HarshenUtils.getObjectFromItemMap(HarshenUtils.getObjectFromItemMap(INPUT_MAP, key) != null ? INPUT_MAP : ITEMLIQUID_MAP, key);
	}
	
	public static ItemStack getOutPutItem(CauldronLiquid liquid)
	{
		if(FORGE_BUCKET_MAP.containsValue(liquid))
			return new ItemStack(Items.BUCKET);
		else
			for(CauldronLiquid l : OUTPUT_MAP.keySet())
				if(l == liquid)
					return OUTPUT_MAP.get(l).copy(); 
		return null;
	}
	
	public static ItemStack getInputFromOutput(CauldronLiquid prevLiquid)
	{
		for(Fluid fluid : FORGE_BUCKET_MAP.keySet())
			if(FORGE_BUCKET_MAP.get(fluid) == prevLiquid)
				return FluidUtil.getFilledBucket(new FluidStack(fluid, FORGE_BUCKET_AMOUNT_MAP.get(fluid)));
		for(ItemStack stack : INPUT_MAP.keySet())
			if(INPUT_MAP.get(stack) == prevLiquid)
				return stack.copy();
		return ItemStack.EMPTY;
	}
	
	public static CauldronLiquid getRelativeFluid(CauldronLiquid prevLiquid)
	{
		for(ItemStack stack : INPUT_MAP.keySet())
			if(INPUT_MAP.get(stack).getName().equals(prevLiquid.getName()))
				return INPUT_MAP.get(stack);
		return prevLiquid;
	}

	@Override
	public void registerInventoryItem(BlockItem item, EnumInventorySlots slot, Object provider, boolean multiplyEvent,
			int toolTipLines) 
	{		
		registerInventoryItem(item, new VanillaProviderToInterface(slot, provider, multiplyEvent, toolTipLines));
	}

	@Override
	public void registerInventoryItem(BlockItem item, IHarshenProvider provider) {
		HarshenUtils.registerInventoryItem(item, provider);
	}

	@Override
	public IHarshenHelper getHelper() {
		return new HarshenHelper();
	}
	
	public static class HarshenHelper implements IHarshenHelper
	{
	}
}
