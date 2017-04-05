package com.pahimar.ee.reference;

public enum Key {

    UNKNOWN, CHARGE, EXTRA, RELEASE, TOGGLE;

    public static final Key[] KEYS = Key.values();

    public static Key getKey(byte ordinal) {
        return KEYS[ordinal % KEYS.length];
    }
}
