package com.example.version_java.data.model;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DistanceCriminal {
    @NotNull
    private String name;
    @NotNull
    private String address;
    private int distance;

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.name = var1;
    }

    @NotNull
    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.address = var1;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final void setDistance(int var1) {
        this.distance = var1;
    }

    public DistanceCriminal(@NotNull String name, @NotNull String address, int distance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        this.name = name;
        this.address = address;
        this.distance = distance;
    }

    // $FF: synthetic method
    public DistanceCriminal(String var1, String var2, int var3, int var4, DefaultConstructorMarker var5) {
        if ((var4 & 1) != 0) {
            var1 = "";
        }

        if ((var4 & 2) != 0) {
            var2 = "";
        }

        if ((var4 & 4) != 0) {
            var3 = 0;
        }
    }

    public DistanceCriminal() {
        this((String) null, (String) null, 0, 7, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.address;
    }

    public final int component3() {
        return this.distance;
    }

    @NotNull
    public final DistanceCriminal copy(@NotNull String name, @NotNull String address, int distance) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        return new DistanceCriminal(name, address, distance);
    }

    // $FF: synthetic method
    public static DistanceCriminal copy$default(DistanceCriminal var0, String var1, String var2, int var3, int var4, Object var5) {
        if ((var4 & 1) != 0) {
            var1 = var0.name;
        }

        if ((var4 & 2) != 0) {
            var2 = var0.address;
        }

        if ((var4 & 4) != 0) {
            var3 = var0.distance;
        }

        return var0.copy(var1, var2, var3);
    }

    @NotNull
    public String toString() {
        return "DistanceCriminal(name=" + this.name + ", address=" + this.address + ", distance=" + this.distance + ")";
    }

    public int hashCode() {
        String var10000 = this.name;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.address;
        return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Integer.hashCode(this.distance);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof DistanceCriminal) {
                DistanceCriminal var2 = (DistanceCriminal) var1;
                if (Intrinsics.areEqual(this.name, var2.name) && Intrinsics.areEqual(this.address, var2.address) && this.distance == var2.distance) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
