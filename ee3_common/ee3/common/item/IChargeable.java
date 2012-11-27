package ee3.common.item;

import net.minecraft.src.ItemStack;

public interface IChargeable {

    public abstract short getCharge(ItemStack stack);
    
    public abstract void setCharge(ItemStack stack, short charge);

    public abstract void increaseCharge(ItemStack stack);

    public abstract void decreaseCharge(ItemStack stack);

}
