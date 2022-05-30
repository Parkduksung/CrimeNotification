package com.example.version_java.network.response;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CriminalResponse {
    @NotNull
    private String name;
    @NotNull
    private String addressResident;
    @NotNull
    private String addressReal;

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.name = var1;
    }

    @NotNull
    public final String getAddressResident() {
        return this.addressResident;
    }

    public final void setAddressResident(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.addressResident = var1;
    }

    @NotNull
    public final String getAddressReal() {
        return this.addressReal;
    }

    public final void setAddressReal(@NotNull String var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.addressReal = var1;
    }

    public CriminalResponse(@NotNull String name, @NotNull String addressResident, @NotNull String addressReal) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(addressResident, "addressResident");
        Intrinsics.checkNotNullParameter(addressReal, "addressReal");
        this.name = name;
        this.addressResident = addressResident;
        this.addressReal = addressReal;
    }

    // $FF: synthetic method
    public CriminalResponse(String var1, String var2, String var3, int var4, DefaultConstructorMarker var5) {
        if ((var4 & 1) != 0) {
            var1 = "";
        }

        if ((var4 & 2) != 0) {
            var2 = "";
        }

        if ((var4 & 4) != 0) {
            var3 = "";
        }
    }

    public CriminalResponse() {
        this((String) null, (String) null, (String) null, 7, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.addressResident;
    }

    @NotNull
    public final String component3() {
        return this.addressReal;
    }

    @NotNull
    public final CriminalResponse copy(@NotNull String name, @NotNull String addressResident, @NotNull String addressReal) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(addressResident, "addressResident");
        Intrinsics.checkNotNullParameter(addressReal, "addressReal");
        return new CriminalResponse(name, addressResident, addressReal);
    }

    // $FF: synthetic method
    public static CriminalResponse copy$default(CriminalResponse var0, String var1, String var2, String var3, int var4, Object var5) {
        if ((var4 & 1) != 0) {
            var1 = var0.name;
        }

        if ((var4 & 2) != 0) {
            var2 = var0.addressResident;
        }

        if ((var4 & 4) != 0) {
            var3 = var0.addressReal;
        }

        return var0.copy(var1, var2, var3);
    }

    @NotNull
    public String toString() {
        return "CriminalResponse(name=" + this.name + ", addressResident=" + this.addressResident + ", addressReal=" + this.addressReal + ")";
    }

    public int hashCode() {
        String var10000 = this.name;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        String var10001 = this.addressResident;
        var1 = (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31;
        var10001 = this.addressReal;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof CriminalResponse) {
                CriminalResponse var2 = (CriminalResponse) var1;
                if (Intrinsics.areEqual(this.name, var2.name) && Intrinsics.areEqual(this.addressResident, var2.addressResident) && Intrinsics.areEqual(this.addressReal, var2.addressReal)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
