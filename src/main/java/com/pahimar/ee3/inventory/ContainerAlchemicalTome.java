package com.pahimar.ee3.inventory;

import com.pahimar.ee3.knowledge.TransmutationKnowledgeRegistry;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.repackage.cofh.lib.gui.slot.SlotViewOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

import java.util.TreeSet;

public class ContainerAlchemicalTome extends ContainerEE
{
    private final InventoryTransmutationKnowledge inventoryTransmutationKnowledge;
    private int pageOffset;

    private final static int MAX_ROW_INDEX = 8;
    private final static int MAX_COLUMN_INDEX = 5;

    public ContainerAlchemicalTome(InventoryPlayer inventoryPlayer)
    {
        TreeSet<ItemStack> knownTransmutations = new TreeSet<ItemStack>(ItemHelper.displayNameComparator);
        knownTransmutations.addAll(TransmutationKnowledgeRegistry.getInstance().getPlayersKnownTransmutations(inventoryPlayer.player.getUniqueID()));

        inventoryTransmutationKnowledge = new InventoryTransmutationKnowledge(knownTransmutations);
        pageOffset = 0;

        int i = 0;
        for (int rowIndex = 0; rowIndex < MAX_ROW_INDEX; ++rowIndex)
        {
            for (int columnIndex = 0; columnIndex < MAX_COLUMN_INDEX; ++columnIndex)
            {
                this.addSlotToContainer(new SlotViewOnly(inventoryTransmutationKnowledge, i, 18 + columnIndex * 20, 18 + rowIndex * 19));
                i++;
            }
        }

        i = 40;
        for (int rowIndex = 0; rowIndex < MAX_ROW_INDEX; ++rowIndex)
        {
            for (int columnIndex = 0; columnIndex < MAX_COLUMN_INDEX; ++columnIndex)
            {
                this.addSlotToContainer(new SlotViewOnly(inventoryTransmutationKnowledge, i, 140 + columnIndex * 20, 18 + rowIndex * 19));
                i++;
            }
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return true;
    }

    public int getInventorySize()
    {
        return inventoryTransmutationKnowledge.getSizeInventory();
    }
}
