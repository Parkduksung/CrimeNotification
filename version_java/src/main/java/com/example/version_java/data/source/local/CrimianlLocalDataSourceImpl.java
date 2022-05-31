package com.example.version_java.data.source.local;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.version_java.room.dao.CriminalDao;
import com.example.version_java.room.entity.CriminalEntity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import kotlin.jvm.functions.Function1;

public class CrimianlLocalDataSourceImpl implements CrimianlLocalDataSource {


    private final CriminalDao criminalDao;

    @SuppressLint("NewApi")
    @Override
    public void getLocalCriminals(@NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        try {
            onSuccess.invoke(criminalDao.getAll());
        } catch (Exception e) {
            onFailure.invoke("Error getAllSSGEntity!");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void registerCriminalEntity(ArrayList<CriminalEntity> entityList, @NonNull Function1 result) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Boolean isRegisterAll = registerAll(entityList);
                    result.invoke(isRegisterAll);
                }catch (Exception e){
                    result.invoke(false);
                }

            }
        }).start();
    }


    @Override
    public void getCriminalEntity(@NonNull String name, @NonNull Function1 onSuccess, @NonNull Function1 onFailure) {
        try {
            onSuccess.invoke(criminalDao.getCriminalEntity(name));
        } catch (Exception e) {
            onFailure.invoke("GetCriminalEntity Fail");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Boolean registerAll(ArrayList<CriminalEntity> list) {
        AtomicReference<Boolean> isAllSave = new AtomicReference<>(true);
        for (CriminalEntity criminalEntity : list) {
            isAllSave.set(isAllSave.get() && (criminalDao.registerCriminalEntity(criminalEntity) > 0L));
        }
        return isAllSave.get();
    }

    @Inject
    CrimianlLocalDataSourceImpl(
            CriminalDao criminalDao
    ) {
        this.criminalDao = criminalDao;
    }
}
