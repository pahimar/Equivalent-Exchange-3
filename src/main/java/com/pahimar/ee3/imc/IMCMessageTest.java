package com.pahimar.ee3.imc;

import com.pahimar.ee3.util.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

/**
 * An implementation of {@link IMCMessageHandler}
 * @author thebaloneyboy
 *
 */
public class IMCMessageTest extends IMCMessageHandler {
	
	@Override
	public boolean handleMessage(Object message) {
		//If we know what this class is supposed to receive, then we can skip all of this.
		//This is just for demonstration purposes.
		if (message instanceof ItemStack) {
			//Preform whatever logic for the ItemStack
			Item item = ((ItemStack) message).getItem();
			LogHelper.debug("Test IMC message recieved. Contained ItemStack. Item in ItemStack is: " + item);
		} else if (message instanceof NBTTagCompound) {
			//Preform whatever logic for the NBTTagCompound
			LogHelper.debug("Test IMC message received. Contaned NBTTagCompound.");
		} else {
			//If its neither of the two, then the message must be a String.
			//Preform whatever logic for the String.
			LogHelper.debug("Test IMC message received. Contained String. Received " + message);
		}
		return true;
	}
}
