package zohar.com.fristreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLActivity extends AppCompatActivity {
    private static final String TAG = "NineActivity";

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
                requestHttp();
            }
        });
    }

    private void requestHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(4000);
                    connection.setConnectTimeout(4000);
                    connection.setRequestMethod("GET");
                    InputStream inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer response = new StringBuffer();
                    String line = null;
                    while((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    Log.d(TAG,response.toString());
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (reader != null){
                        try{
                            reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                    if (connection != null){
                        connection.disconnect();
                    }
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
