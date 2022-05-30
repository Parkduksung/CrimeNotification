package com.example.version_java.network.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class Address {
    @NotNull
    private final String address_name;
    @NotNull
    private final String b_code;
    @NotNull
    private final String h_code;
    @NotNull
    private final String main_address_no;
    @NotNull
    private final String mountain_yn;
    @NotNull
    private final String region_1depth_name;
    @NotNull
    private final String region_2depth_name;
    @NotNull
    private final String region_3depth_h_name;
    @NotNull
    private final String region_3depth_name;
    @NotNull
    private final String sub_address_no;
    @NotNull
    private final String x;
    @NotNull
    private final String y;

    @NotNull
    public final String getAddress_name() {
        return this.address_name;
    }

    @NotNull
    public final String getB_code() {
        return this.b_code;
    }

    @NotNull
    public final String getH_code() {
        return this.h_code;
    }

    @NotNull
    public final String getMain_address_no() {
        return this.main_address_no;
    }

    @NotNull
    public final String getMountain_yn() {
        return this.mountain_yn;
    }

    @NotNull
    public final String getRegion_1depth_name() {
        return this.region_1depth_name;
    }

    @NotNull
    public final String getRegion_2depth_name() {
        return this.region_2depth_name;
    }

    @NotNull
    public final String getRegion_3depth_h_name() {
        return this.region_3depth_h_name;
    }

    @NotNull
    public final String getRegion_3depth_name() {
        return this.region_3depth_name;
    }

    @NotNull
    public final String getSub_address_no() {
        return this.sub_address_no;
    }

    @NotNull
    public final String getX() {
        return this.x;
    }

    @NotNull
    public final String getY() {
        return this.y;
    }

    public Address(@NotNull String address_name, @NotNull String b_code, @NotNull String h_code, @NotNull String main_address_no, @NotNull String mountain_yn, @NotNull String region_1depth_name, @NotNull String region_2depth_name, @NotNull String region_3depth_h_name, @NotNull String region_3depth_name, @NotNull String sub_address_no, @NotNull String x, @NotNull String y) {
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(b_code, "b_code");
        Intrinsics.checkNotNullParameter(h_code, "h_code");
        Intrinsics.checkNotNullParameter(main_address_no, "main_address_no");
        Intrinsics.checkNotNullParameter(mountain_yn, "mountain_yn");
        Intrinsics.checkNotNullParameter(region_1depth_name, "region_1depth_name");
        Intrinsics.checkNotNullParameter(region_2depth_name, "region_2depth_name");
        Intrinsics.checkNotNullParameter(region_3depth_h_name, "region_3depth_h_name");
        Intrinsics.checkNotNullParameter(region_3depth_name, "region_3depth_name");
        Intrinsics.checkNotNullParameter(sub_address_no, "sub_address_no");
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        this.address_name = address_name;
        this.b_code = b_code;
        this.h_code = h_code;
        this.main_address_no = main_address_no;
        this.mountain_yn = mountain_yn;
        this.region_1depth_name = region_1depth_name;
        this.region_2depth_name = region_2depth_name;
        this.region_3depth_h_name = region_3depth_h_name;
        this.region_3depth_name = region_3depth_name;
        this.sub_address_no = sub_address_no;
        this.x = x;
        this.y = y;
    }

    @NotNull
    public final String component1() {
        return this.address_name;
    }

    @NotNull
    public final String component2() {
        return this.b_code;
    }

    @NotNull
    public final String component3() {
        return this.h_code;
    }

    @NotNull
    public final String component4() {
        return this.main_address_no;
    }

    @NotNull
    public final String component5() {
        return this.mountain_yn;
    }

    @NotNull
    public final String component6() {
        return this.region_1depth_name;
    }

    @NotNull
    public final String component7() {
        return this.region_2depth_name;
    }

    @NotNull
    public final String component8() {
        return this.region_3depth_h_name;
    }

    @NotNull
    public final String component9() {
        return this.region_3depth_name;
    }

    @NotNull
    public final String component10() {
        return this.sub_address_no;
    }

    @NotNull
    public final String component11() {
        return this.x;
    }

    @NotNull
    public final String component12() {
        return this.y;
    }

    @NotNull
    public final Address copy(@NotNull String address_name, @NotNull String b_code, @NotNull String h_code, @NotNull String main_address_no, @NotNull String mountain_yn, @NotNull String region_1depth_name, @NotNull String region_2depth_name, @NotNull String region_3depth_h_name, @NotNull String region_3depth_name, @NotNull String sub_address_no, @NotNull String x, @NotNull String y) {
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(b_code, "b_code");
        Intrinsics.checkNotNullParameter(h_code, "h_code");
        Intrinsics.checkNotNullParameter(main_address_no, "main_address_no");
        Intrinsics.checkNotNullParameter(mountain_yn, "mountain_yn");
        Intrinsics.checkNotNullParameter(region_1depth_name, "region_1depth_name");
        Intrinsics.checkNotNullParameter(region_2depth_name, "region_2depth_name");
        Intrinsics.checkNotNullParameter(region_3depth_h_name, "region_3depth_h_name");
        Intrinsics.checkNotNullParameter(region_3depth_name, "region_3depth_name");
        Intrinsics.checkNotNullParameter(sub_address_no, "sub_address_no");
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        return new Address(address_name, b_code, h_code, main_address_no, mountain_yn, region_1depth_name, region_2depth_name, region_3depth_h_name, region_3depth_name, sub_address_no, x, y);
    }

    // $FF: synthetic method
    public static Address copy$default(Address var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, int var13, Object var14) {
        if ((var13 & 1) != 0) {
            var1 = var0.address_name;
        }

        if ((var13 & 2) != 0) {
            var2 = var0.b_code;
        }

        if ((var13 & 4) != 0) {
            var3 = var0.h_code;
        }

        if ((var13 & 8) != 0) {
            var4 = var0.main_address_no;
        }

        if ((var13 & 16) != 0) {
            var5 = var0.mountain_yn;
        }

        if ((var13 & 32) != 0) {
            var6 = var0.region_1depth_name;
        }

        if ((var13 & 64) != 0) {
            var7 = var0.region_2depth_name;
        }

        if ((var13 & 128) != 0) {
            var8 = var0.region_3depth_h_name;
        }

        if ((var13 & 256) != 0) {
            var9 = var0.region_3depth_name;
        }

        if ((var13 & 512) != 0) {
            var10 = var0.sub_address_no;
        }

        if ((var13 & 1024) != 0) {
            var11 = var0.x;
        }

        if ((var13 & 2048) != 0) {
            var12 = var0.y;
        }

        return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
    }

    @NotNull
    public String toString() {
        return "Address(address_name=" + this.address_name + ", b_code=" + this.b_code + ", h_code=" + this.h_code + ", main_address_no=" + this.main_address_no + ", mountain_yn=" + this.mountain_yn + ", region_1depth_name=" + this.region_1depth_name + ", region_2depth_name=" + this.region_2depth_name + ", region_3depth_h_name=" + this.region_3depth_h_name + ", region_3depth_name=" + this.region_3depth_name + ", sub_address_no=" + this.sub_address_no + ", x=" + this.x + ", y=" + this.y + ")";
    }

    public int hashCode() {
        String var10000 = this.address_name;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.b_code;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.h_code;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.main_address_no;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.mountain_yn;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_1depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_2depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_3depth_h_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_3depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.sub_address_no;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.x;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.y;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (!(var1 instanceof Address)) {
                return false;
            }

            Address var2 = (Address) var1;
            if (!Intrinsics.areEqual(this.address_name, var2.address_name) || !Intrinsics.areEqual(this.b_code, var2.b_code) || !Intrinsics.areEqual(this.h_code, var2.h_code) || !Intrinsics.areEqual(this.main_address_no, var2.main_address_no) || !Intrinsics.areEqual(this.mountain_yn, var2.mountain_yn) || !Intrinsics.areEqual(this.region_1depth_name, var2.region_1depth_name) || !Intrinsics.areEqual(this.region_2depth_name, var2.region_2depth_name) || !Intrinsics.areEqual(this.region_3depth_h_name, var2.region_3depth_h_name) || !Intrinsics.areEqual(this.region_3depth_name, var2.region_3depth_name) || !Intrinsics.areEqual(this.sub_address_no, var2.sub_address_no) || !Intrinsics.areEqual(this.x, var2.x) || !Intrinsics.areEqual(this.y, var2.y)) {
                return false;
            }
        }

        return true;
    }
}
