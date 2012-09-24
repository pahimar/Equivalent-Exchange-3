package ee3.common.core.helper;

import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;

public class NBTHelper {

	/**
	 * Initializes the NBT Tag Compound for the given ItemStack if it is null
	 * @param itemStack The ItemStack for which its NBT Tag Compound is being checked for initialization
	 */
	private static void initNBTTagCompound(ItemStack itemStack) {
		if (itemStack.stackTagCompound == null) {
			itemStack.setTagCompound(new NBTTagCompound());
		}
	}
	
	
	// String
	public static String getString(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setString(itemStack, keyName, "");
		}
		
		return itemStack.stackTagCompound.getString(keyName);
	}
	
	public static void setString(ItemStack itemStack, String keyName, String keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setString(keyName, keyValue);
	}
	
	
	// boolean
	public static boolean getBoolean(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setBoolean(itemStack, keyName, false);
		}
		
		return itemStack.stackTagCompound.getBoolean(keyName);
	}
	
	public static void setBoolean(ItemStack itemStack, String keyName, boolean keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setBoolean(keyName, keyValue);
	}
	
	
	// byte
	public static byte getByte(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setByte(itemStack, keyName, (byte) 0);
		}
		
		return itemStack.stackTagCompound.getByte(keyName);
	}
	
	public static void setByte(ItemStack itemStack, String keyName, byte keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setByte(keyName, keyValue);
	}
	
	
	// short
	public static short getShort(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setShort(itemStack, keyName, (short) 0);
		}
		
		return itemStack.stackTagCompound.getShort(keyName);
	}
	
	public static void setShort(ItemStack itemStack, String keyName, short keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setShort(keyName, keyValue);
	}
	
	
	// int
	public static int getInt(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setInteger(itemStack, keyName, 0);
		}
		
		return itemStack.stackTagCompound.getInteger(keyName);
	}
	
	public static void setInteger(ItemStack itemStack, String keyName, int keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setInteger(keyName, keyValue);
	}
	
	
	// long
	public static long getLong(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setLong(itemStack, keyName, 0);
		}
		
		return itemStack.stackTagCompound.getLong(keyName);
	}
	
	public static void setLong(ItemStack itemStack, String keyName, long keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setLong(keyName, keyValue);
	}
	
	
	// float
	public static float getFloat(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setFloat(itemStack, keyName, 0);
		}
		
		return itemStack.stackTagCompound.getFloat(keyName);
	}
	
	public static void setFloat(ItemStack itemStack, String keyName, float keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setFloat(keyName, keyValue);
	}
	
	
	// double
	public static double getDouble(ItemStack itemStack, String keyName) {
		initNBTTagCompound(itemStack);
		
		if (!itemStack.stackTagCompound.hasKey(keyName)) {
			setDouble(itemStack, keyName, 0);
		}
		
		return itemStack.stackTagCompound.getDouble(keyName);
	}
	
	public static void setDouble(ItemStack itemStack, String keyName, double keyValue) {
		initNBTTagCompound(itemStack);
		
		itemStack.stackTagCompound.setDouble(keyName, keyValue);
	}
	
}
