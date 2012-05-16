package ee3.item;

import ee3.lib.Reference;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;

public class InventoryLootBall implements IInventory {

	public ItemStack parent;
	public ItemStack[] inventoryStacks;
	
	public InventoryLootBall(int i) {
		inventoryStacks = new ItemStack[i];
	}

	public InventoryLootBall(ItemStack parent, ItemStack[] itemStacks) {
		this.parent = parent;
		this.inventoryStacks = itemStacks;
		
		readFromNBT(parent.getTagCompound());
	}
	
	private void save() {
        NBTTagCompound nbttagcompound = parent.getTagCompound();
        if(nbttagcompound == null)
            nbttagcompound = new NBTTagCompound();
        writeToNBT(nbttagcompound);
        parent.setTagCompound(nbttagcompound);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		if(nbttagcompound == null)
            return;

		if(nbttagcompound.hasKey("Items")) {
            NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
            inventoryStacks = new ItemStack[getSizeInventory()];
            for(int i = 0; i < nbttaglist.tagCount(); i++) {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
                byte byte0 = nbttagcompound1.getByte("Slot");
                if(byte0 >= 0 && byte0 < inventoryStacks.length)
                        inventoryStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
		}
	}
	
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < inventoryStacks.length; i++)
            if(inventoryStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                inventoryStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        nbttagcompound.setTag("Items", nbttaglist);
	}
	
	public ItemStack[] getInventoryContents() { return inventoryStacks; }
	@Override public int getSizeInventory() { return inventoryStacks.length; }
	@Override public ItemStack getStackInSlot(int var1) { return inventoryStacks[var1]; }
	@Override public ItemStack decrStackSize(int var1, int var2) { return null; }
	@Override public ItemStack getStackInSlotOnClosing(int var1) { return null; }
	@Override public void setInventorySlotContents(int var1, ItemStack var2) { inventoryStacks[var1] = var2; }
	@Override public String getInvName() { return "Loot Ball"; }
	@Override public int getInventoryStackLimit() { return 64; }
	@Override public void onInventoryChanged() { }
	@Override public boolean isUseableByPlayer(EntityPlayer var1) { return true; }
	@Override public void openChest() { }
	@Override public void closeChest() { }
}
