package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.skill.PlayerKnowledge;
import com.pahimar.ee3.util.IOwnable;
import net.minecraft.item.ItemStack;

public class ItemAlchemicalTome extends ItemEE implements IOwnable
{
    public ItemAlchemicalTome()
    {
        super();
        this.setUnlocalizedName(Names.Items.ALCHEMICAL_TOME);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    public PlayerKnowledge getPlayerKnowledge(ItemStack itemStack)
    {
        return null;
    }
}
