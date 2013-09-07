package com.pahimar.ee3.emc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcRegistry {

	private static EmcRegistry emcRegistry = null;
	
	private Map<CustomWrappedStack, EmcValue> stackMappings;
	private TreeMap<EmcValue, List<CustomWrappedStack>> valueMappings;

	private EmcRegistry() {

		stackMappings = new HashMap<CustomWrappedStack, EmcValue>();
		valueMappings = new TreeMap<EmcValue, List<CustomWrappedStack>>();
	}

	public static EmcRegistry getInstance() {

		if (emcRegistry == null) {
			emcRegistry = new EmcRegistry();
			emcRegistry.init();
		}

		return emcRegistry;
	}

	private void init() {

	}

}
