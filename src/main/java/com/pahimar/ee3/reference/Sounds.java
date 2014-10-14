package com.pahimar.ee3.reference;

import java.util.Random;

public class Sounds
{
    private static final Random random = new Random();

    public static final String CHEST_OPEN = "random.chestopen";
    public static final String CHEST_CLOSE = "random.chestclosed";

    public static final String CHARGE_DOWN = "chargeDown";
    public static final String CHARGE_UP = "chargeUp";
    public static final String DESTRUCTION = "destruct";
    public static final String FAIL = "fail";
    public static final String GUST = "gust";
    public static final String HEAL = "heal";
    public static final String KINESIS = "kinesis";
    public static final String LAUNCH = "launch";
    public static final String NOVA = "nova";
    public static final String PHILOSOPHERS_BALL = "philball";
    public static final String TOCK = "tock";
    public static final String TRANSMUTE = "transmute";
    public static final String WALL = "wall";
    public static final String WATER_BALL = "waterball";
    public static final String WIND = "wind";

    public static final class Chalk
    {
        private static final String CHALK_PREFIX = "chalk_";
        private static final int CHALK_SOUNDS_COUNT = 11;

        public static final String getRandomChalkSound()
        {
            return String.format("%s%s", CHALK_PREFIX, random.nextInt(CHALK_SOUNDS_COUNT) + 1);
        }
    }
}
