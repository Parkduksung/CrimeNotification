package com.example.version_java.data.source.remote;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function1;

public interface CriminalRemoteDataSource {

    void getRemoteCriminals(
            @NotNull Function1 onSuccess,
            @NotNull Function1 onFailure
    );
}
