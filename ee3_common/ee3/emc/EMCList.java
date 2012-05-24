package ee3.emc;

import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EMCList {

	private HashMap<Integer, HashMap<Integer, EMCValue>> emcMap;
	
	public EMCList() {
		emcMap = new HashMap<Integer, HashMap<Integer, EMCValue>>();
	}
	
	public void initEMCList() {
		
	}


	/* Helper functions */

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
