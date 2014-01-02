package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.helper.TransmutationHelper;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Sounds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketSoundEvent;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemPhilosophersStone
 *
 * @author pahimar
 */
public class ItemPhilosophersStone extends ItemEE implements ITransmutationStone, IChargeable, IKeyBound
{
    private int maxChargeLevel;

    public ItemPhilosophersStone(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.PHILOSOPHERS_STONE_NAME);
        this.setMaxDamage(ConfigurationSettings.PHILOSOPHERS_STONE_MAX_DURABILITY - 1);
        maxChargeLevel = 3;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack itemStack, int renderPass)
    {
        return ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_CRAFTING_GUI_OPEN) || ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack)
    {
        return EnumChatFormatting.YELLOW + super.getItemDisplayName(itemStack);
    }

    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack)
    {
        return false;
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack)
    {
        ItemStack copiedStack = itemStack.copy();

        copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);

        // TODO Is this still necessary?
        copiedStack.stackSize = 1;

        return copiedStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int sideHit, float hitVecX, float hitVecY, float hitVecZ)
    {
        if (world.isRemote)
        {
            transmuteBlock(itemStack, entityPlayer, world, x, y, z, sideHit);
        }
        return true;
    }

    @Override
    public void openPortableCraftingGUI(EntityPlayer thePlayer, ItemStack itemStack)
    {
        ItemStackNBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_CRAFTING_GUI_OPEN, true);
        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int) thePlayer.posX, (int) thePlayer.posY, (int) thePlayer.posZ);
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit)
    {
        EquivalentExchange3.proxy.transmuteBlock(itemStack, player, world, x, y, z, sideHit);
    }

    @Override
    public short getCharge(ItemStack stack)
    {
        return ItemStackNBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY);
    }

    @Override
    public void setCharge(ItemStack stack, short charge)
    {
        if (charge <= maxChargeLevel)
        {
            ItemStackNBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, charge);
        }
    }

    @Override
    public void increaseCharge(ItemStack stack)
    {
        if (ItemStackNBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) < maxChargeLevel)
        {
            ItemStackNBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, (short) (ItemStackNBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) + 1));
        }
    }

    @Override
    public void decreaseCharge(ItemStack stack)
    {
        if (ItemStackNBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) > 0)
        {
            ItemStackNBTHelper.setShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY, (short) (ItemStackNBTHelper.getShort(stack, Strings.NBT_ITEM_CHARGE_LEVEL_KEY) - 1));
        }
    }

    @Override
    public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding)
    {
        if (keyBinding.equals(ConfigurationSettings.KEYBINDING_EXTRA))
        {
            if (!thePlayer.isSneaking())
            {
                openPortableCraftingGUI(thePlayer, itemStack);
            }
        }
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_TOGGLE))
        {
            if (TransmutationHelper.targetBlockStack != null)
            {
                if (!thePlayer.isSneaking())
                {
                    TransmutationHelper.targetBlockStack = TransmutationHelper.getNextBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
                }
                else
                {
                    TransmutationHelper.targetBlockStack = TransmutationHelper.getPreviousBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
                }
            }
        }
        else if (keyBinding.equals(ConfigurationSettings.KEYBINDING_CHARGE))
        {
            if (!thePlayer.isSneaking())
            {
                if (getCharge(itemStack) == maxChargeLevel)
                {
                    PacketDispatcher.sendPacketToAllAround(thePlayer.posX, thePlayer.posY, thePlayer.posZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.FAIL, thePlayer.posX, thePlayer.posY, thePlayer.posZ, 1.5F, 1.5F)));
                }
                else
                {
                    increaseCharge(itemStack);
                    PacketDispatcher.sendPacketToAllAround(thePlayer.posX, thePlayer.posY, thePlayer.posZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.CHARGE_UP, thePlayer.posX, thePlayer.posY, thePlayer.posZ, 0.5F, 0.5F + 0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel))));
                }
            }
            else
            {
                if (getCharge(itemStack) == 0)
                {
                    PacketDispatcher.sendPacketToAllAround(thePlayer.posX, thePlayer.posY, thePlayer.posZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.FAIL, thePlayer.posX, thePlayer.posY, thePlayer.posZ, 1.5F, 1.5F)));
                }
                else
                {
                    decreaseCharge(itemStack);
                    PacketDispatcher.sendPacketToAllAround(thePlayer.posX, thePlayer.posY, thePlayer.posZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.CHARGE_DOWN, thePlayer.posX, thePlayer.posY, thePlayer.posZ, 0.5F, 1.0F - (0.5F - 0.5F * (getCharge(itemStack) * 1.0F / maxChargeLevel)))));
                }
            }
        }
    }
}
