package zohar.com.fristreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class NineActivity extends AppCompatActivity {

    private static final String TAG = "NineActivity";

    private Button mBtnRequest;
    private Button mBtnOkHttp;
    private Button mBtnXmlPull;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        mBtnRequest = findViewById(R.id.btn_send_request);
        mBtnOkHttp = findViewById(R.id.btn_send_request_ok);
        mBtnXmlPull = findViewById(R.id.btn_xml_pull);

        mBtnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NineActivity.this,HttpURLActivity.class);
                startActivity(intent);
            }
        });

        mBtnOkHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NineActivity.this,OkHttpActivity.class);
                startActivity(intent);
            }
        });

        mBtnXmlPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NineActivity.this,XmlPullActivity.class);
                startActivity(intent);
            }
        });
    }




}
