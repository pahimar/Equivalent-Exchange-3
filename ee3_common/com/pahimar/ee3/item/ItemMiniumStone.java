package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.CustomItemRarity;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemMiniumStone
 * 
 * The "lesser" or "imperfect" Philosophers Stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMiniumStone extends ItemEE implements ITransmutationStone,
        IKeyBound {

    public ItemMiniumStone(int id) {

        super(id);
        this.setIconCoord(1, 0);
        this.setItemName(Strings.MINIUM_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setMaxDamage(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY - 1);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {

        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
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

        transmuteBlock(itemStack, entityPlayer, world, x, y, z, sideHit);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {

        return Integer.parseInt(Colours.PURE_RED, 16);
    }

    @Override
    public void openPortableCrafting(EntityPlayer thePlayer) {

        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit) {

        EquivalentExchange3.proxy.transmuteBlock(itemStack, player, world, x, y, z, sideHit);
    }

    @Override
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {

        if (keyBinding.equals(ConfigurationSettings.KEYBINDING_EXTRA)) {
            openPortableCrafting(thePlayer);
        }
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_TOGGLE)) {
            if (TransmutationHelper.targetBlockStack != null) {
                TransmutationHelper.targetBlockStack = TransmutationHelper.getNextBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
            }
        }

    }

}
