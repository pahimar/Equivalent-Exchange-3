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
        this.setIconCoord(7, 0);
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
    public int getIconIndex(ItemStack itemStack, int renderPass) {
        
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

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass) {

        int returnValue = Integer.parseInt(Colours.PURE_WHITE, 16);

        if (renderPass == 0) {
            switch (itemStack.getItemDamage()) {
                case 0:
                    returnValue = Integer.parseInt(Colours.BAG_WHITE, 16);
                    break;
                case 1:
                    returnValue = Integer.parseInt(Colours.BAG_ORANGE, 16);
                    break;
                case 2:
                    returnValue = Integer.parseInt(Colours.BAG_MAGENTA, 16);
                    break;
                case 3:
                    returnValue = Integer.parseInt(Colours.BAG_LIGHT_BLUE, 16);
                    break;
                case 4:
                    returnValue = Integer.parseInt(Colours.BAG_YELLOW, 16);
                    break;
                case 5:
                    returnValue = Integer.parseInt(Colours.BAG_LIME, 16);
                    break;
                case 6:
                    returnValue = Integer.parseInt(Colours.BAG_PINK, 16);
                    break;
                case 7:
                    returnValue = Integer.parseInt(Colours.BAG_GRAY, 16);
                    break;
                case 8:
                    returnValue = Integer.parseInt(Colours.BAG_LIGHT_GRAY, 16);
                    break;
                case 9:
                    returnValue = Integer.parseInt(Colours.BAG_CYAN, 16);
                    break;
                case 10:
                    returnValue = Integer.parseInt(Colours.BAG_PURPLE, 16);
                    break;
                case 11:
                    returnValue = Integer.parseInt(Colours.BAG_BLUE, 16);
                    break;
                case 12:
                    returnValue = Integer.parseInt(Colours.BAG_BROWN, 16);
                    break;
                case 13:
                    returnValue = Integer.parseInt(Colours.BAG_GREEN, 16);
                    break;
                case 14:
                    returnValue = Integer.parseInt(Colours.BAG_RED, 16);
                    break;
                case 15:
                    returnValue = Integer.parseInt(Colours.BAG_BLACK, 16);
                    break;
            }
        }
        return returnValue;
    }
}
