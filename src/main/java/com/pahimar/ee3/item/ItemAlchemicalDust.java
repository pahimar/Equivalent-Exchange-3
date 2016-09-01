package com.pahimar.ee3.item;

import com.pahimar.ee3.item.base.ItemEE;
import com.pahimar.ee3.reference.Colors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAlchemicalDust extends ItemEE implements IItemColor {

    public ItemAlchemicalDust() {
        super("alchemical_dust", "ash", "minium_dust");
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemstack(ItemStack itemStack, int renderPass) {

        if (itemStack.getMetadata() < Colors.DUST_COLOURS.length) {
            return Colors.DUST_COLOURS[itemStack.getMetadata()];
        }

        return Colors.PURE_WHITE;
    }
}
