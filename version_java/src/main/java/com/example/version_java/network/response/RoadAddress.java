package com.example.version_java.network.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class RoadAddress {
    @NotNull
    private final String address_name;
    @NotNull
    private final String building_name;
    @NotNull
    private final String main_building_no;
    @NotNull
    private final String region_1depth_name;
    @NotNull
    private final String region_2depth_name;
    @NotNull
    private final String region_3depth_name;
    @NotNull
    private final String road_name;
    @NotNull
    private final String sub_building_no;
    @NotNull
    private final String underground_yn;
    @NotNull
    private final String x;
    @NotNull
    private final String y;
    @NotNull
    private final String zone_no;

    @NotNull
    public final String getAddress_name() {
        return this.address_name;
    }

    @NotNull
    public final String getBuilding_name() {
        return this.building_name;
    }

    @NotNull
    public final String getMain_building_no() {
        return this.main_building_no;
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
    public final String getRegion_3depth_name() {
        return this.region_3depth_name;
    }

    @NotNull
    public final String getRoad_name() {
        return this.road_name;
    }

    @NotNull
    public final String getSub_building_no() {
        return this.sub_building_no;
    }

    @NotNull
    public final String getUnderground_yn() {
        return this.underground_yn;
    }

    @NotNull
    public final String getX() {
        return this.x;
    }

    @NotNull
    public final String getY() {
        return this.y;
    }

    @NotNull
    public final String getZone_no() {
        return this.zone_no;
    }

    public RoadAddress(@NotNull String address_name, @NotNull String building_name, @NotNull String main_building_no, @NotNull String region_1depth_name, @NotNull String region_2depth_name, @NotNull String region_3depth_name, @NotNull String road_name, @NotNull String sub_building_no, @NotNull String underground_yn, @NotNull String x, @NotNull String y, @NotNull String zone_no) {
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(building_name, "building_name");
        Intrinsics.checkNotNullParameter(main_building_no, "main_building_no");
        Intrinsics.checkNotNullParameter(region_1depth_name, "region_1depth_name");
        Intrinsics.checkNotNullParameter(region_2depth_name, "region_2depth_name");
        Intrinsics.checkNotNullParameter(region_3depth_name, "region_3depth_name");
        Intrinsics.checkNotNullParameter(road_name, "road_name");
        Intrinsics.checkNotNullParameter(sub_building_no, "sub_building_no");
        Intrinsics.checkNotNullParameter(underground_yn, "underground_yn");
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        Intrinsics.checkNotNullParameter(zone_no, "zone_no");
        this.address_name = address_name;
        this.building_name = building_name;
        this.main_building_no = main_building_no;
        this.region_1depth_name = region_1depth_name;
        this.region_2depth_name = region_2depth_name;
        this.region_3depth_name = region_3depth_name;
        this.road_name = road_name;
        this.sub_building_no = sub_building_no;
        this.underground_yn = underground_yn;
        this.x = x;
        this.y = y;
        this.zone_no = zone_no;
    }

    @NotNull
    public final String component1() {
        return this.address_name;
    }

    @NotNull
    public final String component2() {
        return this.building_name;
    }

    @NotNull
    public final String component3() {
        return this.main_building_no;
    }

    @NotNull
    public final String component4() {
        return this.region_1depth_name;
    }

    @NotNull
    public final String component5() {
        return this.region_2depth_name;
    }

    @NotNull
    public final String component6() {
        return this.region_3depth_name;
    }

    @NotNull
    public final String component7() {
        return this.road_name;
    }

    @NotNull
    public final String component8() {
        return this.sub_building_no;
    }

    @NotNull
    public final String component9() {
        return this.underground_yn;
    }

    @NotNull
    public final String component10() {
        return this.x;
    }

    @NotNull
    public final String component11() {
        return this.y;
    }

    @NotNull
    public final String component12() {
        return this.zone_no;
    }

    @NotNull
    public final RoadAddress copy(@NotNull String address_name, @NotNull String building_name, @NotNull String main_building_no, @NotNull String region_1depth_name, @NotNull String region_2depth_name, @NotNull String region_3depth_name, @NotNull String road_name, @NotNull String sub_building_no, @NotNull String underground_yn, @NotNull String x, @NotNull String y, @NotNull String zone_no) {
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(building_name, "building_name");
        Intrinsics.checkNotNullParameter(main_building_no, "main_building_no");
        Intrinsics.checkNotNullParameter(region_1depth_name, "region_1depth_name");
        Intrinsics.checkNotNullParameter(region_2depth_name, "region_2depth_name");
        Intrinsics.checkNotNullParameter(region_3depth_name, "region_3depth_name");
        Intrinsics.checkNotNullParameter(road_name, "road_name");
        Intrinsics.checkNotNullParameter(sub_building_no, "sub_building_no");
        Intrinsics.checkNotNullParameter(underground_yn, "underground_yn");
        Intrinsics.checkNotNullParameter(x, "x");
        Intrinsics.checkNotNullParameter(y, "y");
        Intrinsics.checkNotNullParameter(zone_no, "zone_no");
        return new RoadAddress(address_name, building_name, main_building_no, region_1depth_name, region_2depth_name, region_3depth_name, road_name, sub_building_no, underground_yn, x, y, zone_no);
    }

    // $FF: synthetic method
    public static RoadAddress copy$default(RoadAddress var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, int var13, Object var14) {
        if ((var13 & 1) != 0) {
            var1 = var0.address_name;
        }

        if ((var13 & 2) != 0) {
            var2 = var0.building_name;
        }

        if ((var13 & 4) != 0) {
            var3 = var0.main_building_no;
        }

        if ((var13 & 8) != 0) {
            var4 = var0.region_1depth_name;
        }

        if ((var13 & 16) != 0) {
            var5 = var0.region_2depth_name;
        }

        if ((var13 & 32) != 0) {
            var6 = var0.region_3depth_name;
        }

        if ((var13 & 64) != 0) {
            var7 = var0.road_name;
        }

        if ((var13 & 128) != 0) {
            var8 = var0.sub_building_no;
        }

        if ((var13 & 256) != 0) {
            var9 = var0.underground_yn;
        }

        if ((var13 & 512) != 0) {
            var10 = var0.x;
        }

        if ((var13 & 1024) != 0) {
            var11 = var0.y;
        }

        if ((var13 & 2048) != 0) {
            var12 = var0.zone_no;
        }

        return var0.copy(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
    }

    @NotNull
    public String toString() {
        return "RoadAddress(address_name=" + this.address_name + ", building_name=" + this.building_name + ", main_building_no=" + this.main_building_no + ", region_1depth_name=" + this.region_1depth_name + ", region_2depth_name=" + this.region_2depth_name + ", region_3depth_name=" + this.region_3depth_name + ", road_name=" + this.road_name + ", sub_building_no=" + this.sub_building_no + ", underground_yn=" + this.underground_yn + ", x=" + this.x + ", y=" + this.y + ", zone_no=" + this.zone_no + ")";
    }

    public int hashCode() {
        String var10000 = this.address_name;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.building_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.main_building_no;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_1depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_2depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.region_3depth_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.road_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.sub_building_no;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.underground_yn;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.x;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.y;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.zone_no;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (!(var1 instanceof RoadAddress)) {
                return false;
            }

            RoadAddress var2 = (RoadAddress) var1;
            if (!Intrinsics.areEqual(this.address_name, var2.address_name) || !Intrinsics.areEqual(this.building_name, var2.building_name) || !Intrinsics.areEqual(this.main_building_no, var2.main_building_no) || !Intrinsics.areEqual(this.region_1depth_name, var2.region_1depth_name) || !Intrinsics.areEqual(this.region_2depth_name, var2.region_2depth_name) || !Intrinsics.areEqual(this.region_3depth_name, var2.region_3depth_name) || !Intrinsics.areEqual(this.road_name, var2.road_name) || !Intrinsics.areEqual(this.sub_building_no, var2.sub_building_no) || !Intrinsics.areEqual(this.underground_yn, var2.underground_yn) || !Intrinsics.areEqual(this.x, var2.x) || !Intrinsics.areEqual(this.y, var2.y) || !Intrinsics.areEqual(this.zone_no, var2.zone_no)) {
                return false;
            }
        }

        return true;
    }
}