package cn.smlcx.testset.activity;

import android.util.Log;

import cn.smlcx.testset.R;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ThirdActivity extends BaseActivity {
   /* @BindView(R.id.textHello)
    TextView mTextHello;*/

    @Override
    protected void initViews() {
        //通过create创建observable对象，在call中调用subscriber的onnext方法
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0;i < 20;i++)        {
                    subscriber.onNext("fuck i is " + i);
                }
                subscriber.onCompleted();
            }});

        //上面的代码我们已经构建了一个观察者，我们接下来新建一个订阅者
        Subscriber<String> subscriber = new Subscriber<String>()
        {
            @Override
            public void onCompleted() {
                Log.i("rxjava", "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                Log.i("rxjava", "error");
            }
            @Override
            public void onNext(String o) {
                Log.i("rxjava", o);
            }};

        //通过调用subscribe方法使观察者和订阅者产生关联，一旦订阅就观察者就开始发送消息
        observable.subscribe(subscriber);

        /***************************************简化步骤******************************************/
        /**Integer类型参数**/
        Observable.just(6).subscribe(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("danxx" ,"Integer--->"+integer);
                    }
                }
        );
        /**String类型参数**/
        Observable.just("String args").subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d("danxx" ,"String--->"+s);
                    }
                }
        );

        /***************************************map操作******************************************/
        /**map感觉就是一个中间加工厂，可以把结果做处理，减少观察者的处理步骤**/
        Observable.just("I am ->")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        /**map处理结果**/
                        return s+"map";
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("danxx" ,"map 处理后的结果 String--->"+s);
            }
        });

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_third;
    }

}
