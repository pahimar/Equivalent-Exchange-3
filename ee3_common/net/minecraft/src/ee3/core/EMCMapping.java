package net.minecraft.src.ee3.core;

public class EMCMapping {
	
	private float cost;
	private float recovery;
	
	public EMCMapping() { }
	
	public EMCMapping(float cost) {
		this.cost = cost;
		recovery = cost;
	}
	
	public EMCMapping(float cost, float recovery) {
		this.cost = cost;
		this.recovery = recovery;
	}
	
	public float getCostEMC() {
		return cost;
	}
	
	public float getRecoveryEMC() {
		return recovery;
	}
	
	public void setCostEMC(float cost) {
		this.cost = cost;
	}
	
	public void setRecoveryEMC(float recovery) {
		this.recovery = recovery;
	}
}
