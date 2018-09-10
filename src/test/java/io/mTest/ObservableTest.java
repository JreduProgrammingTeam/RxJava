package io.mTest;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.TrampolineScheduler;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import sun.plugin2.util.SystemUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author hunbing
 * @date 15:38 2018/4/9
 * @description
 **/
public class ObservableTest {

    @Test
    public void ObservableCreateTest(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                // 这里是观察者发出声音的地方
                emitter.onNext("就是一段话");
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                OutputUtil.printLn("绑定");
            }

            @Override
            public void onNext(String s) {
                OutputUtil.printLn(s);
            }

            @Override
            public void onError(Throwable e) {
                OutputUtil.printLn("错误");
            }

            @Override
            public void onComplete() {
                OutputUtil.printLn("完成");
            }
        });
    }

    @Test
    public void ObservableArrayTest(){
        String[] array = {"a"};
        Observable.fromArray(array)
                .subscribe(new Observer<String>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        this.disposable = d;
                        OutputUtil.printLn(d.isDisposed()+"");
                        OutputUtil.printLn("绑定");
                    }

                    @Override
                    public void onNext(String s) {
                        OutputUtil.printLn(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        OutputUtil.printLn("错误");
                    }

                    @Override
                    public void onComplete() {
                        OutputUtil.printLn("完成");
                    }
                });
    }

    @Test
    public void observableJustTest(){
        Observable.just(1,2,3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        OutputUtil.printLn("绑定");
                    }

                    @Override
                    public void onNext(Integer s) {
                        OutputUtil.printLn(s+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        OutputUtil.printLn("错误");
                    }

                    @Override
                    public void onComplete() {
                        OutputUtil.printLn("完成");
                    }
                });
    }

    @Test
    public void observableIntervalTest(){
        System.out.println(Thread.currentThread().getId());

        Observable.interval(1,1, TimeUnit.MILLISECONDS)
                .take(5)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println(Thread.currentThread().getId());

                        OutputUtil.printLn("绑定");
                    }

                    @Override
                    public void onNext(Long s) {
                        System.out.println(Thread.currentThread().getId());

                        OutputUtil.printLn(System.currentTimeMillis()+"");
                        OutputUtil.printLn(s+"");
                    }

                    @Override
                    public void onError(Throwable e) {
                        OutputUtil.printLn("错误");
                    }

                    @Override
                    public void onComplete() {
                        OutputUtil.printLn("完成");
                    }
                });
    }
}
