package com.example.version_java.ui.splash;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.version_java.base.BaseViewModel;
import com.example.version_java.data.repo.CriminalRepository;
import com.example.version_java.data.repo.KakaoRepository;
import com.example.version_java.network.response.CriminalResponse;
import com.example.version_java.network.response.Document;
import com.example.version_java.network.response.KakaoSearchResponse;
import com.example.version_java.room.entity.CriminalEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlin.jvm.functions.Function1;

@HiltViewModel
public class SplashViewModel extends BaseViewModel {

    private final CriminalRepository criminalRepository;
    private final KakaoRepository kakaoRepository;


    /**
     * 현재 저장되어있는 범죄자 데이터가 있는지 체크
     * 있을경우, 앞의 변수 isRoute 를 true 로 바꾸게 해줌.
     * 없을경우 loadCriminals() 호출
     * 체크하는 로직이 실패할 경우 에러메세지 노출.
     */
    public void checkSaveCriminals() {
        new Thread(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                criminalRepository.getLocalCriminals(
                        onSuccess -> {
                            List<CriminalEntity> list = (List<CriminalEntity>) onSuccess;
                            if (list.isEmpty()) {
                                loadCriminals();
                            } else {
                                viewStateChanged(SplashViewState.RouteHome.INSTANCE);
                            }
                            return null;
                        },
                        onFailure -> {
                            viewStateChanged(new SplashViewState.Error((String) onFailure));
                            return null;
                        }
                );
            }
        }).start();
    }


    /**
     * 범죄자 데이터를 로딩
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadCriminals() {

        Log.d("결과", "loadCriminals start");
        /**
         * 엑셀파일 데이터를 읽어오는 로직 호출.
         */
        criminalRepository.getRemoteCriminals(o -> {
            List<CriminalResponse> criminalResponseList = (List<CriminalResponse>) o;

            Log.d("결과", "loadCriminals criminalResponseList : " + criminalResponseList.size());
            /**
             * 범죄자 리스트에 각 좌표값 리스트를 합쳐 하나의 리스트로 바꿈.
             */
            ArrayList<CriminalEntity> criminalEntities = new ArrayList<>();

            getLocationList(criminalResponseList, callback -> {
                ArrayList<Document> documents = (ArrayList<Document>) callback;

                Log.d("결과", "getLocationList list :" + documents.size());

                for (int i = 0; i < criminalResponseList.size(); i++) {
                    criminalEntities.add(
                            new CriminalEntity(
                                    i,
                                    criminalResponseList.get(i).getName(),
                                    criminalResponseList.get(i).getAddressReal(),
                                    Double.parseDouble(documents.get(i).getLatitude()),
                                    Double.parseDouble(documents.get(i).getLongitude())
                            )
                    );
                }

                Log.d("결과", "criminalEntities list :" + criminalEntities.size());
                /**
                 * 하나로 합친 리스트를 저장이 잘 됬는지 체크여부.
                 */
                criminalRepository.registerCriminalEntity(criminalEntities, o1 -> {
                    Boolean result = (Boolean) o1;
                    if (result) {
                        Log.d("결과", "registerCriminalEntity success ");
                        viewStateChanged(SplashViewState.RouteHome.INSTANCE);
                    } else {
                        Log.d("결과", "registerCriminalEntity fail ");
                        viewStateChanged(new SplashViewState.Error("저장을 실패하였습니다. 다시 시도해 주세요."));
                    }
                    return null;
                });
                return null;
            });
            return null;
        }, o -> {
            viewStateChanged(new SplashViewState.Error((String) o));
            return null;
        });
    }


    /**
     * 엑셀데이터의 주소의 좌표값을 가져와 리스트로 만드는 로직
     * KakaoApi 를 통해 좌표값을 가져와 리스트업.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getLocationList(List<CriminalResponse> list, Function1 callback) {

        Log.d("결과", "getLocationList start");
        AtomicInteger count = new AtomicInteger(0);
        ArrayList<Document> arrayList = new ArrayList<>();

        list.forEach(criminalResponse -> {
            kakaoRepository.getSearchList(criminalResponse.getAddressReal(), response -> {
                if (response instanceof KakaoSearchResponse) {
                    count.getAndIncrement();
                    KakaoSearchResponse kakaoSearchResponse = (KakaoSearchResponse) response;
                    List<Document> documents = kakaoSearchResponse.getDocuments();

                    if (!documents.isEmpty()) {
                        arrayList.add(documents.get(0));
                    }
                } else if (response instanceof String) {
                    count.getAndIncrement();
                }

                if (count.get() == list.size() && arrayList.size() == list.size()) {
                    Log.d("결과", "count.get() == arrayList.size() ");
                    callback.invoke(arrayList);
                }
                return null;
            });
        });

    }

    @Inject
    public SplashViewModel(
            @NotNull Application application,
            CriminalRepository criminalRepository,
            KakaoRepository kakaoRepository
    ) {
        super(application);
        this.criminalRepository = criminalRepository;
        this.kakaoRepository = kakaoRepository;
    }
}