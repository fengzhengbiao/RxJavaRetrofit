package com.example.su.rxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.su.rxjavaretrofit.entity.Subject;
import com.example.su.rxjavaretrofit.http.HttpMethods;
import com.example.su.rxjavaretrofit.subscribler.ProgressSubscrible;
import com.example.su.rxjavaretrofit.subscribler.SubscriberOnNextListener;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    Button btnGet;
    TextView tvResult;
    private SubscriberOnNextListener<List<Subject>> nextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = (Button) findViewById(R.id.btn_get);
        tvResult = (TextView) findViewById(R.id.tv_result);
        ButterKnife.bind(this);
        nextListener = subjects -> tvResult.setText(subjects.toString());
        btnGet.setOnClickListener(v -> HttpMethods.getInstance().getTopMovie(new ProgressSubscrible<>(nextListener), 1, 10));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
