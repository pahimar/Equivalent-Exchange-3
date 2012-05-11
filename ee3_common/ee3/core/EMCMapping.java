package ee3.core;

public class EMCMapping {
	
	private float cost;
	private float recoveryPercentage;
	private boolean learnable;
	private boolean recoverable;
	
	public EMCMapping() { }
	
	public EMCMapping(float cost) {
		this.cost = cost;
		recoveryPercentage = 1.0F;
		learnable = true;
		recoverable = true;
	}
	
	public EMCMapping(float cost, float recoveryPercentage, boolean learnable, boolean recoverable) {
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
