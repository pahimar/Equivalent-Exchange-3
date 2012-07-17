package ee3.emc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.ShapedRecipes;
import net.minecraft.src.ShapelessRecipes;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EMCList {

	private HashMap<Integer, HashMap<Integer, EMCValue>> emcMap;
	
	public EMCList() {
		emcMap = new HashMap<Integer, HashMap<Integer, EMCValue>>();
	}
	
	public void initEMCList() {
		determineBaseMaterials();
	}


	/* Helper functions */

	private static void determineBaseMaterials() {
		CraftingManager instance = CraftingManager.getInstance();
		List recipeList = instance.getRecipeList();

		IRecipe recipe;
		ShapedRecipes shapedRecipe;
		ShapelessRecipes shapelessRecipe;
		
		ItemStack[] shapedInputs;
		List<ItemStack> shapelessInputs;
		
		ItemStack recipeOutput = null;
		Iterator<IRecipe> recipeIter = recipeList.iterator();
		
		ItemStack[] recipeInputs = null;
		Vector<Integer> inputs = new Vector<Integer>();
		Vector<Integer> outputs = new Vector<Integer>();
		
		try {			
			while (recipeIter.hasNext()) {
				recipe = recipeIter.next();
				
				if (recipe instanceof ShapedRecipes) {
					shapedRecipe = (ShapedRecipes) recipe;
					//shapedInputs = mod_EE3.proxy.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeItems");
					recipeInputs = ModLoader.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeItems");
					recipeOutput = ModLoader.getPrivateValue(ShapedRecipes.class, shapedRecipe, "recipeOutput");
					System.out.println("Shaped Recipe");
				}
				else if (recipe instanceof ShapelessRecipes) {
					shapelessRecipe = (ShapelessRecipes) recipe;
					shapelessInputs = ModLoader.getPrivateValue(ShapelessRecipes.class, shapelessRecipe, "recipeItems");
					recipeInputs = shapelessInputs.toArray(new ItemStack[0]);
					recipeOutput = ModLoader.getPrivateValue(ShapelessRecipes.class, shapelessRecipe, "recipeOutput");
					System.out.println("Shapeless Recipe");
				}
				System.out.println("Output: " + recipeOutput.toString());
				outputs.add(recipeOutput.getItem().shiftedIndex);
				for (ItemStack itemStack : recipeInputs) {
					if (itemStack != null) {
						System.out.println("Input: " + itemStack.toString());
						if (!inputs.contains(new Integer(itemStack.getItem().shiftedIndex)))
							inputs.add(new Integer(itemStack.getItem().shiftedIndex));
					}
				}
				System.out.println();
			}
			
			//for (int i = 0; i < outputs.size(); i++) {
			//	System.out.println("i: " + i + ", item: " + outputs.get(i));
			//}
			inputs.removeAll(outputs);
			for (int i = 0; i < inputs.size(); i++) {
				System.out.println(i + "," + inputs.get(i) + "," + Item.itemsList[inputs.get(i)].getItemName());
			}
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	private void printEMCList() {
		System.out.println("\n*** Starting Debug Dump of EMC List ***");
		System.out.println("*** End of EMC List ***\n");
	}
	
	private void addEMCValue(Block block, EMCValue emc) {
		this.addEMCValue(block.blockID, 0, emc);
	}
	
	private void addEMCValue(Block block, int meta, EMCValue emc) {
		this.addEMCValue(block.blockID, meta, emc);
	}
	
	private void addEMCValue(Item item, EMCValue emc) {
		this.addEMCValue(item.shiftedIndex, 0, emc);
	}
	
	private void addEMCValue(ItemStack itemStack, EMCValue emc) {
		this.addEMCValue(itemStack.itemID, itemStack.getItemDamage(), emc);
	}
	
	private void addEMCValue(int id, EMCValue emc) {
		this.addEMCValue(id, 0, emc);
	}
	
	private void addEMCValue(int id, int meta, EMCValue emc) {
		HashMap<Integer, EMCValue> tempMap = new HashMap<Integer, EMCValue>();
		if(emcMap.get(id) != null) {
			tempMap = emcMap.get(id);
		}
		tempMap.put(meta, emc);
		emcMap.put(id, tempMap); 
	}
}
