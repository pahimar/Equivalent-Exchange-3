package ee3.item;

import ee3.lib.Reference;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.forge.ITextureProvider;

/**
 * TODO Class Description 
 * @author pahimar, x3n0ph0b3
 *
 */
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

	public String getString(ItemStack ist, String s) {
        if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
		if (!ist.stackTagCompound.hasKey(s))
			setString(ist, s, "");
		
		return ist.stackTagCompound.getString(s);
	}
	
	public void setString(ItemStack ist, String s, String s1) {
		if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
		ist.stackTagCompound.setString(s, s1);
	}
	
    public boolean getBoolean(ItemStack ist, String s) {
        if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
		if (!ist.stackTagCompound.hasKey(s))
			setBoolean(ist, s, false);
		
		return ist.stackTagCompound.getBoolean(s);
	}
    
	public void setBoolean(ItemStack ist, String s, boolean b) {
		if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
		ist.stackTagCompound.setBoolean(s, b);
	}
	
    public short getShort(ItemStack ist, String s) {
        if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
    	if (!ist.stackTagCompound.hasKey(s))
    		setShort(ist, s, 0);
    	
    	return (short)(ist.stackTagCompound.getShort(s));
    }
    
    public void setShort(ItemStack ist, String s, int i) {
		if (ist.stackTagCompound == null)
        	ist.setTagCompound(new NBTTagCompound());
        
    	ist.stackTagCompound.setShort(s, (short)i);
    }
    
	public int getInteger(ItemStack ist, String s) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		if (!ist.stackTagCompound.hasKey(s))
			setInteger(ist, s, 0);
		
		return ist.stackTagCompound.getInteger(s);
	}
	
	public void setInteger(ItemStack ist, String s, int i) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		ist.stackTagCompound.setInteger(s, i);
	}
	
	public byte getByte(ItemStack ist, String s) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		if (!ist.stackTagCompound.hasKey(s))
			setByte(ist, s, (byte)0);
		
		return ist.stackTagCompound.getByte(s);
	}
	
	public void setByte(ItemStack ist, String s, byte i) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		ist.stackTagCompound.setByte(s, i);
	}
	
	public long getLong(ItemStack ist, String s) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		if (!ist.stackTagCompound.hasKey(s))
			setLong(ist, s, 0);
		
		return ist.stackTagCompound.getLong(s);
	}
	
	public void setLong(ItemStack ist, String s, long i) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		ist.stackTagCompound.setLong(s, i);
	}
	
	public float getFloat(ItemStack ist, String s) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		if (!ist.stackTagCompound.hasKey(s))
			setFloat(ist, s, 0F);
		
		return ist.stackTagCompound.getFloat(s);
	}
	
	public void setFloat(ItemStack ist, String s, float f) {
		if (ist.stackTagCompound == null)
			ist.setTagCompound(new NBTTagCompound());
		
		ist.stackTagCompound.setFloat(s, f);
	}
}
