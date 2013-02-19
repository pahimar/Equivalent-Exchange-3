package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchemyBag extends ItemEE {

    public static final String[] alchemyBagNames = new String[] { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray", "cyan", "purple", "blue", "brown", "green", "red", "black" };

    public ItemAlchemyBag(int id) {

        super(id);
        this.setHasSubtypes(true);
        this.setIconCoord(7, 0);
        this.setItemName(Strings.ALCHEMY_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getIconFromDamageForRenderPass(int meta, int renderPass) {

        if (renderPass == 0) {
            return this.getIconFromDamage(meta);
        }
        else {
            return this.getIconFromDamage(meta) + 1;
        }
    }

    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int meta) {

        if (FMLClientHandler.instance().getClient().currentScreen != null) {
            return (this.iconIndex + 2);
        }

        return this.iconIndex;
    }

    @SideOnly(Side.CLIENT)
    public String getItemNameIS(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 15);
        return super.getItemName() + "." + alchemyBagNames[meta];
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

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list) {

        for (int meta = 0; meta < 16; ++meta) {
            list.add(new ItemStack(id, 1, meta));
        }
    }

}
