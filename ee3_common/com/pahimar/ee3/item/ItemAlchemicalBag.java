package com.pahimar.ee3.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchemicalBag extends ItemEE {

    private static final String[] ALCHEMICAL_BAG_SUBTYPES = new String[] { "Open", "OpenDrawString", "Closed", "ClosedDrawString" };
    
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    
    public ItemAlchemicalBag(int id) {

        super(id);
        this.setUnlocalizedName(Strings.ALCHEMICAL_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister iconRegister) {

        this.icons = new Icon[ALCHEMICAL_BAG_SUBTYPES.length];
        
        for (int i = 0; i < ALCHEMICAL_BAG_SUBTYPES.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a(Reference.MOD_ID.toLowerCase() + ":" + Strings.ALCHEMICAL_BAG_NAME + ALCHEMICAL_BAG_SUBTYPES[i]);
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

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;
    }

    @Override
    public Icon getIcon(ItemStack itemStack, int renderPass) {
        
    	// If the bag is open
        if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
            if (renderPass == 0) {
                return icons[0];
            }
            else {
                return icons[1];
            }
        }
        // Else, the bag is closed
        else {
            if (renderPass == 0) {
                return icons[2];
            }
            else {
                return icons[3];
            }
        }
    }
}
