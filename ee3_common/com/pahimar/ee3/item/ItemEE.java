package com.pahimar.ee3.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemEE
 * 
 * Wrapper for mod items
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemEE extends Item {

    public ItemEE(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
        maxStackSize = 1;
        setNoRepair();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    // public void setIconIndex(IconRegister iconRegister)
    public void func_94581_a(IconRegister iconRegister) {
        this.iconIndex = iconRegister.func_94245_a(Reference.MOD_ID.toLowerCase() + ":"+ this.getUnlocalizedName());
    }
}
