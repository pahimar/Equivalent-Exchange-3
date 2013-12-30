package com.pahimar.ee3.client.helper;

import java.util.regex.Pattern;

public class ColourUtils
{
    private static final Pattern HEX_COLOUR_PATTERN = Pattern.compile("[0-9a-fA-F]{6}");

    @SuppressWarnings("unused")
    public static byte[] convertHexColorToByteArray(String hexColor)
    {
        if (HEX_COLOUR_PATTERN.matcher(hexColor).matches())
        {
            byte[] colourByteArray = new byte[3];

            colourByteArray[0] = (byte) Integer.parseInt(hexColor.substring(0, 2), 16);
            colourByteArray[1] = (byte) Integer.parseInt(hexColor.substring(2, 4), 16);
            colourByteArray[2] = (byte) Integer.parseInt(hexColor.substring(4, 6), 16);

            return colourByteArray;
        }

        return null;
    }

    public static byte[] convertIntColourToByteArray(int intColour)
    {
        byte[] colourByteArray = new byte[3];

        colourByteArray[0] = (byte) (intColour >> 16 & 255);
        colourByteArray[1] = (byte) (intColour >> 8 & 255);
        colourByteArray[2] = (byte) (intColour & 255);

        return colourByteArray;
    }

    public static float[] convertIntColourToFloatArray(int intColour)
    {
        float[] colourFloatArray = new float[3];

        colourFloatArray[0] = (float) (intColour >> 16 & 255) / 255F;
        colourFloatArray[1] = (float) (intColour >> 8 & 255) / 255F;
        colourFloatArray[2] = (float) (intColour & 255) / 255F;

        return colourFloatArray;
    }

    @SuppressWarnings("unused")
    public static byte[] getBlendedColours(String firstColour, String secondColour)
    {
        byte[] firstColourByteArray = convertHexColorToByteArray(firstColour);
        byte[] secondColourByteArray = convertHexColorToByteArray(secondColour);

        if (firstColourByteArray != null && secondColourByteArray != null)
        {
            return getBlendedColours(firstColourByteArray, secondColourByteArray);
        }
        else
        {
            return null;
        }
    }

    @SuppressWarnings("unused")
    public static byte[] getBlendedColours(byte[] firstColour, byte[] secondColour)
    {
        byte[][] blendedColours = getByteBlendedColours(firstColour, secondColour, 3);

        if (blendedColours.length == 3)
        {
            return blendedColours[1];
        }
        else
        {
            return null;
        }
    }

    public static byte[][] getByteBlendedColours(String firstColour, String secondColour, int steps)
    {
        byte[] firstColourByteArray = convertHexColorToByteArray(firstColour);
        byte[] secondColourByteArray = convertHexColorToByteArray(secondColour);

        if (firstColourByteArray != null && secondColourByteArray != null)
        {
            return getByteBlendedColours(firstColourByteArray, secondColourByteArray, steps);
        }
        else
        {
            return null;
        }
    }

    public static byte[][] getByteBlendedColours(byte[] firstColour, byte[] secondColour, int steps)
    {
        if (firstColour.length == 3 && secondColour.length == 3 && steps > 0)
        {
            byte[][] blendedColours = new byte[steps + 2][3];

            byte redDifference = (byte) (((secondColour[0] & 0xFF) - (firstColour[0] & 0xFF)) / steps);
            byte greenDifference = (byte) (((secondColour[1] & 0xFF) - (firstColour[1] & 0xFF)) / steps);
            byte blueDifference = (byte) (((secondColour[2] & 0xFF) - (firstColour[2] & 0xFF)) / steps);

            blendedColours[0] = firstColour;
            for (int i = 1; i < blendedColours.length - 1; i++)
            {
                blendedColours[i][0] = (byte) (firstColour[0] + i * redDifference);
                blendedColours[i][1] = (byte) (firstColour[1] + i * greenDifference);
                blendedColours[i][2] = (byte) (firstColour[2] + i * blueDifference);
            }
            blendedColours[blendedColours.length - 1] = secondColour;

            return blendedColours;
        }

        return null;
    }

    public static float[][] getFloatBlendedColours(byte[] firstColour, byte[] secondColour, int steps)
    {
        byte[][] byteBlendedColours = getByteBlendedColours(firstColour, secondColour, steps);

        if (byteBlendedColours != null)
        {
            float[][] floatBlendedColours = new float[byteBlendedColours.length][3];

            for (int i = 0; i < byteBlendedColours.length; i++)
            {
                floatBlendedColours[i][0] = (byteBlendedColours[i][0] & 0xFF) / 255F;
                floatBlendedColours[i][1] = (byteBlendedColours[i][1] & 0xFF) / 255F;
                floatBlendedColours[i][2] = (byteBlendedColours[i][2] & 0xFF) / 255F;
            }

            return floatBlendedColours;
        }
        else
        {
            return null;
        }
    }

    public static float[][] getFloatBlendedColours(String firstColour, String secondColour, int steps)
    {
        byte[] firstColourByteArray = convertHexColorToByteArray(firstColour);
        byte[] secondColourByteArray = convertHexColorToByteArray(secondColour);

        if (firstColourByteArray != null && secondColourByteArray != null)
        {
            return getFloatBlendedColours(firstColourByteArray, secondColourByteArray, steps);
        }
        else
        {
            return null;
        }
    }

    public static float[][] getFloatBlendedColours(int firstColour, int secondColour, int steps)
    {
        byte[] firstColourByteArray = convertIntColourToByteArray(firstColour);
        byte[] secondColourByteArray = convertIntColourToByteArray(secondColour);

        if (firstColourByteArray != null && secondColourByteArray != null)
        {
            return getFloatBlendedColours(firstColourByteArray, secondColourByteArray, steps);
        }
        else
        {
            return null;
        }
    }
}
