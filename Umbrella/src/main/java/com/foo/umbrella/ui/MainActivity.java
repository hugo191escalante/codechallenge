package com.foo.umbrella.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.foo.umbrella.R;
import com.foo.umbrella.data.ApiServicesProvider;
import com.foo.umbrella.data.model.WeatherData;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiServicesProvider apiServicesProvider = new ApiServicesProvider(getApplication());
        apiServicesProvider.getWeatherService().forecastForZipObservable("30339")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Result<WeatherData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Result<WeatherData> weatherDataResult) {
                        if (!weatherDataResult.isError()) {
                            Log.d(TAG, "onNext: " + weatherDataResult.response().body());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }
}
