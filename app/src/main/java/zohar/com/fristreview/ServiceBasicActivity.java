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

public class ServiceBasicActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStartService;
    private Button mBtnStopService;
    private Button mBtnStartBindService;
    private Button mBtnStopBindService;

    private MyService.DownloadBinder binder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MyService.DownloadBinder) iBinder;
            binder.startDownload();
            binder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_basic);
        mBtnStartService = findViewById(R.id.btn_start_service);
        mBtnStopService = findViewById(R.id.btn_stop_service);
        mBtnStartBindService = findViewById(R.id.btn_start_bind_service);
        mBtnStopBindService = findViewById(R.id.btn_stop_bind_service);

        mBtnStopService.setOnClickListener(this);
        mBtnStartService.setOnClickListener(this);
        mBtnStopBindService.setOnClickListener(this);
        mBtnStartBindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_service:
                Intent intent = new Intent(ServiceBasicActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
                Intent stopintent = new Intent(ServiceBasicActivity.this,MyService.class);
                stopService(stopintent);
                break;
            case R.id.btn_start_bind_service:
                Intent bindService = new Intent(ServiceBasicActivity.this,MyService.class);
                bindService(bindService,connection,BIND_AUTO_CREATE);
                break;
            case R.id.btn_stop_bind_service:
                unbindService(connection);
                break;
            default:
                break;
        }

    }
}
