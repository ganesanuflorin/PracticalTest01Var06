package ro.pub.cs.systems.eim.practicaltest01var06;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var06Service extends Service {
    private ProccesingThread proccesingThred;
    private int score;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        score = intent.getIntExtra("scoreService", 0);
        proccesingThred = new ProccesingThread(this, score);
        proccesingThred.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        proccesingThred.stopThread();
    }
}