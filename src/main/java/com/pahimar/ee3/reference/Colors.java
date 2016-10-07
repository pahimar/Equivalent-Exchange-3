package com.pahimar.ee3.reference;

public class Colors {

    public static final int PURE_WHITE = 0xFFFFFF;

    // Alchemical Dust
    public static final int DUST_ASH = 0xA0A0A0;
    public static final int DUST_MINIUM = 0xFF4545;
    public static final int[] DUST_COLOURS = new int[]{DUST_ASH, DUST_MINIUM};

    public static final String[] DYE_NAMES = {"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};

    public enum TextColor {
        BLACK("\u00A70"),
        DARK_BLUE("\u00A71"),
        DARK_GREEN("\u00A72"),
        DARK_CYAN("\u00A73"),
        DARK_RED("\u00A74"),
        PURPLE("\u00A75"),
        ORANGE("\u00A76"),
        LIGHT_GREY("\u00A77"),
        DARK_GREY("\u00A78"),
        LIGHT_GREEN("\u00A7a"),
        LIGHT_CYAN("\u00A7b"),
        LIGHT_RED("\u00A7c"),
        PINK("\u00A7d"),
        YELLOW("\u00A7e"),
        WHITE("\u00A7f");

        private String color;

        TextColor(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public String toString() {
            return color;
        }
    }
}
