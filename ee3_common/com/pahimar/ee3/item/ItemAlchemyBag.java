package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchemyBag extends ItemEE {

    public ItemAlchemyBag(int id) {

        super(id);
        this.setUnlocalizedName(Strings.ALCHEMY_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

        if (!world.isRemote) {
            NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_ALCHEMY_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_BAG, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }
    
    @Override
    public boolean getShareTag() {

        return true;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(ItemStack itemStack, int renderPass) {
        
    	// If the bag is open
        if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMY_BAG_GUI_OPEN)) {
            if (renderPass == 0) {
                return this.iconIndex;
            }
            else {
                return this.iconIndex;
            }
        }
        // Else, the bag is closed
        else {
            if (renderPass == 0) {
                return this.iconIndex;
            }
            else {
                return this.iconIndex;
            }
        }
    }
}
