package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;

public class ItemDarkMatterFishingRod extends ItemFishingRod
{
    public ItemDarkMatterFishingRod()
    {
        super();
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_FISHING_ROD);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
