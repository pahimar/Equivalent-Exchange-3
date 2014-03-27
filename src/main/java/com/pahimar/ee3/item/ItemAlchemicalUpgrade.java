package com.pahimar.ee3.item;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.lib.*;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemAlchemicalUpgrade extends ItemEE
{
    public ItemAlchemicalUpgrade(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.ALCHEMICAL_UPGRADE_NAME);
        this.setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_UPGRADE_SUBTYPE_NAMES.length; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_UPGRADE_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_UPGRADE_NAME, Strings.ALCHEMICAL_UPGRADE_SUBTYPE_NAMES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_UPGRADE_SUBTYPE_NAMES.length - 1)]);
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack)
    {
        switch (MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_UPGRADE_SUBTYPE_NAMES.length - 1))
        {
            case 0:
            {
                return EnumChatFormatting.GREEN + super.getItemDisplayName(itemStack);
            }
            case 1:
            {
                return EnumChatFormatting.BLUE + super.getItemDisplayName(itemStack);
            }
            case 2:
            {
                return EnumChatFormatting.RED + super.getItemDisplayName(itemStack);
            }
            default:
            {
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            }
        }
    }

    @Override
    public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ)
    {
        if (world.isRemote)
        {
            return false;
        }

        TileEntity tile = world.getBlockTileEntity(x, y, z);
        if (canUpgrade(itemStack, tile))
        {
            if (tile instanceof TileEntityChest)
            {
                TileEntityChest chest = (TileEntityChest)tile;

                //If players are using this chest, don't upgrade
                if (chest.numUsingPlayers > 0)
                {
                    return false;
                }

                //Make the new Alchemical Chest TileEntity
                TileAlchemicalChest newChest = (TileAlchemicalChest) ModBlocks.alchemicalChest.createTileEntity(world, itemStack.getItemDamage());

                //Set the correct orientation
                newChest.setOrientation(chest.blockMetadata);

                //Copy all the ItemStacks in our new chest and delete the ItemStacks in the old chest
                for (int slot = 0; slot < chest.getSizeInventory(); slot++)
                {
                    newChest.setInventorySlotContents(slot, chest.getStackInSlot(slot));
                    chest.setInventorySlotContents(slot, null);
                }

                //Set the block to air and make sure the neighbouring chests get notified
                world.setBlock(x, y, z, 0, 0, 3);
                chest.updateContainingBlockInfo();
                chest.checkForAdjacentChests();

                //Set our new block and TileEntity instead
                world.setBlock(x, y, z, ModBlocks.alchemicalChest.blockID, itemStack.getItemDamage(), 3);
                world.setBlockTileEntity(x, y, z, newChest);

                itemStack.stackSize--;
                return true;
            }
            else if (tile instanceof TileAlchemicalChest)
            {
                TileAlchemicalChest chest = (TileAlchemicalChest)tile;

                //If players are using this chest, don't upgrade
                if (chest.numUsingPlayers > 0)
                {
                    return false;
                }

                //Make the new Alchemical Chest TileEntity
                TileAlchemicalChest newChest = (TileAlchemicalChest) ModBlocks.alchemicalChest.createTileEntity(world, itemStack.getItemDamage());

                //Set the correct orientation
                newChest.setOrientation(chest.getOrientation());

                //Copy all the ItemStacks in our new chest and delete the ItemStacks in the old chest
                for (int slot = 0; slot < chest.getSizeInventory(); slot++)
                {
                    newChest.setInventorySlotContents(slot, chest.getStackInSlot(slot));
                    chest.setInventorySlotContents(slot, null);
                }

                //Set the block to air
                world.setBlock(x, y, z, 0, 0, 3);
                chest.updateContainingBlockInfo();

                //Set our new block and TileEntity instead
                world.setBlock(x, y, z, ModBlocks.alchemicalChest.blockID, itemStack.getItemDamage(), 3);
                world.setBlockTileEntity(x, y, z, newChest);

                itemStack.stackSize--;
                return true;
            }
        }
        return false;
    }

    public boolean canUpgrade(ItemStack itemStack, TileEntity tile)
    {
        if (tile != null)
        {
            if (tile instanceof TileEntityChest)
            {
                return this.getDamage(itemStack) >= 0;
            }
            else if (tile instanceof TileAlchemicalChest)
            {
                return itemStack.getItemDamage() > tile.getBlockMetadata();
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        if (itemStack.getItemDamage() == 0)
        {
            return Integer.parseInt(Colours.DUST_VERDANT, 16);
        }
        else if (itemStack.getItemDamage() == 1)
        {
            return Integer.parseInt(Colours.DUST_AZURE, 16);
        }
        else if (itemStack.getItemDamage() == 2)
        {
            return Integer.parseInt(Colours.DUST_MINIUM, 16);
        }

        return Integer.parseInt(Colours.PURE_WHITE, 16);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag)
    {
        list.add(StatCollector.translateToLocal(Strings.UPGRADES_CHESTS));
    }
}
