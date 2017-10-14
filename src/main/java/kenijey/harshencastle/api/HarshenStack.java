package kenijey.harshencastle.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
/**
 * A custom class used for handling ItemStacks and OreDicionary. 
 * Each HarshenStack will represent the different items that can be used to represent one itemstack.
 * 
 * <p>
 * For example a recipe that could take the inputs of:
 *<br>+A carrot
 *<br>+A stone sword
 *<br>+either a gold pickaxe or a diamond pickaxe.
 *<br>would need {@link HarshenStack}, the first {@link HarshenStack} being a carrot, the second a stone sword 
 * and the last {@link HarshenStack} being the Gold Pickaxe and Diamond Pickaxe
 * @author Wyn Price
 *
 */
public class HarshenStack 
{
	/**The list containing all the stacks that this class represents*/
	private ArrayList<ItemStack> stackList = new ArrayList<>();
	
	/**Should ItemStacks be dependant on NBT*/
	private boolean dependOnNBT = false;
	
	/**
	 * Used to create lists of stacks
	 * @param stacks the list of stacks. Can have meta of {@link OreDictionary#WILDCARD_VALUE}
	 */
	public HarshenStack(ItemStack... stacks) 
	{
		for(ItemStack stack : stacks)
			stackList.add(stack);
	}
	
	/**
	 * Used to create a list of stacks, from oreDictionary
	 * @param oreDictName The OreDictionary value you want to use
	 */
	public HarshenStack(String oreDictName) {
		NonNullList<ItemStack> stackList = OreDictionary.getOres(oreDictName);
		if(stackList.isEmpty())
		{
			stackList.add(ItemStack.EMPTY);
			new IllegalArgumentException("Oredictionary vaule " + oreDictName + " doesnt exist").printStackTrace(System.out);
		}
		else
			for(ItemStack stack : stackList)
				if(stack.getMetadata() == OreDictionary.WILDCARD_VALUE)
				{
			    	NonNullList<ItemStack> innerStacklist = NonNullList.create();
			    	stack.getItem().getSubItems(CreativeTabs.SEARCH, innerStacklist);
					for(ItemStack wildStack : innerStacklist)
						this.stackList.add(stack.copy());
				}
				else
					this.stackList.add(stack);
	}
	
	private HarshenStack(ArrayList<ItemStack> stackList)
	{
		this.stackList = new ArrayList<>(stackList);
	}
	
	/**
	 * Clones the {@link HarshenStack}
	 */
	public HarshenStack clone()
	{
		return new HarshenStack(getStackList());
	}
	
	/**
	 * Sets if the {@link HarshenStack} should depend on NBT
	 * @param dependOnNBT what to to set {@link HarshenStack#dependOnNBT} to
	 * @return itself
	 */
	public HarshenStack setDependOnNBT(boolean dependOnNBT)
	{
		this.dependOnNBT = dependOnNBT;
		return this;
	}
	
	/**
	 * Does the {@link HarshenStack} depend on NBT
	 */
	public boolean isDependOnNBT() {
		return dependOnNBT;
	}
	
	
	/**
	 * Gets a new instance of a list of all the available stacks.
	 */
	public ArrayList<ItemStack> getStackList() {
		return new ArrayList<>(stackList);
	}
	
	/**
	 * Can the input stack be used as an input item 
	 * @param stack the stack to test with
	 * @return true if the stack can be used, false if not
	 */
	public boolean containsItem(ItemStack stack)
	{
		for(ItemStack innerStack :  getStackList())
			if((innerStack.isItemEqual(stack) && (dependOnNBT ? ItemStack.areItemStackShareTagsEqual(innerStack, stack) : true)) ||
					(innerStack.isEmpty() && stack.isEmpty()))
				return true;
		return false;
	}
	
	
	//Below are old versions used so I can test without changing EVERYTHING
	
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Block blockIn){this(blockIn, 1);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Block blockIn, int amount){this(blockIn, amount, 0);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Block blockIn, int amount, int meta){this(Item.getItemFromBlock(blockIn), amount, meta);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Item itemIn){this(itemIn, 1);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Item itemIn, int amount){this(itemIn, amount, 0);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Item itemIn, int amount, int meta){this(itemIn, amount, meta, null);}
	/**@deprecated For old code. You need to change this*/
	@Deprecated
	public HarshenStack(Item itemIn, int amount, int meta, @Nullable NBTTagCompound capNBT)
	{
		this.stackList.add(new ItemStack(itemIn, amount, meta, capNBT));
	}
}
