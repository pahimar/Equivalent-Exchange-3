package com.pahimar.ee3.client.util;

public class ColorUtils
{
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

        colourFloatArray[0] = ((intColour >> 16 & 0xFF) / 255F);
        colourFloatArray[1] = ((intColour >> 8 & 0xFF) / 255F);
        colourFloatArray[2] = ((intColour & 0xFF) / 255F);

        return colourFloatArray;
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
