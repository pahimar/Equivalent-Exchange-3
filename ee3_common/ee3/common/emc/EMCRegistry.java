package ee3.common.emc;

import java.util.HashMap;
import java.util.Map;

public class EMCRegistry {

	private static final EMCRegistry emcRegistry = new EMCRegistry();
	
	private HashMap<Integer, HashMap<Integer, EMCEntry>> emcMap = new HashMap<Integer, HashMap<Integer, EMCEntry>>();
	
	public EMCRegistry instance() {
		return emcRegistry;
	}
	
}
