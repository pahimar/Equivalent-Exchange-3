package com.pahimar.ee3.item;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.IEnergyValueProvider;
import com.pahimar.ee3.reference.Names;
import net.minecraft.item.ItemStack;

public class ItemMiniumShard extends ItemEE implements IEnergyValueProvider
{
    public ItemMiniumShard()
    {
        super();
        this.setMaxStackSize(64);
        this.setUnlocalizedName(Names.Items.MINIUM_SHARD);
    }

    @Override
    public EnergyValue getEnergyValue(ItemStack itemStack) {
        if (itemStack != null && itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("energyValue")) {
            if (Float.compare(itemStack.getTagCompound().getFloat("energyValue"), 0) > 0)
                return new EnergyValue(itemStack.getTagCompound().getFloat("energyValue"));
        }
        return null;
    }
}
