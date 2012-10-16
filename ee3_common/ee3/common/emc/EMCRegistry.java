package ee3.common.emc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EMCRegistry {

	private static final EMCRegistry emcRegistry = new EMCRegistry();

	private HashMap<Integer, HashMap<Integer, EMCEntry>> emcMap = new HashMap<Integer, HashMap<Integer, EMCEntry>>();

	public EMCRegistry instance() {
		return emcRegistry;
	}
	
	public EMCEntry getEMCValue(Block block) {
		if (block != null) {
			return getEMCValue(block.blockID, 0);
		}
		
		return null;
	}
	
	public EMCEntry getEMCValue(Item item) {
		if (item != null) {
			return getEMCValue(item.shiftedIndex, 0);
		}
		
		return null;
	}
	
	public EMCEntry getEMCValue(ItemStack itemStack) {
		if (itemStack != null) {
			return getEMCValue(itemStack.itemID, itemStack.getItemDamage());
		}
		
		return null;
	}
	
	public EMCEntry getEMCValue(int id) {
		return getEMCValue(id, 0);
	}
	
	public EMCEntry getEMCValue(int id, int meta) {
		if (emcMap.containsKey(id)) {
			if (emcMap.get(id).containsKey(meta)) {
				return emcMap.get(id).get(meta);
			}
		}
		
		return null;
	}
	
	public void addEMCValue(Block block, EMCEntry emcEntry) {
		addEMCValue(block.blockID, 0, emcEntry);
	}
	
	public void addEMCValue(Block block, int meta, EMCEntry emcEntry) {
		addEMCValue(block.blockID, meta, emcEntry);
	}
	
	public void addEMCValue(Item item, EMCEntry emcEntry) {
		addEMCValue(item.shiftedIndex, 0, emcEntry);
	}
	
	public void addEMCValue(ItemStack itemStack, EMCEntry emcEntry) {
		addEMCValue(itemStack.itemID, itemStack.getItemDamage(), emcEntry);
	}
	
	public void addEMCValue(int id, EMCEntry emcEntry) {
		addEMCValue(id, 0, emcEntry);
	}
	
	public void addEMCValue(int id, int meta, EMCEntry emcEntry) {
		HashMap<Integer, EMCEntry> tempMap = new HashMap<Integer, EMCEntry>();
		
		if (emcMap.containsKey(id)) {
			tempMap = emcMap.get(id);
			
			if (tempMap.containsKey(meta)) {
				return;
			}
		}
		
		tempMap.put(meta, emcEntry);
		emcMap.put(id, tempMap);
	}

}
