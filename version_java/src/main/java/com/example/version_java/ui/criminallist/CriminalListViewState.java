package com.example.version_java.ui.criminallist;

import com.example.version_java.base.ViewState;
import com.example.version_java.data.model.DistanceCriminal;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class CriminalListViewState implements ViewState {
   private CriminalListViewState() {
   }

   // $FF: synthetic method
   public CriminalListViewState(DefaultConstructorMarker $constructor_marker) {
      this();
   }

   public static final class ShowProgress extends CriminalListViewState {
      @NotNull
      public static final CriminalListViewState.ShowProgress INSTANCE;

      private ShowProgress() {
         super((DefaultConstructorMarker)null);
      }

      static {
         CriminalListViewState.ShowProgress var0 = new CriminalListViewState.ShowProgress();
         INSTANCE = var0;
      }
   }

   public static final class HideProgress extends CriminalListViewState {
      @NotNull
      public static final CriminalListViewState.HideProgress INSTANCE;

      private HideProgress() {
         super((DefaultConstructorMarker)null);
      }

      static {
         CriminalListViewState.HideProgress var0 = new CriminalListViewState.HideProgress();
         INSTANCE = var0;
      }
   }

   public static final class EmptyCriminalList extends CriminalListViewState {
      @NotNull
      public static final CriminalListViewState.EmptyCriminalList INSTANCE;

      private EmptyCriminalList() {
         super((DefaultConstructorMarker)null);
      }

      static {
         CriminalListViewState.EmptyCriminalList var0 = new CriminalListViewState.EmptyCriminalList();
         INSTANCE = var0;
      }
   }

   public static final class RenewCriminalList extends CriminalListViewState {
      @NotNull
      private final List<DistanceCriminal> list;

      @NotNull
      public final List<DistanceCriminal> getList() {
         return this.list;
      }

      public RenewCriminalList(@NotNull List<DistanceCriminal> list) {
         Intrinsics.checkNotNullParameter(list, "list");
         this.list = list;
      }

      @NotNull
      public final List<DistanceCriminal> component1() {
         return this.list;
      }

      @NotNull
      public final CriminalListViewState.RenewCriminalList copy(@NotNull List<DistanceCriminal> list) {
         Intrinsics.checkNotNullParameter(list, "list");
         return new CriminalListViewState.RenewCriminalList(list);
      }

      // $FF: synthetic method
      public static CriminalListViewState.RenewCriminalList copy$default(CriminalListViewState.RenewCriminalList var0, List<DistanceCriminal> var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.list;
         }

         return var0.copy(var1);
      }

      @NotNull
      public String toString() {
         return "RenewCriminalList(list=" + this.list + ")";
      }

      public int hashCode() {
         List<DistanceCriminal> var10000 = this.list;
         return var10000 != null ? var10000.hashCode() : 0;
      }

      public boolean equals(@Nullable Object var1) {
         if (this != var1) {
            if (var1 instanceof CriminalListViewState.RenewCriminalList) {
               CriminalListViewState.RenewCriminalList var2 = (CriminalListViewState.RenewCriminalList)var1;
               if (Intrinsics.areEqual(this.list, var2.list)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }
   }

   public static final class Error extends CriminalListViewState {
      @NotNull
      private final String errorMessage;

      @NotNull
      public final String getErrorMessage() {
         return this.errorMessage;
      }

      public Error(@NotNull String errorMessage) {
         Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
         this.errorMessage = errorMessage;
      }

      @NotNull
      public final String component1() {
         return this.errorMessage;
      }

      @NotNull
      public final CriminalListViewState.Error copy(@NotNull String errorMessage) {
         Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
         return new CriminalListViewState.Error(errorMessage);
      }

      // $FF: synthetic method
      public static CriminalListViewState.Error copy$default(CriminalListViewState.Error var0, String var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.errorMessage;
         }

         return var0.copy(var1);
      }

      @NotNull
      public String toString() {
         return "Error(errorMessage=" + this.errorMessage + ")";
      }

      public int hashCode() {
         String var10000 = this.errorMessage;
         return var10000 != null ? var10000.hashCode() : 0;
      }

      public boolean equals(@Nullable Object var1) {
         if (this != var1) {
            if (var1 instanceof CriminalListViewState.Error) {
               CriminalListViewState.Error var2 = (CriminalListViewState.Error)var1;
               if (Intrinsics.areEqual(this.errorMessage, var2.errorMessage)) {
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