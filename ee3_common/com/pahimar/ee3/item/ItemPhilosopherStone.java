package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.lib.ConfigurationSettings;
import com.pahimar.ee3.lib.CustomItemRarity;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Sounds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketKeyPressed;
import com.pahimar.ee3.network.packet.PacketRequestEvent;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        this.setIconCoord(2, 0);
        this.setItemName(Strings.PHILOSOPHER_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setMaxDamage(ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY - 1);
        this.maxChargeLevel = 3;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        return true;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {

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

        //boolean result = TransmutationHelper.transmuteInWorld(world, entityPlayer, itemStack, x, y, z);
        boolean result = true;
        if (!world.isRemote) {
            EquivalentExchange3.proxy.sendWorldEventPacket(ActionTypes.TRANSMUTATION, x, y, z, (byte)sideHit, (byte)getCharge(itemStack), (byte)getCharge(itemStack), (byte)getCharge(itemStack), "50:0");
        }
        /*
        if (result) {
            itemStack.damageItem(1, entityPlayer);
        }
        */
        return result;
    }

    @Override
    public void openPortableCrafting(EntityPlayer thePlayer) {

        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
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
            openPortableCrafting(thePlayer);
        }
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_CHARGE)) {
            if (!thePlayer.isSneaking()) {
                increaseCharge(itemStack);
                thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.CHARGE_UP, 0.5F, 0.5F + (0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel)));
            }
            else {
                decreaseCharge(itemStack);
                thePlayer.worldObj.playSoundAtEntity(thePlayer, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - (0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel))));
            }
        }

    }

}
