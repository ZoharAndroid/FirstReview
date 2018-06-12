package zohar.com.fristreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = "OkHttpActivity";

    private TextView mTvResponse;
    private Button mBtnRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url);
        mTvResponse = findViewById(R.id.tv_response);
        mBtnRequest = findViewById(R.id.btn_send_request);

        mBtnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOkHttpRequest();
            }
        });
    }

    private void sendOkHttpRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    Response response= client.newCall(request).execute();
                    String responseContent = response.body().toString();
                    Log.d(TAG,responseContent);
                    showResponse(responseContent);
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvResponse.setText(response);
            }
        });
    }
}
