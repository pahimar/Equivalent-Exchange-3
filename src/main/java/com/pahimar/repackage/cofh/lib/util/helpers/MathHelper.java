package com.pahimar.repackage.cofh.lib.util.helpers;

import java.util.Random;

/**
 * Contains various math-related helper functions. Often faster than conventional implementations.
 *
 * @author King Lemming
 */
public final class MathHelper
{

    private MathHelper()
    {

    }

    public static final Random RANDOM = new Random();
    public static final double PHI = 1.618034;
    public static final double[] SIN_TABLE = new double[65536];

    static
    {
        for (int i = 0; i < 65536; i++)
        {
            SIN_TABLE[i] = Math.sin(i / 65536D * 2 * Math.PI);
        }
        SIN_TABLE[0] = 0;
        SIN_TABLE[16384] = 1;
        SIN_TABLE[32768] = 0;
        SIN_TABLE[49152] = 1;
    }

    public static double sin(double d)
    {

        return SIN_TABLE[(int) ((float) d * 10430.378F) & 65535];
    }

    public static double cos(double d)
    {

        return SIN_TABLE[(int) ((float) d * 10430.378F + 16384.0F) & 65535];
    }

    public static int clampI(int a, int min, int max)
    {

        return a < min ? min : (a > max ? max : a);
    }

    public static float clampF(float a, float min, float max)
    {

        return a < min ? min : (a > max ? max : a);
    }

    public static float approachLinear(float a, float b, float max)
    {

        return a > b ? a - b < max ? b : a - max : b - a < max ? b : a + max;
    }

    public static double approachLinear(double a, double b, double max)
    {

        return a > b ? a - b < max ? b : a - max : b - a < max ? b : a + max;
    }

    public static float interpolate(float a, float b, float d)
    {

        return a + (b - a) * d;
    }

    public static double interpolate(double a, double b, double d)
    {

        return a + (b - a) * d;
    }

    public static double approachExp(double a, double b, double ratio)
    {

        return a + (b - a) * ratio;
    }

    public static double approachExp(double a, double b, double ratio, double cap)
    {

        double d = (b - a) * ratio;

        if (Math.abs(d) > cap)
        {
            d = Math.signum(d) * cap;
        }
        return a + d;
    }

    public static double retreatExp(double a, double b, double c, double ratio, double kick)
    {

        double d = (Math.abs(c - a) + kick) * ratio;

        if (d > Math.abs(b - a))
        {
            return b;
        }
        return a + Math.signum(b - a) * d;
    }

    public static double clip(double value, double min, double max)
    {

        if (value > max)
        {
            value = max;
        }
        else if (value < min)
        {
            value = min;
        }
        return value;
    }

    public static boolean between(double a, double x, double b)
    {

        return a <= x && x <= b;
    }

    public static int approachExpI(int a, int b, double ratio)
    {

        int r = (int) Math.round(approachExp(a, b, ratio));
        return r == a ? b : r;
    }

    public static int retreatExpI(int a, int b, int c, double ratio, int kick)
    {

        int r = (int) Math.round(retreatExp(a, b, c, ratio, kick));
        return r == a ? b : r;
    }

    /**
     * Unchecked implementation to round a number. Parameter should be known to be valid in advance.
     */
    public static int round(double d)
    {

        return (int) (d + 0.5D);
    }

    /**
     * Unchecked implementation to round a number up. Parameter should be known to be valid in advance.
     */
    public static int ceil(double d)
    {

        return (int) (d + 0.9999D);
    }

    /**
     * Unchecked implementation to round a number down. Parameter should be known to be valid in advance.
     */
    public static int floor(double d)
    {

        int i = (int) d;
        return d < i ? i - 1 : i;
    }

    /**
     * Unchecked implementation to determine the smaller of two Floats. Parameters should be known to be valid in advance.
     */
    public static float minF(float a, float b)
    {

        return a < b ? a : b;
    }

    public static float minF(int a, float b)
    {

        return a < b ? a : b;
    }

    public static float minF(float a, int b)
    {

        return a < b ? a : b;
    }

    /**
     * Unchecked implementation to determine the larger of two Floats. Parameters should be known to be valid in advance.
     */
    public static float maxF(float a, float b)
    {

        return a > b ? a : b;
    }

    public static float maxF(int a, float b)
    {

        return a > b ? a : b;
    }

    public static float maxF(float a, int b)
    {

        return a > b ? a : b;
    }

    public static double maxAbs(double a, double b)
    {

        if (a < 0.0D)
        {
            a = -a;
        }
        if (b < 0.0D)
        {
            b = -b;
        }
        return a > b ? a : b;
    }

    public static int setBit(int mask, int bit, boolean value)
    {

        mask |= (value ? 1 : 0) << bit;
        return mask;
    }

    public static boolean isBitSet(int mask, int bit)
    {

        return (mask & 1 << bit) != 0;
    }

}

