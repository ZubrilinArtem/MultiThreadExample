package ru.zubrilinartem.multithreading;

import androidx.annotation.MainThread;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LooperActivity extends AppCompatActivity {

	private Object test1 = new Object();
	private Object test2 = new Object();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_looper);
		Button btnStart = findViewById(R.id.button_start);
		btnStart.setOnClickListener(this::onClickStart);
	}

	public void onClickStart(View v) {
		startThread1();
		startThread2();
	}

	public void example() {
		synchronized (test1) {
			Log.d("my_log", "захвачен test1");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			example2();
		}
	}

	public void example2() {
		synchronized (test2) {
			try {
				Log.d("my_log", "захвачен test2");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			example();
		}
	}

	public void startThread1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				example();
				Log.d("my_log", "1 поток выполнен");
			}
		}).start();
	}

	public void startThread2() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				example2();
				Log.d("my_log", "2 поток выполнен");
			}
		}).start();
	}
}



//	}
//
//	public void startThread(View view) {
//		looperThread.start();
//	}
//
//	public void stopThread(View view) {
//		looperThread.looper.quit();
//	}
//
//	public void taskA(View view) {
//		Message msg = Message.obtain();
//		msg.what = TASK_A;
//		looperThread.handler.sendMessage(msg);
//
//	}
//
//	public void taskB(View view) {
//		Message msg = Message.obtain();
//		msg.what = TASK_B;
//		looperThread.handler.sendMessage(msg);
//	}
//
//	public class ExampleHandler extends Handler {
//		private static final String TAG = "ExampleHandler";
//
//		public static final int TASK_A = 1;
//		public static final int TASK_B = 2;
//
//		@Override
//		public void handleMessage(Message msg) {
//			switch (msg.what) {
//				case TASK_A:
//					Log.d(TAG, "Task A executed");
//					break;
//				case TASK_B:
//					Log.d(TAG, "Task B executed");
//					break;
//			}
//		}
//	}
//
//
//	public class ExampleLooperThread extends Thread {
//		private static final String TAG = "ExampleLooperThread";
//
//		public Looper looper;
//		public Handler handler;
//
//		@Override
//		public void run() {
//			Looper.prepare();
//
//			looper = Looper.myLooper();
//
//			handler = new ExampleHandler();
//
//			Looper.loop();
//
//			Log.d(TAG, "End of run()");
//		}
//	}
//
////	public class ExampleHandleThread extends HandlerThread{
////
////		public ExampleHandleThread(String name) {
////			super(name);
////		}
////
////		public void postTask(Runnable task){
////			ExampleHandler.post(task);
////		}
////
////		public void prepareHandler(){
////			ExampleHandler = new Handler(getLooper());
////		}
////
////	}

