package com.example.version_java.data.source.remote;

import androidx.annotation.NonNull;

import com.example.version_java.data.source.local.CrimianlLocalDataSource;
import com.example.version_java.network.api.SheetApi;
import com.example.version_java.network.response.CriminalResponse;

import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriminalRemoteDataSourceImpl implements CriminalRemoteDataSource {

    private final SheetApi sheetApi;


    @Override
    public void getRemoteCriminals(@NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        sheetApi.getSheetCriminals().enqueue(new Callback<List<CriminalResponse>>() {
            @Override
            public void onResponse(Call<List<CriminalResponse>> call, Response<List<CriminalResponse>> response) {
                if (response.body() != null) {
                    onSuccess.invoke(response.body());
                } else {
                    onFailure.invoke("GetRemoteCriminals Error");
                }
            }

            @Override
            public void onFailure(Call<List<CriminalResponse>> call, Throwable t) {
                onFailure.invoke(t.getMessage());
            }
        });
    }

    @Inject
    CriminalRemoteDataSourceImpl(
            SheetApi sheetApi
    ) {
        this.sheetApi = sheetApi;
    }
}
