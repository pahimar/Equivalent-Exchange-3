package ee3.item;

import ee3.core.interfaces.IItemChargeable;
import ee3.core.interfaces.IItemModal;
import ee3.lib.CustomItemRarity;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.mod_EE3;

/**
 * TODO Class Description 
 * @author pahimar, x3n0ph0b3
 *
 */
public class ItemPhilosopherStone extends ItemEE implements IItemChargeable, IItemModal {

	private byte currentCharge;
	private byte maxCharge;
	
	private byte currentMode;
	private byte maxMode;
	
	public ItemPhilosopherStone(int i) {
		super(i);
		maxCharge = 4;
		maxMode = 2;
		
		this.setMaxDamage(1561);
	}
	
	/**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int par1)
    {
    	if (this.getMaxDamage() > 0)
    		return this.iconIndex;
    	else
    		return this.iconIndex + 1;
    }
	
	/*
	 * Returns the custom item rarity type for the item
	 * @see net.minecraft.src.Item#getRarity(net.minecraft.src.ItemStack)
	 */
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		if (getShort(par1ItemStack, "Damage") > 0)
			return mod_EE3.proxy.getCustomEnumRarityType(CustomItemRarity.RARE);
		else
			return mod_EE3.proxy.getCustomEnumRarityType(CustomItemRarity.MAGICAL);
    }
	
	/*
	 * Gives the Philosopher Stone a nice visual effect
	 * @see net.minecraft.src.Item#hasEffect(net.minecraft.src.ItemStack)
	 */
	public boolean hasEffect(ItemStack par1ItemStack) {
        return (getType(par1ItemStack) == 1);
    }

	@Override
	public byte getMaxCharge() {
		return maxCharge;
	}

	@Override
	public void increaseCharge(ItemStack ist) {
		if (getCurrentCharge(ist) < getMaxCharge())
			setCurrentCharge(ist, (byte)(getCurrentCharge(ist) + 1));			
	}

	@Override
	public void decreaseCharge(ItemStack ist) {
		if (getCurrentCharge(ist) > 0)
			setCurrentCharge(ist, (byte)(getCurrentCharge(ist) - 1));	
	}

	@Override
	public byte getCurrentCharge(ItemStack ist) {
		return getByte(ist, "currentCharge");
	}

	@Override
	public void setCurrentCharge(ItemStack ist, byte charge) {
		setByte(ist, "currentCharge", charge);
	}

	@Override
	public byte getCurrentMode(ItemStack ist) {
		return getByte(ist, "currentMode");
	}

	@Override
	public byte getMaxMode() {
		return maxMode;
	}

	@Override
	public byte cycleMode(ItemStack ist) {
		if (getCurrentMode(ist) < getMaxMode())
			setMode(ist, (byte)(getCurrentMode(ist) + 1));
		else
			setMode(ist, (byte)0);
		return getCurrentMode(ist);
	}

	@Override
	public void setMode(ItemStack ist, byte mode) {
		setByte(ist, "currentMode", mode);
	}
	
	public byte getType(ItemStack ist) {
		return getByte(ist, "type");
	}
	
	public void setType(ItemStack ist, byte type) {
		setByte(ist, "type", type);
		setShort(ist, "Damage", -1);
		ist.writeToNBT(ist.stackTagCompound);
		this.setMaxDamage(-1);
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack ist) {
        return false;
    }
}
