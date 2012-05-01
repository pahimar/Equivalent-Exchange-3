package net.minecraft.src.ee3.core;

public class EMCMapping {
	
	private int cost;
	private int recovery;
	
	public EMCMapping() { }
	
	public EMCMapping(int cost) {
		this.cost = cost;
		recovery = cost;
	}
	
	public EMCMapping(int cost, int recovery) {
		this.cost = cost;
		this.recovery = recovery;
	}
	
	public int getCostEMC() {
		return cost;
	}
	
	public int getRecoveryEMC() {
		return recovery;
	}
	
	public void setCostEMC(int cost) {
		this.cost = cost;
	}
	
	public void setRecoveryEMC(int recovery) {
		this.recovery = recovery;
	}
}
