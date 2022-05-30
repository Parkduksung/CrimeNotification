package com.example.version_java.ui.login;

import androidx.annotation.NonNull;

public class Person {
    @NonNull
    private String email;

    @NonNull
    private String password;

    public Person(
            @NonNull String email,
            @NonNull String password
    ) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

}
