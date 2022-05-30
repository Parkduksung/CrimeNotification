package com.example.version_java.network.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class Meta {
    private final boolean is_end;
    private final int pageable_count;
    private final int total_count;

    public final boolean is_end() {
        return this.is_end;
    }

    public final int getPageable_count() {
        return this.pageable_count;
    }

    public final int getTotal_count() {
        return this.total_count;
    }

    public Meta(boolean is_end, int pageable_count, int total_count) {
        this.is_end = is_end;
        this.pageable_count = pageable_count;
        this.total_count = total_count;
    }

    public final boolean component1() {
        return this.is_end;
    }

    public final int component2() {
        return this.pageable_count;
    }

    public final int component3() {
        return this.total_count;
    }

    @NotNull
    public final Meta copy(boolean is_end, int pageable_count, int total_count) {
        return new Meta(is_end, pageable_count, total_count);
    }

    // $FF: synthetic method
    public static Meta copy$default(Meta var0, boolean var1, int var2, int var3, int var4, Object var5) {
        if ((var4 & 1) != 0) {
            var1 = var0.is_end;
        }

        if ((var4 & 2) != 0) {
            var2 = var0.pageable_count;
        }

        if ((var4 & 4) != 0) {
            var3 = var0.total_count;
        }

        return var0.copy(var1, var2, var3);
    }

    @NotNull
    public String toString() {
        return "Meta(is_end=" + this.is_end + ", pageable_count=" + this.pageable_count + ", total_count=" + this.total_count + ")";
    }

    public int hashCode() {
        byte var10000;

        if (this.is_end == true) {
            var10000 = 0;
        } else {
            var10000 = 1;
        }
        if (var10000 != 0) {
            var10000 = 1;
        }

        return (var10000 * 31 + Integer.hashCode(this.pageable_count)) * 31 + Integer.hashCode(this.total_count);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Meta) {
                Meta var2 = (Meta) var1;
                if (this.is_end == var2.is_end && this.pageable_count == var2.pageable_count && this.total_count == var2.total_count) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}