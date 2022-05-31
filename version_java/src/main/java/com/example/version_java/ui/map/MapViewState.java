package com.example.version_java.ui.map;


import com.example.version_java.base.ViewState;
import com.example.version_java.room.entity.CriminalEntity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class MapViewState implements ViewState {
    private MapViewState() {
    }

    // $FF: synthetic method
    public MapViewState(DefaultConstructorMarker $constructor_marker) {
        this();
    }


    public static final class SetZoomLevel extends MapViewState {
        private final int zoomLevel;

        public final int getZoomLevel() {
            return this.zoomLevel;
        }

        public SetZoomLevel(int zoomLevel) {
            super((DefaultConstructorMarker) null);
            this.zoomLevel = zoomLevel;
        }

        public final int component1() {
            return this.zoomLevel;
        }

        @NotNull
        public final MapViewState.SetZoomLevel copy(int zoomLevel) {
            return new MapViewState.SetZoomLevel(zoomLevel);
        }

        // $FF: synthetic method
        public static MapViewState.SetZoomLevel copy$default(MapViewState.SetZoomLevel var0, int var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.zoomLevel;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "SetZoomLevel(zoomLevel=" + this.zoomLevel + ")";
        }

        public int hashCode() {
            return Integer.hashCode(this.zoomLevel);
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.SetZoomLevel) {
                    MapViewState.SetZoomLevel var2 = (MapViewState.SetZoomLevel) var1;
                    if (this.zoomLevel == var2.zoomLevel) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class RenewCurrentLocation extends MapViewState {
        @NotNull
        private final MapPoint mapPoint;

        @NotNull
        public final MapPoint getMapPoint() {
            return this.mapPoint;
        }

        public RenewCurrentLocation(@NotNull MapPoint mapPoint) {
            Intrinsics.checkNotNullParameter(mapPoint, "mapPoint");
            this.mapPoint = mapPoint;
        }

        @NotNull
        public final MapPoint component1() {
            return this.mapPoint;
        }

        @NotNull
        public final MapViewState.RenewCurrentLocation copy(@NotNull MapPoint mapPoint) {
            Intrinsics.checkNotNullParameter(mapPoint, "mapPoint");
            return new MapViewState.RenewCurrentLocation(mapPoint);
        }

        // $FF: synthetic method
        public static MapViewState.RenewCurrentLocation copy$default(MapViewState.RenewCurrentLocation var0, MapPoint var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.mapPoint;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "RenewCurrentLocation(mapPoint=" + this.mapPoint + ")";
        }

        public int hashCode() {
            MapPoint var10000 = this.mapPoint;
            return var10000 != null ? var10000.hashCode() : 0;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.RenewCurrentLocation) {
                    MapViewState.RenewCurrentLocation var2 = (MapViewState.RenewCurrentLocation) var1;
                    if (Intrinsics.areEqual(this.mapPoint, var2.mapPoint)) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class SetCurrentLocation extends MapViewState {
        @NotNull
        private final MapPoint mapPoint;

        @NotNull
        public final MapPoint getMapPoint() {
            return this.mapPoint;
        }

        public SetCurrentLocation(@NotNull MapPoint mapPoint) {
            Intrinsics.checkNotNullParameter(mapPoint, "mapPoint");
            this.mapPoint = mapPoint;
        }

        @NotNull
        public final MapPoint component1() {
            return this.mapPoint;
        }

        @NotNull
        public final MapViewState.SetCurrentLocation copy(@NotNull MapPoint mapPoint) {
            Intrinsics.checkNotNullParameter(mapPoint, "mapPoint");
            return new MapViewState.SetCurrentLocation(mapPoint);
        }

        // $FF: synthetic method
        public static MapViewState.SetCurrentLocation copy$default(MapViewState.SetCurrentLocation var0, MapPoint var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.mapPoint;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "SetCurrentLocation(mapPoint=" + this.mapPoint + ")";
        }

        public int hashCode() {
            MapPoint var10000 = this.mapPoint;
            return var10000 != null ? var10000.hashCode() : 0;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.SetCurrentLocation) {
                    MapViewState.SetCurrentLocation var2 = (MapViewState.SetCurrentLocation) var1;
                    if (Intrinsics.areEqual(this.mapPoint, var2.mapPoint)) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class GetCriminalItems extends MapViewState {
        @NotNull
        private final ArrayList<MapPOIItem> items;

        @NotNull
        public final ArrayList<MapPOIItem> getItems() {
            return this.items;
        }

        public GetCriminalItems(@NotNull ArrayList<MapPOIItem> items) {
            Intrinsics.checkNotNullParameter(items, "items");
            this.items = items;
        }

        @NotNull
        public final ArrayList<MapPOIItem> component1() {
            return this.items;
        }

        @NotNull
        public final MapViewState.GetCriminalItems copy(@NotNull ArrayList<MapPOIItem> items) {
            Intrinsics.checkNotNullParameter(items, "items");
            return new MapViewState.GetCriminalItems(items);
        }

        // $FF: synthetic method
        public static MapViewState.GetCriminalItems copy$default(MapViewState.GetCriminalItems var0, ArrayList<MapPOIItem> var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.items;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "GetCriminalItems(items=" + Arrays.toString(new ArrayList[]{this.items}) + ")";
        }

        public int hashCode() {
            ArrayList<MapPOIItem> var10000 = this.items;
            return var10000 != null ? Arrays.hashCode(new ArrayList[]{var10000}) : 0;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.GetCriminalItems) {
                    MapViewState.GetCriminalItems var2 = (MapViewState.GetCriminalItems) var1;
                    if (Intrinsics.areEqual(this.items, var2.items)) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class GetSelectPOIItem extends MapViewState {
        @NotNull
        private final CriminalEntity item;
        @NotNull
        private final String distance;

        @NotNull
        public final CriminalEntity getItem() {
            return this.item;
        }

        @NotNull
        public final String getDistance() {
            return this.distance;
        }

        public GetSelectPOIItem(@NotNull CriminalEntity item, @NotNull String distance) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(distance, "distance");
            this.item = item;
            this.distance = distance;
        }

        @NotNull
        public final CriminalEntity component1() {
            return this.item;
        }

        @NotNull
        public final String component2() {
            return this.distance;
        }

        @NotNull
        public final MapViewState.GetSelectPOIItem copy(@NotNull CriminalEntity item, @NotNull String distance) {
            Intrinsics.checkNotNullParameter(item, "item");
            Intrinsics.checkNotNullParameter(distance, "distance");
            return new MapViewState.GetSelectPOIItem(item, distance);
        }

        // $FF: synthetic method
        public static MapViewState.GetSelectPOIItem copy$default(MapViewState.GetSelectPOIItem var0, CriminalEntity var1, String var2, int var3, Object var4) {
            if ((var3 & 1) != 0) {
                var1 = var0.item;
            }

            if ((var3 & 2) != 0) {
                var2 = var0.distance;
            }

            return var0.copy(var1, var2);
        }

        @NotNull
        public String toString() {
            return "GetSelectPOIItem(item=" + this.item + ", distance=" + this.distance + ")";
        }

        public int hashCode() {
            CriminalEntity var10000 = this.item;
            int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
            String var10001 = this.distance;
            return var1 + (var10001 != null ? var10001.hashCode() : 0);
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.GetSelectPOIItem) {
                    MapViewState.GetSelectPOIItem var2 = (MapViewState.GetSelectPOIItem) var1;
                    if (Intrinsics.areEqual(this.item, var2.item) && Intrinsics.areEqual(this.distance, var2.distance)) {
                        return true;
                    }
                }

                return false;
            } else {
                return true;
            }
        }
    }

    public static final class Error extends MapViewState {
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
        public final MapViewState.Error copy(@NotNull String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            return new MapViewState.Error(errorMessage);
        }

        // $FF: synthetic method
        public static MapViewState.Error copy$default(MapViewState.Error var0, String var1, int var2, Object var3) {
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
                if (var1 instanceof MapViewState.Error) {
                    MapViewState.Error var2 = (MapViewState.Error) var1;
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

    public static final class AroundCriminals extends MapViewState {
        @NotNull
        private final List list;

        @NotNull
        public final List getList() {
            return this.list;
        }

        public AroundCriminals(@NotNull List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            this.list = list;
        }

        @NotNull
        public final List component1() {
            return this.list;
        }

        @NotNull
        public final MapViewState.AroundCriminals copy(@NotNull List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            return new MapViewState.AroundCriminals(list);
        }

        // $FF: synthetic method
        public static MapViewState.AroundCriminals copy$default(MapViewState.AroundCriminals var0, List var1, int var2, Object var3) {
            if ((var2 & 1) != 0) {
                var1 = var0.list;
            }

            return var0.copy(var1);
        }

        @NotNull
        public String toString() {
            return "AroundCriminals(list=" + this.list + ")";
        }

        public int hashCode() {
            List var10000 = this.list;
            return var10000 != null ? var10000.hashCode() : 0;
        }

        public boolean equals(@Nullable Object var1) {
            if (this != var1) {
                if (var1 instanceof MapViewState.AroundCriminals) {
                    MapViewState.AroundCriminals var2 = (MapViewState.AroundCriminals) var1;
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

    public static final class ShowProgress extends MapViewState {
        @NotNull
        public static final MapViewState.ShowProgress INSTANCE;

        private ShowProgress() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.ShowProgress var0 = new MapViewState.ShowProgress();
            INSTANCE = var0;
        }
    }

    public static final class ShowUserPopupMenu extends MapViewState {
        @NotNull
        public static final MapViewState.ShowUserPopupMenu INSTANCE;

        private ShowUserPopupMenu() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.ShowUserPopupMenu var0 = new MapViewState.ShowUserPopupMenu();
            INSTANCE = var0;
        }
    }

    public static final class WithdrawUser extends MapViewState {
        @NotNull
        public static final MapViewState.WithdrawUser INSTANCE;

        private WithdrawUser() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.WithdrawUser var0 = new MapViewState.WithdrawUser();
            INSTANCE = var0;
        }
    }

    public static final class LogoutUser extends MapViewState {
        @NotNull
        public static final MapViewState.LogoutUser INSTANCE;

        private LogoutUser() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.LogoutUser var0 = new MapViewState.LogoutUser();
            INSTANCE = var0;
        }
    }

    public static final class HideProgress extends MapViewState {
        @NotNull
        public static final MapViewState.HideProgress INSTANCE;

        private HideProgress() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.HideProgress var0 = new MapViewState.HideProgress();
            INSTANCE = var0;
        }
    }

    public static final class CallPolice extends MapViewState {
        @NotNull
        public static final MapViewState.CallPolice INSTANCE;

        private CallPolice() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.CallPolice var0 = new MapViewState.CallPolice();
            INSTANCE = var0;
        }
    }

    public static final class RouteAroundCriminalList extends MapViewState {
        @NotNull
        public static final MapViewState.RouteAroundCriminalList INSTANCE;

        private RouteAroundCriminalList() {
            super((DefaultConstructorMarker) null);
        }

        static {
            MapViewState.RouteAroundCriminalList var0 = new MapViewState.RouteAroundCriminalList();
            INSTANCE = var0;
        }
    }
}
