package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.helper.ItemHelper;
import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemAlchemicalBag
 *
 * @author pahimar
 */
public class ItemAlchemicalBag extends ItemEE
{
    private static final String[] ALCHEMICAL_BAG_ICONS = {"Open", "OpenDrawString", "Closed", "ClosedDrawString"};

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemAlchemicalBag(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        icons = new Icon[ALCHEMICAL_BAG_ICONS.length];

        for (int i = 0; i < ALCHEMICAL_BAG_ICONS.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_BAG_NAME + ALCHEMICAL_BAG_ICONS[i]);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            ItemStackNBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_BAG, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    public Icon getIcon(ItemStack itemStack, int renderPass)
    {
        // If the bag is open
        if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN))
        {
            if (renderPass != 1)
            {
                return icons[0];
            }
            else
            {
                return icons[1];
            }
        }
        // Else, the bag is closed
        else
        {
            if (renderPass != 1)
            {
                return icons[2];
            }
            else
            {
                return icons[3];
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        if (renderPass == 1)
        {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        }
        else
        {
            int bagColor = this.getColor(itemStack);

            if (bagColor < 0)
            {
                bagColor = Integer.parseInt(Colours.PURE_WHITE, 16);
            }

            return bagColor;
        }
    }

    public boolean hasColor(ItemStack itemStack)
    {
        return ItemHelper.hasColor(itemStack);
    }

    public int getColor(ItemStack itemStack)
    {
        return ItemHelper.getColor(itemStack);
    }

    public void setColor(ItemStack itemStack, int color)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() instanceof ItemAlchemicalBag)
            {
                ItemHelper.setColor(itemStack, color);
            }
        }
    }

    public void removeColor(ItemStack itemStack)
    {
        if (itemStack != null)
        {
            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound != null)
            {
                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

                if (displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR))
                {
                    displayTagCompound.removeTag(Strings.NBT_ITEM_COLOR);
                }
            }
        }
    }
    
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if( par1ItemStack.stackTagCompound == null )
            par1ItemStack.setTagCompound(new NBTTagCompound());
        par1ItemStack.getTagCompound().setTag("Items", new NBTTagList());
    }
}
