package ee3.common.item;

import net.minecraft.src.EntityPlayer;

public interface IKeyBound {

    public abstract void doKeyBindingAction(EntityPlayer thePlayer, String keyBinding);

}
