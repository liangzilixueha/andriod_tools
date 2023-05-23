package com.liangzilixueha.andriod_tools;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class cl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cl1);
        TextView textView = findViewById(R.id.tv1);
        textView.setOnClickListener(view -> {
            //打开摄像头
            Log.e("csw", "打开摄像头" );
        });
    }
}