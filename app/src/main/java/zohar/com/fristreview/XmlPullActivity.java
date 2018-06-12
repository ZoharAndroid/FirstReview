package zohar.com.fristreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class XmlPullActivity extends AppCompatActivity {

    private static final String TAG = "XmlPullActivity";

    private TextView mTvXml;
    private Button mBtnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        mTvXml = findViewById(R.id.tv_xml_pull);
        mBtnSend = findViewById(R.id.btn_send_xml);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestHttpUrl();
            }
        });


    }


    private void sendRequestHttpUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://10.0.2.2:8080/get_data.xml");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    InputStream is = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is));
                    String line = null;
                    StringBuilder builder = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    String responseData = builder.toString();
                    Log.d(TAG, responseData);
                    parseXmlWithPull(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();
    }


    private void parseXmlWithPull(String xmlData) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser = factory.newPullParser();
            pullParser.setInput(new StringReader(xmlData));
            int eventType = pullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String node = pullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(node)){
                            id = pullParser.nextText();
                        }else if("name".equals(node)){
                            name = pullParser.nextText();
                        }else  if ("version".equals(node)){
                            version = pullParser.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(node)){
                            Log.d(TAG,"id is "+ id);
                            Log.d(TAG,"name is "+ name);
                            Log.d(TAG,"version is "+ version);
                        }
                        break;
                    default:
                        break;
                }
                eventType = pullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
