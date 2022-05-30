package com.example.version_java.network.response;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import kotlin.jvm.internal.Intrinsics;


public final class KakaoSearchResponse {
    @NotNull
    private final List<Document> documents;
    @NotNull
    private final Meta meta;

    @NotNull
    public final List<Document> getDocuments() {
        return this.documents;
    }

    @NotNull
    public final Meta getMeta() {
        return this.meta;
    }

    public KakaoSearchResponse(@NotNull List<Document> documents, @NotNull Meta meta) {
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(meta, "meta");
        this.documents = documents;
        this.meta = meta;
    }

    @NotNull
    public final List<Document> component1() {
        return this.documents;
    }

    @NotNull
    public final Meta component2() {
        return this.meta;
    }

    @NotNull
    public final KakaoSearchResponse copy(@NotNull List<Document> documents, @NotNull Meta meta) {
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(meta, "meta");
        return new KakaoSearchResponse(documents, meta);
    }

    // $FF: synthetic method
    public static KakaoSearchResponse copy$default(KakaoSearchResponse var0, List<Document> var1, Meta var2, int var3, Object var4) {
        if ((var3 & 1) != 0) {
            var1 = var0.documents;
        }

        if ((var3 & 2) != 0) {
            var2 = var0.meta;
        }

        return var0.copy(var1, var2);
    }

    @NotNull
    public String toString() {
        return "KakaoSearchResponse(documents=" + this.documents + ", meta=" + this.meta + ")";
    }

    public int hashCode() {
        List<Document> var10000 = this.documents;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        Meta var10001 = this.meta;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof KakaoSearchResponse) {
                KakaoSearchResponse var2 = (KakaoSearchResponse) var1;
                if (Intrinsics.areEqual(this.documents, var2.documents) && Intrinsics.areEqual(this.meta, var2.meta)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}