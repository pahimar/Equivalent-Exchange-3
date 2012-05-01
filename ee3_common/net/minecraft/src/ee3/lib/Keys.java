package net.minecraft.src.ee3.lib;

public class Keys {
	
	public static final int EXTRA = 0;
	public static final int CHARGE = 1;
	public static final int TOGGLE = 2;
	public static final int RELEASE = 3;
	public static final int LEFT_CLICK = 4;
	public static final int JUMP = 5;
	public static final int SNEAK = 6;
	
	public static String toString(int key) {
		switch (key) {
			case Keys.CHARGE:
				return "KEYS.CHARGE";
			case Keys.EXTRA:
				return "KEYS.EXTRA";
			case Keys.TOGGLE:
				return "KEYS.TOGGLE";
			case Keys.RELEASE:
				return "KEYS.RELEASE";
			case Keys.LEFT_CLICK:
				return "KEYS.LEFT_CLICK";
			case Keys.JUMP:
				return "KEYS.JUMP";
			case Keys.SNEAK:
				return "KEYS.SNEAK";
			default:
				return "Unknown Key";
		}
	}
}
