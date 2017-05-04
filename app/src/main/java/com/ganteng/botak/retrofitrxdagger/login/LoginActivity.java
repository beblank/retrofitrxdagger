package com.ganteng.botak.retrofitrxdagger.login;

import android.content.Intent;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ganteng.botak.retrofitrxdagger.R;
import com.ganteng.botak.retrofitrxdagger.base.App;
import com.ganteng.botak.retrofitrxdagger.login.model.Post;
import com.ganteng.botak.retrofitrxdagger.login.model.ServerResponse;
import com.ganteng.botak.retrofitrxdagger.menu.MenuActivity;
import com.ganteng.botak.retrofitrxdagger.network.NetworkService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "dodol";
    @Inject
    Retrofit retrofit;

    @BindView(R.id.submitBtn)
    Button submitBtn;

    @BindView(R.id.usernameInput)
    EditText usernameInput;

    @BindView(R.id.passwordInput)
    EditText passwordInput;

    private Subscription subscription;
    private String username;
    private String password;

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
        setLogin();
        switch (v.getId()){
            case R.id.submitBtn:
                sendLogin();
                break;
        }

    }

    private void setLogin(){
        username = usernameInput.getText().toString();
        password = passwordInput.getText().toString();
    }

    private void sendData(){
        Toast.makeText(this, "Sends Data", Toast.LENGTH_SHORT).show();
        Observable<Post> observable = retrofit.create(NetworkService.class).sendPost(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        subscription = observable.subscribe(new Subscriber<Post>() {
                    @Override
                    public void onCompleted() {
                        startMenuActivity();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(TAG, "Post onNext: " + post.toString());
                    }
                });
    }

    private void sendLogin(){
        Toast.makeText(this, "send Login", Toast.LENGTH_SHORT).show();
        Observable<ServerResponse> observable = retrofit.create(NetworkService.class).postLogin("login", username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        subscription = observable.subscribe(new Subscriber<ServerResponse>() {
            @Override
            public void onCompleted() {
                startMenuActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
            }

            @Override
            public void onNext(ServerResponse serverResponse) {
                Log.d(TAG, "onNext: " + serverResponse.getMessage() + " " + serverResponse.toString());

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
                        Log.d(TAG, "Get onNext: " + post.toString());
                    }
                });
    }

    private void startMenuActivity(){
        Intent intent = new Intent(this, MenuActivity.class);
        Bundle bundle = new Bundle();

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        Log.d(TAG, "onDestroy: unsubscribed");
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        
    }
}
