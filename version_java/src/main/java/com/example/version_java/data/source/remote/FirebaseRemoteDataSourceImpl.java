package com.example.version_java.data.source.remote;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;

public class FirebaseRemoteDataSourceImpl implements FirebaseRemoteDataSource {

    private final FirebaseAuth firebaseAuth;

    @Override
    public void login(String id, String pass, Function1 result) {
        firebaseAuth.signInWithEmailAndPassword(id, pass)
                .addOnSuccessListener(authResult -> {
                    result.invoke(true);
                })
                .addOnFailureListener(authResult -> {
                    result.invoke(false);
                });
    }

    @Override
    public void register(String id, String pass, Function1 result) {
        firebaseAuth.createUserWithEmailAndPassword(id, pass)
                .addOnSuccessListener(authResult -> {
                    result.invoke(true);
                })
                .addOnFailureListener(authResult -> {
                    result.invoke(false);
                });
    }

    @Override
    public boolean logout() {
        firebaseAuth.signOut();
        return true;
    }

    @Override
    public void delete(Function1 result) {
        firebaseAuth.getCurrentUser().delete().addOnSuccessListener(authResult -> {
                    result.invoke(true);
                })
                .addOnFailureListener(authResult -> {
                    result.invoke(false);
                });
    }

    @Override
    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    @Inject
    FirebaseRemoteDataSourceImpl(
            FirebaseAuth firebaseAuth
    ) {
        this.firebaseAuth = firebaseAuth;
    }
}
