package com.example.version_java.ui.splash;

import com.example.version_java.base.ViewState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class SplashViewState implements ViewState {
    private SplashViewState() {
    }

    // $FF: synthetic method
    public SplashViewState(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    @Metadata(
            mv = {1, 6, 0},
            k = 1,
            d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
            d2 = {"Lcom/example/crimenotification/ui/splash/SplashViewState$RouteHome;", "Lcom/example/crimenotification/ui/splash/SplashViewState;", "()V", "CrimeNotification.app.main"}
    )
    public static final class RouteHome extends SplashViewState {
        @NotNull
        public static final SplashViewState.RouteHome INSTANCE;

        private RouteHome() {
            super((DefaultConstructorMarker)null);
        }

        static {
            SplashViewState.RouteHome var0 = new SplashViewState.RouteHome();
            INSTANCE = var0;
        }
    }

    @Metadata(
            mv = {1, 6, 0},
            k = 1,
            d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"},
            d2 = {"Lcom/example/crimenotification/ui/splash/SplashViewState$Error;", "Lcom/example/crimenotification/ui/splash/SplashViewState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "CrimeNotification.app.main"}
    )
    public static final class Error extends SplashViewState {
        @NotNull
        private final String message;

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        public Error(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        @NotNull
        public final String component1() {
            return this.message;
        }

        @NotNull
        public final SplashViewState.Error copy(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new SplashViewState.Error(message);
        }

        // $FF: synthetic method
        public static SplashViewState.Error copy$default(SplashViewState.Error var0, String var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.message;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "Error(message=" + this.message + ")";
        }

        public int hashCode() {
            String var10000 = this.message;
            return var10000 != null ? var10000.hashCode() : 0;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof SplashViewState.Error) {
                    SplashViewState.Error var2 = (SplashViewState.Error)var1;
                    if (Intrinsics.areEqual(this.message, var2.message)) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }
}
