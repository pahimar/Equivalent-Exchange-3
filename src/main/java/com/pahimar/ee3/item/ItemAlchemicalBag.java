package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.GuiIds;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.util.ColorHelper;
import com.pahimar.ee3.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class ItemAlchemicalBag extends ItemEE implements IDyeable
{
    private static final String[] ALCHEMICAL_BAG_ICONS = {"open", "closed", "symbolTier1", "symbolTier2", "symbolTier3"};

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemAlchemicalBag()
    {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Names.Items.ALCHEMICAL_BAG);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[ALCHEMICAL_BAG_ICONS.length];

        for (int i = 0; i < ALCHEMICAL_BAG_ICONS.length; i++)
        {
            icons[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + Names.Items.ALCHEMICAL_BAG + "." + ALCHEMICAL_BAG_ICONS[i]);
        }
    }

    @Override
    public IIcon getIcon(ItemStack itemStack, int renderPass)
    {
        if (renderPass == 0)
        {
            if (NBTHelper.hasTag(itemStack, Names.NBT.ALCHEMICAL_BAG_GUI_OPEN))
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
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < 3; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            // Set a UUID on the Alchemical Bag, if one doesn't exist already
            NBTHelper.setUUID(itemStack);
            NBTHelper.setBoolean(itemStack, Names.NBT.ALCHEMICAL_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_BAG, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        int bagColor = this.getColor(itemStack);

        if (bagColor < 0)
        {
            bagColor = Integer.parseInt(Colors.PURE_WHITE, 16);
        }

        return bagColor;
    }

    @Override
    public boolean hasColor(ItemStack itemStack)
    {
        return ColorHelper.hasColor(itemStack);
    }

    @Override
    public int getColor(ItemStack itemStack)
    {
        return ColorHelper.getColor(itemStack);
    }

    @Override
    public void setColor(ItemStack itemStack, int color)
    {
        if (itemStack != null)
        {
            if (itemStack.getItem() instanceof ItemAlchemicalBag)
            {
                ColorHelper.setColor(itemStack, color);
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
                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Names.NBT.DISPLAY);

                if (displayTagCompound.hasKey(Names.NBT.COLOR))
                {
                    displayTagCompound.removeTag(Names.NBT.COLOR);
                }
            }
        }
    }
}
