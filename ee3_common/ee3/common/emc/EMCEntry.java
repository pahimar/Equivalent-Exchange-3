package ee3.common.emc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * EMCEntry
 * 
 * Holds the breakdown of how much, and what kinds, of EMC an object has
 * Each object (item or block) will have one such an EMCEntry associated with it.
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EMCEntry {

	/**
	 * cost = total EMC cost of the item.
	 * recoveryPercentage = What percentage of the cost does the user get when turning this into EMC.
	 * 
	 * learnable = Can the user learn to make this?
	 * recoverable = Can this be converted into EMC?
	 * 
	 * breakdown = If you break this down (or make it) what percentage of what EMC type do you get?
	 *             All of the numbers in this list together should add up to 1.0f (or 100%).
	 */
	private float cost, recoveryPercentage;
	private boolean learnable, recoverable;
	private Map<EMCType, Float> breakdown;

	public EMCEntry(float cost) {
		this.cost = cost;
		recoveryPercentage = 1F;
		learnable = true;
		recoverable = true;
		breakdown = Collections.synchronizedMap(new HashMap<EMCType, Float>());
	}

	public EMCEntry(float cost, float recoveryPercentage, boolean learnable, boolean recoverable) {
		this.cost = cost;
		this.recoveryPercentage = recoveryPercentage;
		this.learnable = learnable;
		this.recoverable = recoverable;
		breakdown = Collections.synchronizedMap(new HashMap<EMCType, Float>());
	}

	public float getCost() {
		return cost;
	}

	public float getRecoveryPercentage() {
		return recoveryPercentage;
	}

	public boolean isLearnable() {
		return learnable;
	}

	public boolean isRecoverable() {
		return recoverable;
	}

	public Map<EMCType, Float> getEMCBreakDown() {
		return breakdown;
	}

	public float getEMCBreakdownByType(EMCType emcType) {
		if (breakdown.containsKey(emcType)) {
			if (breakdown.get(emcType) != null) {
				return breakdown.get(emcType).floatValue();
			}
		}

		return -1F;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setRecoveryPercentage(float recoveryPercentage) {
		this.recoveryPercentage = recoveryPercentage;
	}

	public void setLearnable(boolean learnable) {
		this.learnable = learnable;
	}

	public void setRecoverable(boolean recoverable) {
		this.recoverable = recoverable;
	}

	public void addEMCBreakDown(EMCType emcType, Float breakdownPercentage) {
		if (!(breakdown.containsKey(emcType))) {
			breakdown.put(emcType, breakdownPercentage);
		}
	}

	public void setEMCBreakDown(EMCType emcType, Float breakdownPercentage) {
		if (breakdown.containsKey(emcType)) {
			breakdown.put(emcType, breakdownPercentage);
		}
	}

}
