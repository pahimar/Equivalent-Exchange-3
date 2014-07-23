package com.pahimar.ee3.imc;
/** A class which demonstrates the basic handler of an IMC message received. 
 * The class receives an object (ItemStack, NBTTagCompound, String) and can preform logic.
 * If the operation is successful, then it returns true. Otherwise, it returns false.
 * 
 * See {@link IMCMessageTest} for an implementation
 *  
 * @author thebaloneyboy
 *
 */
public class IMCMessageHandler {
	
	public boolean handleMessage(Object message) {
		return true;
	}
}
