package ee3.emc;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EMCValue {
	
	private float cost;
	private float recoveryPercentage;
	private boolean learnable;
	private boolean recoverable;
	private float[] emcBreakdown;
	
	public EMCValue() { }
	
	public EMCValue(float cost) {
		this.cost = cost;
		recoveryPercentage = 1.0F;
		learnable = true;
		recoverable = true;
	}
	
	public EMCValue(float cost, float recoveryPercentage, boolean learnable, boolean recoverable) {
		this.cost = cost;
		this.recoveryPercentage = recoveryPercentage;
		this.learnable = learnable;
		this.recoverable = recoverable;
	}
	
	public float getCostEMC() {
		return cost;
	}
	
	public float getRecoveryEMC() {
		return recoveryPercentage;
	}
	
	public boolean isLearnable() {
		return learnable;
	}
	
	public boolean isRecoverable() {
		return recoverable;
	}
	
	public void setCostEMC(float cost) {
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
}
