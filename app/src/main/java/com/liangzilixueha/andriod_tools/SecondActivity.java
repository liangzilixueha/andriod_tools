package com.liangzilixueha.andriod_tools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Magnifier;
import android.widget.TextView;

import com.liangzilixueha.liangzipage.LiangziFiles;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button btn = findViewById(R.id.btn);
        TextView tv = findViewById(R.id.tv);
        btn.setOnClickListener(v -> {
            LiangziFiles liangziFiles = new LiangziFiles();
            String time = new Date().toString() + " ";
            String filename = this.getClass().getName() + " ";
            String functionname = "onCreate ";
            String error = "这是错误信息";
            String string = time + filename + functionname + error;
            liangziFiles.writeLog(SecondActivity.this, string);
            tv.setText(liangziFiles.readLog(SecondActivity.this));
        });
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> {
            LiangziFiles liangziFiles = new LiangziFiles();
            liangziFiles.clearLog(SecondActivity.this);
            tv.setText(liangziFiles.readLog(SecondActivity.this));
        });

    }
}