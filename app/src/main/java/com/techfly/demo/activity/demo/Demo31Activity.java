package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.util.LogsUtil;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Demo31Activity extends BaseActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo31", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initObserver();

    }

    private void initObserver(){

        //方法一
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                LogsUtil.normal("onSubscribe="+d);
            }

            @Override
            public void onNext(Object value) {
                LogsUtil.normal("value="+value);
            }

            @Override
            public void onError(Throwable e) {
                LogsUtil.normal("e="+e);
            }

            @Override
            public void onComplete() {
                LogsUtil.normal("onComplete");
            }
        };

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

                LogsUtil.normal("subscribe");

                e.onNext("aaa");

                e.onNext("bbb");

                e.onNext("ccc");

                e.onComplete();
            }
        });
        observable.subscribe(observer);

        //方法二
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {

            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Drawable value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        /*Schedulers.newThread();
        Schedulers.io();
        Schedulers.shutdown();
        Schedulers.computation();*/

        Observable.just(1,2,3,4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        LogsUtil.normal("value="+value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        LogsUtil.normal("onComplete");
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

    }
}
