package zohar.com.fristreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TenActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn103;
    private Button mBtnDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten);
        mBtn103 = findViewById(R.id.btn_10_3);
        mBtnDownload = findViewById(R.id.btn_download);

        mBtn103.setOnClickListener(this);
        mBtnDownload.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_10_3:
                Intent intent = new Intent(TenActivity.this,ServiceBasicActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_download:
                Intent downloadIntent = new Intent(TenActivity.this,DownloadActivity.class);
                startActivity(downloadIntent);
                break;
            default:
                break;
        }
    }
}
