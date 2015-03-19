package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.block.BlockAshInfusedStoneSlab;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.item.ItemAlchemicalTome;
import com.pahimar.ee3.item.ItemMiniumStone;
import com.pahimar.ee3.item.ItemPhilosophersStone;
import com.pahimar.ee3.knowledge.AbilityRegistry;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityTransmutationTablet;
import com.pahimar.ee3.reference.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityTransmutationTablet extends TileEntityEE implements IInventory
{
    private static final int INVENTORY_SIZE = 10;
    public static final int ITEM_INPUT_1 = 0;
    public static final int ITEM_INPUT_2 = 1;
    public static final int ITEM_INPUT_3 = 2;
    public static final int ITEM_INPUT_4 = 3;
    public static final int ITEM_INPUT_5 = 4;
    public static final int ITEM_INPUT_6 = 5;
    public static final int ITEM_INPUT_7 = 6;
    public static final int ITEM_INPUT_8 = 7;
    public static final int STONE_INDEX = 8;
    public static final int ALCHEMICAL_TOME_INDEX = 9;

    private EnergyValue energyValue;
    private ForgeDirection rotation;
    private ItemStack[] inventory;

    public TileEntityTransmutationTablet()
    {
        super();
        rotation = ForgeDirection.UNKNOWN;
        energyValue = new EnergyValue(0);
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    public EnergyValue getEnergyValue()
    {
        return energyValue;
    }

    public void setEnergyValue(EnergyValue energyValue)
    {
        this.energyValue = energyValue;
    }

    public ForgeDirection getRotation()
    {
        return rotation;
    }

    public void setRotation(ForgeDirection rotation)
    {
        this.rotation = rotation;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord - 1.5d, yCoord - 1, zCoord - 1.5d, xCoord + 1.5d, yCoord + 1, zCoord + 1.5d);
    }

    public boolean isStructureValid()
    {
        return ((worldObj.getBlock(xCoord - 1, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord - 1) == 1) &&
                (worldObj.getBlock(xCoord, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1) == 2) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord - 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord - 1) == 3) &&
                (worldObj.getBlock(xCoord - 1, yCoord, zCoord) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord) == 4) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord) == 5) &&
                (worldObj.getBlock(xCoord - 1, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord + 1) == 6) &&
                (worldObj.getBlock(xCoord, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1) == 7) &&
                (worldObj.getBlock(xCoord + 1, yCoord, zCoord + 1) instanceof BlockAshInfusedStoneSlab && worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord + 1) == 8));
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityTransmutationTablet(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }

        NBTTagCompound energyValueTagCompound = nbtTagCompound.getCompoundTag("energyValue");
        if (!energyValueTagCompound.hasNoTags())
        {
            energyValue = EnergyValue.loadEnergyValueFromNBT(energyValueTagCompound);
        }
        else
        {
            energyValue = new EnergyValue(0);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("rotation", rotation.ordinal());

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex)
        {
            if (inventory[currentIndex] != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                inventory[currentIndex].writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);

        NBTTagCompound energyValueTagCompound = new NBTTagCompound();
        if (energyValue != null)
        {
            energyValue.writeToNBT(energyValueTagCompound);
        }
        nbtTagCompound.setTag("energyValue", energyValueTagCompound);
    }

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return inventory[slotIndex];
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }

        float newEnergyValue = 0f;
        for (int i = 0; i <= STONE_INDEX; i++)
        {
            if (inventory[i] != null && EnergyValueRegistry.getInstance().hasEnergyValue(inventory[i]))
            {
                newEnergyValue += EnergyValueRegistry.getInstance().getEnergyValue(inventory[i]).getEnergyValue() * inventory[i].stackSize;
            }
        }
        this.energyValue = new EnergyValue(newEnergyValue);
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomName() ? this.getCustomName() : Names.Containers.TRANSMUTATION_TABLET;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return this.hasCustomName();
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        // NOOP
    }

    @Override
    public void closeInventory()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (slotIndex < STONE_INDEX && EnergyValueRegistry.getInstance().hasEnergyValue(itemStack) && AbilityRegistry.getInstance().isRecoverable(itemStack))
        {
            return true;
        }
        else if (slotIndex == STONE_INDEX && (itemStack.getItem() instanceof ItemMiniumStone || itemStack.getItem() instanceof ItemPhilosophersStone))
        {
            return true;
        }
        else if (slotIndex == ALCHEMICAL_TOME_INDEX && itemStack.getItem() instanceof ItemAlchemicalTome)
        {
            return true;
        }

        return false;
    }
}
