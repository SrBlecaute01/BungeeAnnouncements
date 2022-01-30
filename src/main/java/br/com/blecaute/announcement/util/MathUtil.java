package br.com.blecaute.announcement.util;

import com.google.common.math.IntMath;

public class MathUtil {

    private MathUtil() {}

    public static int getGCD(int... values) {

        int lastGcd = values[0];
        for (int i = 1; i < values.length; i++) {
            lastGcd = IntMath.gcd(values[i], lastGcd);
        }

        return lastGcd;
    }


}