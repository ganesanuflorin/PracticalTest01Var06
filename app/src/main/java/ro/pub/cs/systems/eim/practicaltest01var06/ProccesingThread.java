package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;

public class ProccesingThread extends Thread{
    private Context context = null;

    private boolean isRunning = true;

    private static final String TAG = "Colocviu1_6Service";

    private int score;


    public ProccesingThread(Context context, int scor) {
        this.context = context;
        this.score = scor;

    }

    @Override
    public void run() {
        Log.d(TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            if (score > 300) {
                sleep();
                sendMessage();
                stopThread();
            } else {
                stopThread();
            }
        }
        Log.d(TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("punctaj");
        intent.putExtra("message",
                new Date(System.currentTimeMillis()) + " Score: " + score);
        context.sendBroadcast(intent);
    }


    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
