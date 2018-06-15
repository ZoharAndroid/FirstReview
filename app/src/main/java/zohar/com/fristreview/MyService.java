package zohar.com.fristreview;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {

    private static final String TAG = "MyService";

    private DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on create 执行");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new Notification.Builder(this).setAutoCancel(true).setContentText("这是主要内容").setContentTitle("这是标题").setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)).setSmallIcon(R.mipmap.ic_launcher).setContentIntent(pi).build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand 执行");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy 执行");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "开始下载");
        }


        public void getProgress() {
            Log.d(TAG, "获取进度");
        }
    }
}
