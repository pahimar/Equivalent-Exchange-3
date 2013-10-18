package com.pahimar.ee3.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.ItemHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ItemAlchemicalBag
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemAlchemicalBag extends ItemEE {

    private static final String[] ALCHEMICAL_BAG_SUBTYPES = new String[] { "Open", "OpenDrawString", "Closed", "ClosedDrawString" };

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemAlchemicalBag(int id) {

        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        icons = new Icon[ALCHEMICAL_BAG_SUBTYPES.length];

        for (int i = 0; i < ALCHEMICAL_BAG_SUBTYPES.length; ++i) {
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_BAG_NAME + ALCHEMICAL_BAG_SUBTYPES[i]);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {

        if (!world.isRemote) {
            NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_BAG, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

    @Override
    public boolean getShareTag() {

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;
    }

    @Override
    public Icon getIcon(ItemStack itemStack, int renderPass) {

        // If the bag is open
        if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
            if (renderPass != 1)
                return icons[0];
            else
                return icons[1];
        }
        // Else, the bag is closed
        else {
            if (renderPass != 1)
                return icons[2];
            else
                return icons[3];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass) {

        if (renderPass == 1)
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        else {
            int bagColor = this.getColor(itemStack);

            if (bagColor < 0) {
                bagColor = Integer.parseInt(Colours.PURE_WHITE, 16);
            }

            return bagColor;
        }
    }

    public boolean hasColor(ItemStack itemStack) {

        return ItemHelper.hasColor(itemStack);
    }

    public int getColor(ItemStack itemStack) {

        return ItemHelper.getColor(itemStack);
    }

    public void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {
            if (!(itemStack.getItem() instanceof ItemAlchemicalBag))
                // TODO Localize
                throw new UnsupportedOperationException("Cannot dye non-bags!");
            else {
                ItemHelper.setColor(itemStack, color);
            }
        }
    }

    public void removeColor(ItemStack itemStack) {

        if (itemStack != null) {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound != null) {

                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

                if (displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR)) {

                    displayTagCompound.removeTag(Strings.NBT_ITEM_COLOR);
                }
            }
        }
    }
}
