package com.ganteng.botak.retrofitrxdagger.login;

import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ganteng.botak.retrofitrxdagger.R;
import com.ganteng.botak.retrofitrxdagger.base.App;
import com.ganteng.botak.retrofitrxdagger.login.model.Post;
import com.ganteng.botak.retrofitrxdagger.network.NetworkService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "dodol";
    @Inject
    Retrofit retrofit;

    @BindView(R.id.submitBtn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App)getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);


        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitBtn:
                sendData();
                break;
        }

    }

    private void sendData(){
        Toast.makeText(this, "Get Data", Toast.LENGTH_SHORT).show();
        retrofit.create(NetworkService.class).sendPost("dodol", "body dodol")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: " + post.toString());
                    }
                });
    }

    private void getData(){
        Toast.makeText(this, "Get Data", Toast.LENGTH_SHORT).show();
        retrofit.create(NetworkService.class).getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "onNext: " + post.toString());
                    }
                });
    }
}
