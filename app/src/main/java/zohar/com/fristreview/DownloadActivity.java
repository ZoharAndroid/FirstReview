package zohar.com.fristreview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartDownload;
    private Button mBtnPauseDownload;
    private Button mBtnCancelDownload;

    private DownloadService.DownloadBinder downloadBinder;


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (DownloadService.DownloadBinder)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        mBtnStartDownload = findViewById(R.id.btn_start_download);
        mBtnCancelDownload = findViewById(R.id.btn_cancel_download);
        mBtnPauseDownload = findViewById(R.id.btn_pause_download);

        mBtnPauseDownload.setOnClickListener(this);
        mBtnCancelDownload.setOnClickListener(this);
        mBtnStartDownload.setOnClickListener(this);

        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent,connection,BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_download:
                String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
                downloadBinder.startDownload(url);
                break;
            case R.id.btn_cancel_download:
                downloadBinder.cancelDownload();
                break;
            case R.id.btn_pause_download:
                downloadBinder.pauseDownload();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
