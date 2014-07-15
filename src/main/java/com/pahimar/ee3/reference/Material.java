package com.pahimar.ee3.reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class Material
{
    public static final class Tools
    {
        public static final Item.ToolMaterial DARK_MATTER = EnumHelper.addToolMaterial(Names.Materials.DARK_MATTER, 3, 0, 14f, 3f, 0);
    }

    public static final class Armor
    {
        public static final ItemArmor.ArmorMaterial DARK_MATTER = EnumHelper.addArmorMaterial(Names.Materials.DARK_MATTER, 0, new int[]{3, 8, 6, 3}, 0);
    }
}
