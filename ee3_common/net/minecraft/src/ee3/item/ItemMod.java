package net.minecraft.src.ee3.item;

import net.minecraft.src.Item;
import net.minecraft.src.ee3.lib.Reference;
import net.minecraft.src.forge.ITextureProvider;

public class ItemMod extends Item implements ITextureProvider {

	public ItemMod(int i) {
		super(i);
		maxStackSize = 1;
		setNoRepair();
	}
	
	@Override
	public String getTextureFile() {
		return Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET;
	}

}
