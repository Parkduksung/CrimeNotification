package com.example.version_java.network.response;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;

public final class Document {
    @NotNull
    private final Address address;
    @NotNull
    private final String address_name;
    @NotNull
    private final String address_type;
    @NotNull
    private final RoadAddress road_address;
    @SerializedName("x")
    @NotNull
    private final String longitude;
    @SerializedName("y")
    @NotNull
    private final String latitude;

    @NotNull
    public final Address getAddress() {
        return this.address;
    }

    @NotNull
    public final String getAddress_name() {
        return this.address_name;
    }

    @NotNull
    public final String getAddress_type() {
        return this.address_type;
    }

    @NotNull
    public final RoadAddress getRoad_address() {
        return this.road_address;
    }

    @NotNull
    public final String getLongitude() {
        return this.longitude;
    }

    @NotNull
    public final String getLatitude() {
        return this.latitude;
    }

    public Document(@NotNull Address address, @NotNull String address_name, @NotNull String address_type, @NotNull RoadAddress road_address, @NotNull String longitude, @NotNull String latitude) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(address_type, "address_type");
        Intrinsics.checkNotNullParameter(road_address, "road_address");
        Intrinsics.checkNotNullParameter(longitude, "longitude");
        Intrinsics.checkNotNullParameter(latitude, "latitude");
        this.address = address;
        this.address_name = address_name;
        this.address_type = address_type;
        this.road_address = road_address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @NotNull
    public final Address component1() {
        return this.address;
    }

    @NotNull
    public final String component2() {
        return this.address_name;
    }

    @NotNull
    public final String component3() {
        return this.address_type;
    }

    @NotNull
    public final RoadAddress component4() {
        return this.road_address;
    }

    @NotNull
    public final String component5() {
        return this.longitude;
    }

    @NotNull
    public final String component6() {
        return this.latitude;
    }

    @NotNull
    public final Document copy(@NotNull Address address, @NotNull String address_name, @NotNull String address_type, @NotNull RoadAddress road_address, @NotNull String longitude, @NotNull String latitude) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(address_name, "address_name");
        Intrinsics.checkNotNullParameter(address_type, "address_type");
        Intrinsics.checkNotNullParameter(road_address, "road_address");
        Intrinsics.checkNotNullParameter(longitude, "longitude");
        Intrinsics.checkNotNullParameter(latitude, "latitude");
        return new Document(address, address_name, address_type, road_address, longitude, latitude);
    }

    // $FF: synthetic method
    public static Document copy$default(Document var0, Address var1, String var2, String var3, RoadAddress var4, String var5, String var6, int var7, Object var8) {
        if ((var7 & 1) != 0) {
            var1 = var0.address;
        }

        if ((var7 & 2) != 0) {
            var2 = var0.address_name;
        }

        if ((var7 & 4) != 0) {
            var3 = var0.address_type;
        }

        if ((var7 & 8) != 0) {
            var4 = var0.road_address;
        }

        if ((var7 & 16) != 0) {
            var5 = var0.longitude;
        }

        if ((var7 & 32) != 0) {
            var6 = var0.latitude;
        }

        return var0.copy(var1, var2, var3, var4, var5, var6);
    }

    @NotNull
    public String toString() {
        return "Document(address=" + this.address + ", address_name=" + this.address_name + ", address_type=" + this.address_type + ", road_address=" + this.road_address + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ")";
    }

    public int hashCode() {
        Address var10000 = this.address;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.address_name;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.address_type;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        RoadAddress var2 = this.road_address;
        var1 = (var1 + (var2 != null ? var2.hashCode() : 0)) * 31;
        var10001 = this.longitude;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.latitude;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Document) {
                Document var2 = (Document) var1;
                if (Intrinsics.areEqual(this.address, var2.address) && Intrinsics.areEqual(this.address_name, var2.address_name) && Intrinsics.areEqual(this.address_type, var2.address_type) && Intrinsics.areEqual(this.road_address, var2.road_address) && Intrinsics.areEqual(this.longitude, var2.longitude) && Intrinsics.areEqual(this.latitude, var2.latitude)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}