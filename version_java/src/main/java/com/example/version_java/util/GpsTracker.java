package com.example.version_java.util;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function1;


public final class GpsTracker {

    private final FusedLocationProviderClient fusedLocationClient;
    private final CancellationTokenSource cancellationTokenSource;
    private final Application application;

    @SuppressLint({"MissingPermission"})
    @NotNull
    public final void getLocation(Function1 callback) {
        try {

            Task<Location> locationTask = fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.getToken());

            locationTask.addOnSuccessListener(location -> {
                callback.invoke(location);
            }).addOnFailureListener(e -> {
                callback.invoke(e.getMessage());
            });
        } catch (Exception var3) {

        }
    }

    public final void onCancel() {
        this.cancellationTokenSource.cancel();
    }

    public GpsTracker(@NotNull Application application) {
        this.application = application;
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(application);
        this.cancellationTokenSource = new CancellationTokenSource();
    }
}
