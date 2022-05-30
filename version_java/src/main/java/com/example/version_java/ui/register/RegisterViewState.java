package com.example.version_java.ui.register;

import com.example.version_java.base.ViewState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class RegisterViewState implements ViewState {
   private RegisterViewState() {
   }

   // $FF: synthetic method
   public RegisterViewState(DefaultConstructorMarker $constructor_marker) {
      this();
   }


   public static final class RouteHome extends RegisterViewState {
      @NotNull
      public static final RegisterViewState.RouteHome INSTANCE;

      private RouteHome() {
         super((DefaultConstructorMarker)null);
      }

      static {
         RegisterViewState.RouteHome var0 = new RegisterViewState.RouteHome();
         INSTANCE = var0;
      }
   }


   public static final class Error extends RegisterViewState {
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
      public final RegisterViewState.Error copy(@NotNull String message) {
         Intrinsics.checkNotNullParameter(message, "message");
         return new RegisterViewState.Error(message);
      }

      // $FF: synthetic method
      public static RegisterViewState.Error copy$default(RegisterViewState.Error var0, String var1, int var2, Object var3) {
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
            if (var1 instanceof RegisterViewState.Error) {
               RegisterViewState.Error var2 = (RegisterViewState.Error)var1;
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

   public static final class EnableInput extends RegisterViewState {
      private final boolean isEnable;

      public final boolean isEnable() {
         return this.isEnable;
      }

      public EnableInput(boolean isEnable) {
         super((DefaultConstructorMarker)null);
         this.isEnable = isEnable;
      }

      public final boolean component1() {
         return this.isEnable;
      }

      @NotNull
      public final RegisterViewState.EnableInput copy(boolean isEnable) {
         return new RegisterViewState.EnableInput(isEnable);
      }

      // $FF: synthetic method
      public static RegisterViewState.EnableInput copy$default(RegisterViewState.EnableInput var0, boolean var1, int var2, Object var3) {
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
            if (var1 instanceof RegisterViewState.EnableInput) {
               RegisterViewState.EnableInput var2 = (RegisterViewState.EnableInput)var1;
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
   public static final class ShowProgress extends RegisterViewState {
      @NotNull
      public static final RegisterViewState.ShowProgress INSTANCE;

      private ShowProgress() {
         super((DefaultConstructorMarker)null);
      }

      static {
         RegisterViewState.ShowProgress var0 = new RegisterViewState.ShowProgress();
         INSTANCE = var0;
      }
   }
   public static final class HideProgress extends RegisterViewState {
      @NotNull
      public static final RegisterViewState.HideProgress INSTANCE;

      private HideProgress() {
         super((DefaultConstructorMarker)null);
      }

      static {
         RegisterViewState.HideProgress var0 = new RegisterViewState.HideProgress();
         INSTANCE = var0;
      }
   }
}
