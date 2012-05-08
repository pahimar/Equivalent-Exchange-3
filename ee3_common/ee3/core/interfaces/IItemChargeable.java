package ee3.core.interfaces;

public interface IItemChargeable {

	public abstract byte getCurrentCharge();
	
	public abstract byte getMaxCharge();
	
	public abstract void increaseCharge();
	
	public abstract void decreaseCharge();
	
	public abstract void setCurrentCharge(byte charge);
}
