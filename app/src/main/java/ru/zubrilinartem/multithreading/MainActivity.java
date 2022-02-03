package ru.zubrilinartem.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnExample = findViewById(R.id.btnExample);
        btnExample.setOnClickListener(this::onClickExample);

        Button btnNotify = findViewById(R.id.btnNotify);
        btnNotify.setOnClickListener(this::onClickNotify);

        Button btnLooper = findViewById(R.id.btnLooper);
        btnLooper.setOnClickListener(this::onClickLooper);

    }

    private void onClickExample(View view){
        Intent intent = new Intent(this, ExampleActivity.class);
        startActivity(intent);
    }

    private void onClickNotify(View view){
        Intent intent = new Intent(this, WaitNotify.class);
        startActivity(intent);
    }

    private void onClickLooper(View view){
        Intent intent = new Intent(this, LooperActivity.class);
        startActivity(intent);
    }
}