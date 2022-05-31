package com.example.version_java.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import kotlin.jvm.internal.Intrinsics;


public final class DistanceManager {
    private static final double R = 6372800.0D;
    @NotNull
    public static final DistanceManager INSTANCE;

    /**
     * 두 좌표의 거리를 계산한다.
     *
     * @param lat1 위도1
     * @param lon1 경도1
     * @param lat2 위도2
     * @param lon2 경도2
     * @return 두 좌표의 거리(m)
     */
    public final int getDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double c = dLat / (double) 2;
        c = Math.sin(c);
        double var17 = 2.0D;
        double var10000 = Math.pow(c, var17);
        c = dLon / (double) 2;
        c = Math.sin(c);
        var17 = 2.0D;
        double var10001 = Math.pow(c, var17);
        c = Math.toRadians(lat1);
        var10001 *= Math.cos(c);
        c = Math.toRadians(lat2);
        double a = var10000 + var10001 * Math.cos(c);
        var10000 = (double) 2;
        var17 = Math.sqrt(a);
        c = var10000 * Math.asin(var17);
        return (int) (6372800.0D * c);
    }

    @NotNull
    public final String toStringDistance(int distance) {
        String var7;
        if (0 <= distance) {
            if (1000 >= distance) {
                var7 = "" + distance + 'M';
                return var7;
            }
        }

        StringBuilder var10000 = (new StringBuilder()).append("약 ");
        String var4 = "%.1f";
        Object[] var5 = new Object[]{(double) distance / (double) 1000};
        String var6 = String.format(var4, Arrays.copyOf(var5, var5.length));
        Intrinsics.checkNotNullExpressionValue(var6, "format(format, *args)");
        var7 = var10000.append(var6).append("KM").toString();
        return var7;
    }

    private DistanceManager() {
    }

    static {
        DistanceManager var0 = new DistanceManager();
        INSTANCE = var0;
    }
}
