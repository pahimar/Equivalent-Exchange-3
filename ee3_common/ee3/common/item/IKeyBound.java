package ee3.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;

public interface IKeyBound {

    public abstract void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding);

}
