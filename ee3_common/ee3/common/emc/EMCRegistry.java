package ee3.common.emc;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.ItemStack;

public class EMCRegistry {

	private static final EMCRegistry emcRegistry = new EMCRegistry();
	
	private HashMap<Integer, HashMap<Integer, EMCEntry>> emcMap = new HashMap<Integer, HashMap<Integer, EMCEntry>>();
	
	public EMCRegistry instance() {
		return emcRegistry;
	}
	
	public EMCEntry getEMCEntry(ItemStack item) {
		
		return null;
	}
	
}
