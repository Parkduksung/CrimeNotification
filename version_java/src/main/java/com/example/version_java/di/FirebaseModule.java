package com.example.version_java.di;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {

    @Singleton
    @Provides
    public static FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
}
