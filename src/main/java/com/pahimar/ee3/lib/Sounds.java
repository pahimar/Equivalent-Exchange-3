package com.pahimar.ee3.lib;

/**
 * Equivalent-Exchange-3
 * 
 * Sounds
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Sounds {

    private static final String SOUND_RESOURCE_LOCATION = Reference.MOD_ID.toLowerCase() + ":";
    private static final String SOUND_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "chargeDown.ogg", SOUND_RESOURCE_LOCATION + "chargeUp.ogg", SOUND_RESOURCE_LOCATION + "destruct.ogg", SOUND_RESOURCE_LOCATION + "fail.ogg", SOUND_RESOURCE_LOCATION + "gust.ogg", SOUND_RESOURCE_LOCATION + "heal.ogg", SOUND_RESOURCE_LOCATION + "kinesis.ogg", SOUND_RESOURCE_LOCATION + "launch.ogg", SOUND_RESOURCE_LOCATION + "nova.ogg", SOUND_RESOURCE_LOCATION + "philball.ogg", SOUND_RESOURCE_LOCATION + "tock.ogg", SOUND_RESOURCE_LOCATION + "transmute.ogg", SOUND_RESOURCE_LOCATION + "wall.ogg", SOUND_RESOURCE_LOCATION + "waterball.ogg", SOUND_RESOURCE_LOCATION + "wind.ogg" };

    public static final String CHARGE_DOWN = SOUND_PREFIX + "chargeDown";
    public static final String CHARGE_UP = SOUND_PREFIX + "chargeUp";
    public static final String DESTRUCTION = SOUND_PREFIX + "destruct";
    public static final String FAIL = SOUND_PREFIX + "fail";
    public static final String GUST = SOUND_PREFIX + "gust";
    public static final String HEAL = SOUND_PREFIX + "heal";
    public static final String KINESIS = SOUND_PREFIX + "kinesis";
    public static final String LAUNCH = SOUND_PREFIX + "launch";
    public static final String NOVA = SOUND_PREFIX + "nova";
    public static final String PHILOSOPHERS_BALL = SOUND_PREFIX + "philball";
    public static final String TOCK = SOUND_PREFIX + "tock";
    public static final String TRANSMUTE = SOUND_PREFIX + "transmute";
    public static final String WALL = SOUND_PREFIX + "wall";
    public static final String WATER_BALL = SOUND_PREFIX + "waterball";
    public static final String WIND = SOUND_PREFIX + "wind";

    public static final String CHEST_OPEN = "random.chestopen";
    public static final String CHEST_CLOSE = "random.chestclosed";
    public static final String CHARGE_FAIL = FAIL;

}
