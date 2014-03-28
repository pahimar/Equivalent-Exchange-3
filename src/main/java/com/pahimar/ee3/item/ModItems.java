package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems
{
    public static final Item itemAlchemicalBag = new ItemAlchemicalBag();
    public static final Item itemAlchemicalDust = new ItemAlchemicalDust();
    public static final Item itemChalk = new ItemChalk();

    public static void init()
    {
        GameRegistry.registerItem(itemAlchemicalBag, "item." + Names.Items.ALCHEMICAL_BAG);
        GameRegistry.registerItem(itemAlchemicalDust, "item." + Names.Items.ALCHEMICAL_DUST);
        GameRegistry.registerItem(itemChalk, "item." + Names.Items.CHALK);
    }
}
