package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.ConfigurationSettings;
import com.pahimar.ee3.lib.CustomItemRarity;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketKeyPressed;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
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
public class ItemMiniumStone extends ItemEE
        implements ITransmutationStone, IKeyBound {

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

        boolean result = TransmutationHelper.transmuteInWorld(world, entityPlayer, itemStack, x, y, z);

        if (result) {
            itemStack.damageItem(1, entityPlayer);
        }

        return result;
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
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) {

        if (keyBinding.equals(ConfigurationSettings.KEYBINDING_EXTRA)) {
            openPortableCrafting(thePlayer);
        }

    }

}
