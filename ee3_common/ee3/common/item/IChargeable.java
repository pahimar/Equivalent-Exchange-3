package ee3.common.item;

import net.minecraft.src.ItemStack;

public interface IChargeable {
    
    public abstract void setCharge(ItemStack stone, short charge);
    
    public abstract void increaseCharge(ItemStack stone);
    
    public abstract void decreaseCharge(ItemStack stone);

}
