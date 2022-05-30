package com.example.version_java.data.repo;

import com.example.version_java.room.entity.CriminalEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function1;

public interface CriminalRepository {

    void getRemoteCriminals(
            @NotNull Function1 onSuccess,
            @NotNull Function1 onFailure
    );

    void getLocalCriminals(
            @NotNull Function1 onSuccess,
            @NotNull Function1 onFailure
    );

    void registerCriminalEntity(
            ArrayList<CriminalEntity> entityList,
            @NotNull Function1 result
    );

    void getCriminalEntity(
            @NotNull String name,
            @NotNull Function1 onSuccess,
            @NotNull Function1 onFailure
    );
}
