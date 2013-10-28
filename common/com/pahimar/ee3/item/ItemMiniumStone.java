package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ItemMiniumStone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMiniumStone extends ItemEE
        implements ITransmutationStone, IKeyBound {

    public ItemMiniumStone(int id) {

        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.MINIUM_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setMaxDamage(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY - 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {

        return NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_CRAFTING_GUI_OPEN) || NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack) {

        return EnumChatFormatting.BLUE + super.getItemDisplayName(itemStack);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {

        return false;
    }

    @Override
    public boolean getShareTag() {

        return true;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {

        ItemStack copiedStack = itemStack.copy();

        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);

        // Hacky hacky hack hack
        copiedStack.stackSize = 1;

        return copiedStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ) {

        if (world.isRemote) {
            transmuteBlock(itemStack, entityPlayer, world, x, y, z, sideHit);
        }
        return true;
    }

    @Override
    public void openPortableCraftingGUI(EntityPlayer thePlayer, ItemStack itemStack) {

        NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_CRAFTING_GUI_OPEN, true);
        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
    }

    @Override
    public void openPortableTransmutationGUI(EntityPlayer thePlayer, ItemStack itemStack) {

        NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN, true);
        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_TRANSMUTATION, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit) {

        EquivalentExchange3.proxy.transmuteBlock(itemStack, player, world, x, y, z, sideHit);
    }

    @Override
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {

        if (keyBinding.equals(ConfigurationSettings.KEYBINDING_EXTRA)) {
            if (!thePlayer.isSneaking()) {
                openPortableCraftingGUI(thePlayer, itemStack);
            }
            else {
                openPortableTransmutationGUI(thePlayer, itemStack);
            }
        }
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_TOGGLE)) {
            if (TransmutationHelper.targetBlockStack != null) {
                if (!thePlayer.isSneaking()) {
                    TransmutationHelper.targetBlockStack = TransmutationHelper.getNextBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
                }
                else {
                    TransmutationHelper.targetBlockStack = TransmutationHelper.getPreviousBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
                }
            }
        }

    }

}
