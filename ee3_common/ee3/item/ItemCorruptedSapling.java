package ee3.item;

import net.minecraft.src.ItemSapling;

public class ItemCorruptedSapling extends ItemSapling {

	public ItemCorruptedSapling(int par1) {
		super(par1);
	}

	public int getColorFromDamage(int par1, int par2) {
		return Integer.parseInt("A5162E", 16);
    }
	
}
