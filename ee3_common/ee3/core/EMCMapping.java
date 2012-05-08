package ee3.core;

public class EMCMapping {
	
	private float cost;
	private float recovery;
	private boolean learnable;
	private boolean recoverable;
	
	public EMCMapping() { }
	
	public EMCMapping(float cost) {
		this.cost = cost;
		recovery = cost;
		learnable = true;
		recoverable = true;
	}
	
	public EMCMapping(float cost, float recovery, boolean learnable, boolean recoverable) {
		this.cost = cost;
		this.recovery = recovery;
		this.learnable = learnable;
		this.recoverable = recoverable;
	}
	
	public float getCostEMC() {
		return cost;
	}
	
	public float getRecoveryEMC() {
		return recovery;
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
	
	public void setRecoveryEMC(float recovery) {
		this.recovery = recovery;
	}
	
	public void setLearnable(boolean learnable) {
		this.learnable = learnable;
	}
	
	public void setRecoverable(boolean recoverable) {
		this.recoverable = recoverable;
	}
}
