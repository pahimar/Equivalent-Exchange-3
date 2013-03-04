package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.client.FMLClientHandler;
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
    public Icon getIconIndex(ItemStack itemStack, int renderPass) {
        
        if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMY_BAG_GUI_OPEN)) {
            if (renderPass == 0) {
                return this.iconIndex + 2;
            }
            else {
                return this.iconIndex + 1 + 2;
            }
        }
        else {
            if (renderPass == 0) {
                return this.iconIndex;
            }
            else {
                return this.iconIndex + 1;
            }
        }
    }
}
