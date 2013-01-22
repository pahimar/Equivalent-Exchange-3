package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.lib.CustomItemRarity;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Sounds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemPhilosopherStone
 * 
 * The Philosophers Stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemPhilosopherStone extends ItemEE
        implements ITransmutationStone, IChargeable, IKeyBound {

    private int maxChargeLevel;

    public ItemPhilosopherStone(int id) {

        super(id);
        this.setIconCoord(3, 0);
        this.setItemName(Strings.PHILOSOPHER_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setMaxDamage(ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY - 1);
        this.maxChargeLevel = 3;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack) {

        return NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANS_GUI_OPEN);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack itemStack) {

        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.RARE);
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

        itemStack.setItemDamage(itemStack.getItemDamage() + 1);

        return itemStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ) {

        if (world.isRemote) {
            transmuteBlock(itemStack, entityPlayer, world, x, y, z, sideHit);
        }
        return true;
    }

    @Override
    public void openPortableCrafting(EntityPlayer thePlayer, ItemStack itemStack) {

        NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_TRANS_GUI_OPEN, true);
        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit) {

        EquivalentExchange3.proxy.transmuteBlock(itemStack, player, world, x, y, z, sideHit);
    }

    @Override
    public short getCharge(ItemStack stack) {

        return NBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY);
    }

    @Override
    public void setCharge(ItemStack stack, short charge) {

        if (charge <= maxChargeLevel) {
            NBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, charge);
        }
    }

    @Override
    public void increaseCharge(ItemStack stack) {

        if (NBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) < maxChargeLevel) {
            NBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, (short) (NBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) + 1));
        }
    }

    @Override
    public void decreaseCharge(ItemStack stack) {

        if (NBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) > 0) {
            NBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, (short) (NBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) - 1));
        }
    }

    @Override
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {

        if (keyBinding.equals(ConfigurationSettings.KEYBINDING_EXTRA)) {
            openPortableCrafting(thePlayer, itemStack);
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
        // TODO Packet-ize the sounds
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_CHARGE)) {
            if (!thePlayer.isSneaking()) {
                if (getCharge(itemStack) == maxChargeLevel) {
                    thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.FAIL, 1.5F, 1.5F);
                }
                else {
                    increaseCharge(itemStack);
                    thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.CHARGE_UP, 0.5F, 0.5F + (0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel)));
                }
            }
            else {
                if (getCharge(itemStack) == 0) {
                    thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.FAIL, 1.5F, 1.5F);
                }
                else {
                    decreaseCharge(itemStack);
                    thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - (0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel))));
                }
            }
        }

    }

}
