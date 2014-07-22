package com.pahimar.ee3.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import com.pahimar.ee3.imc.IMCMessageHandler;
import com.pahimar.ee3.imc.IMCMessageTest;


import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;

/**
 * This class handles all IMC messages received by EE3. 
 * When it receives a message, the handler looks up the key of the message for the appropriate message handler.
 * This class handles registration of IMC messages, and registration of IMC messages. 
 * Registration of the message handlers goes in Pre-Init. 
 * Lookup and handling happens when an IMCEvent is fired off.
 * 
 * Also sends messages as well. Send messages in Pre-Init.
 * 
 * @author thebaloneyboy
 *
 */
public class IMCHandler {
	
	//Create a map which stores our IMC keys and Message Handlers
	private static final Map<String, Class<? extends IMCMessageHandler>> messageHandlers = new LinkedHashMap<String, Class<? extends IMCMessageHandler>>();
	
	//This gets called whenever we receive a message.
	public static void handleIMCMessage(IMCMessage message) throws InstantiationException, IllegalAccessException  {
		//Get the key of the message, so we can find its Message Handler.
		String key = message.key;
		//Get and create a new instance of the Key's Handler
		Class<? extends IMCMessageHandler> clazz = messageHandlers.get(key);
		IMCMessageHandler handler = clazz.newInstance();
		//Get the Message as an Object
		Object contents = getContentsFromMessage(message);
		//Pass the message to its handler.
		handler.handleMessage(contents);

	}
	
	//Registers Message Handlers and their Keys. Call in Pre-Init.
	public static void registerMessageHandlers() {
		registerMessageHandler("test", IMCMessageTest.class);
	}
	
	//Puts the Key and Handler in the map, so we can look it up later
	private static void registerMessageHandler(String key, Class<? extends IMCMessageHandler> handler) {
		messageHandlers.put(key, handler);
	}
	
	//Gets the Contents of an IMC message. Returns an Object (ItemStack, NBTTagCompound, String)
	protected static Object getContentsFromMessage(IMCMessage message) {
		if (message.isItemStackMessage()) {
			return message.getItemStackValue();
		} else if (message.isNBTMessage()) {
			return message.getNBTValue();
		} else if (message.isStringMessage()) {
			return message.getStringValue();
		} else {
			return null;
		}
	}
	
	//Sends IMC Messages to ther Mods.
	public static void sendIMCMessages() {
		
	}
}
