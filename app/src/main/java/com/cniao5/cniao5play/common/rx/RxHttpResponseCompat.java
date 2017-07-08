package com.cniao5.cniao5play.common.rx;

import com.cniao5.cniao5play.bean.BaseBean;
import com.cniao5.cniao5play.common.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lgz on 7/8/17.
 */

public class RxHttpResponseCompat {

    public static <T> Observable.Transformer<BaseBean<T>, T> compatResult() {
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(final BaseBean<T> tBaseBean) {
                        if (tBaseBean.isSucceed()) {
                            return Observable.create(new Observable.OnSubscribe<T>() {
                                @Override
                                public void call(Subscriber<? super T> subscriber) {
                                    try {
                                        subscriber.onNext(tBaseBean.getData());
                                        subscriber.onCompleted();
                                    } catch (Exception e) {
                                        subscriber.onError(e);
                                    }
                                }
                            });
                        } else {
                            return Observable.error(
                                    new ApiException(tBaseBean.getStatus(), tBaseBean.getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
