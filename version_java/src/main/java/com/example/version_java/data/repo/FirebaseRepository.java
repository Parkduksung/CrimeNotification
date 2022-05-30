package com.example.version_java.data.repo;

import com.google.firebase.auth.FirebaseAuth;

import kotlin.jvm.functions.Function1;

public interface FirebaseRepository {

    void login(String id, String pass, Function1 result);

    void register(String id, String pass, Function1 result);

    boolean logout();

    void delete(Function1 result);

    FirebaseAuth getFirebaseAuth();
}
