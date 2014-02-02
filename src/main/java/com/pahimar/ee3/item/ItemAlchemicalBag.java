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
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemAlchemicalBag
 *
 * @author pahimar
 */
public class ItemAlchemicalBag extends ItemEE implements IDyeable
{
    private static final String[] ALCHEMICAL_BAG_ICONS = {"open", "closed", "symbolTier1", "symbolTier2", "symbolTier3"};

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemAlchemicalBag(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.ALCHEMICAL_BAG_NAME);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < 3; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        icons = new Icon[ALCHEMICAL_BAG_ICONS.length];

        for (int i = 0; i < ALCHEMICAL_BAG_ICONS.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_BAG_NAME + "." + ALCHEMICAL_BAG_ICONS[i]);
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
        if (renderPass == 0)
        {
            if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN))
            {
                return icons[0];
            }
            else
            {
                return icons[1];
            }
        }
        else
        {
            return icons[2 + MathHelper.clamp_int(itemStack.getItemDamage(), 0, 3)];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        int bagColor = this.getColor(itemStack);

        if (bagColor < 0)
        {
            bagColor = Integer.parseInt(Colours.PURE_WHITE, 16);
        }

        return bagColor;
    }

    @Override
    public boolean hasColor(ItemStack itemStack)
    {
        return ItemHelper.hasColor(itemStack);
    }

    @Override
    public int getColor(ItemStack itemStack)
    {
        return ItemHelper.getColor(itemStack);
    }

    @Override
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

    @Override
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
}
