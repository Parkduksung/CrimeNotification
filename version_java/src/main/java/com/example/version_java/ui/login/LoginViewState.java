package com.example.version_java.ui.login;

import com.example.version_java.base.ViewState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class LoginViewState implements ViewState {
    private LoginViewState() {
    }

    // $FF: synthetic method
    public LoginViewState(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    public static final class RouteRegister extends LoginViewState {
        @NotNull
        public static final LoginViewState.RouteRegister INSTANCE;

        private RouteRegister() {
            super((DefaultConstructorMarker) null);
        }

        static {
            LoginViewState.RouteRegister var0 = new LoginViewState.RouteRegister();
            INSTANCE = var0;
        }
    }

    public static final class RouteHome extends LoginViewState {
        @NotNull
        public static final LoginViewState.RouteHome INSTANCE;

        private RouteHome() {
            super((DefaultConstructorMarker) null);
        }

        static {
            LoginViewState.RouteHome var0 = new LoginViewState.RouteHome();
            INSTANCE = var0;
        }
    }

    public static final class Error extends LoginViewState {
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
        public final LoginViewState.Error copy(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new LoginViewState.Error(message);
        }

        // $FF: synthetic method
        public static LoginViewState.Error copy$default(LoginViewState.Error var0, String var1, int var2, Object var3) {
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
                if (var1 instanceof LoginViewState.Error) {
                    LoginViewState.Error var2 = (LoginViewState.Error) var1;
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

    public static final class EnableInput extends LoginViewState {
        private final boolean isEnable;

        public final boolean isEnable() {
            return this.isEnable;
        }

        public EnableInput(boolean isEnable) {
            super((DefaultConstructorMarker) null);
            this.isEnable = isEnable;
        }

        public final boolean component1() {
            return this.isEnable;
        }

        @NotNull
        public final LoginViewState.EnableInput copy(boolean isEnable) {
            return new LoginViewState.EnableInput(isEnable);
        }

        // $FF: synthetic method
        public static LoginViewState.EnableInput copy$default(LoginViewState.EnableInput var0, boolean var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.isEnable;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "EnableInput(isEnable=" + this.isEnable + ")";
        }

        public int hashCode() {
            byte var10000;
            if (this.isEnable) {
                var10000 = 1;
            } else {
                var10000 = 0;
            }
            return var10000;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof LoginViewState.EnableInput) {
                    LoginViewState.EnableInput var2 = (LoginViewState.EnableInput) var1;
                    if (this.isEnable == var2.isEnable) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class ShowProgress extends LoginViewState {
        @NotNull
        public static final LoginViewState.ShowProgress INSTANCE;

        private ShowProgress() {
            super((DefaultConstructorMarker) null);
        }

        static {
            LoginViewState.ShowProgress var0 = new LoginViewState.ShowProgress();
            INSTANCE = var0;
        }
    }

    public static final class HideProgress extends LoginViewState {
        @NotNull
        public static final LoginViewState.HideProgress INSTANCE;

        private HideProgress() {
            super((DefaultConstructorMarker) null);
        }

        static {
            LoginViewState.HideProgress var0 = new LoginViewState.HideProgress();
            INSTANCE = var0;
        }
    }
}
