package ru.zubrilinartem.multithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WaitNotify extends AppCompatActivity {

    private static final Object monitor = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_notify);

        Button btnThreadOne = findViewById(R.id.btnThreadOne);
        Button btnThreadTwo = findViewById(R.id.btnThreadTwo);

        btnThreadOne.setOnClickListener(this::onClickThreadOne);
        btnThreadTwo.setOnClickListener(this::onClickThreadTwo);
    }


    private void sendData(){
        Log.d("my_log", "поток 1 пробует захватить monitor");
        synchronized (monitor){
            Log.d("my_log", "поток 1 уснул и отпустил monitor");
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("my_log", "поток 1 проснулся");
        }
    }

    private void prepareDate(){
        Log.d("my_log", "поток 2 пробует захватить монитор");
        synchronized (monitor){
            Log.d("my_log", "поток 2 сделал дела и отпустил монитор");
            monitor.notifyAll();
        }
    }

    public void onClickThreadOne(View v){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sendData();
            }
        };
        new Thread(runnable).start();
    }

    public void onClickThreadTwo(View v){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                prepareDate();
            }
        };
        new Thread(runnable).start();
    }

}