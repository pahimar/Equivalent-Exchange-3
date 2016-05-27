package com.pahimar.ee3.inventory;

import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.inventory.element.IElementButtonHandler;
import com.pahimar.ee3.inventory.element.IElementTextFieldHandler;
import com.pahimar.ee3.item.ItemAlchenomicon;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.ItemStackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ContainerAlchenomicon extends ContainerEE implements IElementButtonHandler, IElementTextFieldHandler
{
    private final InventoryAlchenomicon inventoryAlchenomicon;
    private int pageOffset, maxPageOffset;
    private String searchTerm;
    private boolean requiresUpdate = false;

    private final static int MAX_ROW_INDEX = 8;
    private final static int MAX_COLUMN_INDEX = 5;

    public ContainerAlchenomicon(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        TreeSet<ItemStack> knownTransmutations = new TreeSet<>(Comparators.DISPLAY_NAME_COMPARATOR);

        if (itemStack.getItem() instanceof ItemAlchenomicon && ItemStackUtils.getOwnerName(itemStack) != null) {
            String playerName = ItemStackUtils.getOwnerName(itemStack);
            knownTransmutations.addAll(PlayerKnowledgeRegistryProxy.getKnownItemStacks(playerName));
        }

        inventoryAlchenomicon = new InventoryAlchenomicon(knownTransmutations);
        pageOffset = 0;
        maxPageOffset = knownTransmutations.size() / 80;

        int i = 0;
        for (int rowIndex = 0; rowIndex < MAX_ROW_INDEX; ++rowIndex)
        {
            for (int columnIndex = 0; columnIndex < MAX_COLUMN_INDEX; ++columnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryAlchenomicon, i, 18 + columnIndex * 20, 18 + rowIndex * 19)
                {
                    @Override
                    public boolean canTakeStack(EntityPlayer player)
                    {
                        return false;
                    }

                    @Override
                    @SideOnly(Side.CLIENT)
                    public boolean func_111238_b()
                    {
                        return false;
                    }
                });
                i++;
            }
        }

        i = 40;
        for (int rowIndex = 0; rowIndex < MAX_ROW_INDEX; ++rowIndex)
        {
            for (int columnIndex = 0; columnIndex < MAX_COLUMN_INDEX; ++columnIndex)
            {
                this.addSlotToContainer(new Slot(inventoryAlchenomicon, i, 140 + columnIndex * 20, 18 + rowIndex * 19)
                {
                    @Override
                    public boolean canTakeStack(EntityPlayer player)
                    {
                        return false;
                    }

                    @Override
                    @SideOnly(Side.CLIENT)
                    public boolean func_111238_b()
                    {
                        return false;
                    }
                });
                i++;
            }
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.pageOffset);
        iCrafting.sendProgressBarUpdate(this, 1, this.maxPageOffset);
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        if (requiresUpdate)
        {
            for (Object crafter : this.crafters)
            {
                ICrafting icrafting = (ICrafting) crafter;
                icrafting.sendProgressBarUpdate(this, 0, this.pageOffset);
                icrafting.sendProgressBarUpdate(this, 1, this.maxPageOffset);
            }

            requiresUpdate = false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int valueType, int updatedValue)
    {
        if (valueType == 0)
        {
            this.pageOffset = updatedValue;
        }
        else if (valueType == 1)
        {
            this.maxPageOffset = updatedValue;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    public int getInventorySize()
    {
        return inventoryAlchenomicon.getSizeInventory();
    }

    public int getKnownTransmutationsCount()
    {
        return inventoryAlchenomicon.getKnownTransmutations().size();
    }

    public int getPageOffset()
    {
        return this.pageOffset;
    }

    public int getMaxPageOffset()
    {
        return this.maxPageOffset;
    }

    @Override
    public void handleElementButtonClick(String elementName, int mouseButton)
    {
        if (elementName.equalsIgnoreCase("prev") && mouseButton == 0 && this.pageOffset > 0)
        {
            this.pageOffset--;
            updateInventory();
        }
        else if (elementName.equalsIgnoreCase("next") && mouseButton == 0 && this.pageOffset < this.maxPageOffset)
        {
            this.pageOffset++;
            updateInventory();
        }
    }

    @Override
    public void handleElementTextFieldUpdate(String elementName, String updatedText)
    {
        if (elementName.equalsIgnoreCase("searchField"))
        {
            this.searchTerm = updatedText;
            pageOffset = 0;
            updateInventory();
        }
    }

    private void updateInventory()
    {
        this.requiresUpdate = true;
        boolean shouldUpdateInventory = false;
        ItemStack[] newInventory = new ItemStack[80];
        List<ItemStack> filteredList = new ArrayList<>(FilterUtils.filterByDisplayName(inventoryAlchenomicon.getKnownTransmutations(), searchTerm, FilterUtils.NameFilterType.CONTAINS, Comparators.DISPLAY_NAME_COMPARATOR));

        maxPageOffset = filteredList.size() / 80;
        if (pageOffset > maxPageOffset)
        {
            pageOffset = 0;
        }

        if (pageOffset == 0)
        {
            if (filteredList.size() <= 80)
            {
                newInventory = filteredList.toArray(newInventory);
                shouldUpdateInventory = true;
            }
            else
            {
                newInventory = filteredList.subList(0, 80).toArray(newInventory);
                shouldUpdateInventory = true;
            }
        }
        else if (pageOffset < maxPageOffset)
        {
            newInventory = filteredList.subList(pageOffset * 80, (pageOffset + 1) * 80).toArray(newInventory);
            shouldUpdateInventory = true;
        }
        else if (pageOffset == maxPageOffset)
        {
            newInventory = filteredList.subList(pageOffset * 80, filteredList.size() - 1).toArray(newInventory);
            shouldUpdateInventory = true;
        }

        if (shouldUpdateInventory)
        {
            for (int i = 0; i < 80; i++)
            {
                inventoryAlchenomicon.setInventorySlotContents(i, newInventory[i]);
                inventoryAlchenomicon.markDirty();
            }
        }
    }
}
