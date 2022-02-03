package ru.zubrilinartem.multithreading;

import androidx.annotation.MainThread;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ExampleActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private TextView tvCount;
    private TextView tvResult;
    private SeekBar sbTime;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        //инициализация view
        tvCount = findViewById(R.id.tvCount);
        sbTime = findViewById(R.id.sbTime);
        tvResult = findViewById(R.id.tcResult);
        Button btnCount = findViewById(R.id.btnCount);

        //создаем слушателей
        btnCount.setOnClickListener(this::onClickCount);
        Button btnLongOperation = findViewById(R.id.btnLongOperation);
        btnLongOperation.setOnClickListener(this::onClickLongOperation);
        sbTime.setOnSeekBarChangeListener(this);
        tvResult.setText("1 сек");
    }

    public void onClickCount(View v){
        int count = Integer.parseInt(tvCount.getText().toString());
        count++;
        tvCount.setText(String.valueOf(count));
    }

    public void onClickLongOperation(View v) {

        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
                    tvResult.post(new Runnable() {
                        @Override
                        public void run() {
                            tvResult.setText("test" + "one");
                        }
                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        time = i * 1000;
        tvResult.setText(time / 1000 + " сек");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

}