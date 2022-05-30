package com.example.version_java.data.repo;

import com.example.version_java.data.source.remote.FirebaseRemoteDataSource;
import com.example.version_java.data.source.remote.KakaoRemoteDataSource;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;

public class FirebaseRepositoryImpl implements FirebaseRepository {

    private final FirebaseRemoteDataSource firebaseRemoteDataSource;


    @Override
    public void login(String id, String pass, Function1 result) {
        firebaseRemoteDataSource.login(id, pass, result);
    }

    @Override
    public void register(String id, String pass, Function1 result) {
        firebaseRemoteDataSource.register(id, pass, result);
    }

    @Override
    public boolean logout() {
        return firebaseRemoteDataSource.logout();
    }

    @Override
    public void delete(Function1 result) {
        firebaseRemoteDataSource.delete(result);
    }

    @Override
    public FirebaseAuth getFirebaseAuth() {
        return firebaseRemoteDataSource.getFirebaseAuth();
    }

    @Inject
    FirebaseRepositoryImpl(
            FirebaseRemoteDataSource firebaseRemoteDataSource
    ) {
        this.firebaseRemoteDataSource = firebaseRemoteDataSource;
    }
}
