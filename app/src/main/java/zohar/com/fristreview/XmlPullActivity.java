package zohar.com.fristreview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XmlPullActivity extends AppCompatActivity {

    private static final String TAG = "XmlPullActivity";

    private static final int XML = 1;
    private static final int SAX = 2;
    private static final int JSON = 3;
    private static final int GSON = 4;

    private TextView mTvXml;
    private Button mBtnSend;
    private Button mBtnSax;
    private Button mBtnJson;
    private Button mBtnGson;
    private Button mBtnHttp;
    private Button mBtnOkHttp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        mTvXml = findViewById(R.id.tv_xml_pull);
        mBtnSend = findViewById(R.id.btn_send_xml);
        mBtnSax = findViewById(R.id.btn_send_sax);
        mBtnJson = findViewById(R.id.btn_send_json);
        mBtnGson = findViewById(R.id.btn_send_gson);
        mBtnHttp = findViewById(R.id.btn_send_http_callback);
        mBtnOkHttp = findViewById(R.id.btn_send_okhttp_callback);

        mBtnHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtils.sendHttpRequest("http://10.0.2.2:8080/get_data.xml", new HttpCallbackListener() {
                    @Override
                    public void onFinished(String response) {
                        //mTvXml.setText(response);
                        Log.d(TAG,response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });


        mBtnOkHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUtils.sendOkHttpRequest("http://10.0.2.2:8080/get_data.xml", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Log.d(TAG,response.body().string());
                    }
                });
            }
        });


        mBtnGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestHttpUrl(4);
            }
        });

        mBtnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestHttpUrl(3);
            }
        });

        mBtnSax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestHttpUrl(2);
            }
        });

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestHttpUrl(1);
            }
        });


    }


    private void sendRequestHttpUrl(final int method) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                HttpURLConnection connection = null;
                URL url = null;
                try {
                    if (method == 1 || method == 2) {
                        url = new URL("http://10.0.2.2:8080/get_data.xml");
                    }

                    if (method == 3 || method == 4) {
                        url = new URL("http://10.0.2.2:8080/get_data.json");
                    }

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
                    switch (method) {
                        case 1:
                            //xml解析
                            parseXmlWithPull(responseData);
                            break;
                        case 2:
                            //SAX解析
                            parseXmlWithSax(responseData);
                            break;
                        case 3:
                            parseJsonWithJSONObject(responseData);
                            break;
                        case 4:
                            parseJsonWithGson(responseData);
                            break;
                        default:
                            break;
                    }


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


    private void parseJsonWithGson(String jsonData) {
        final StringBuilder content = new StringBuilder();
        Gson gson = new Gson();
        List<App> apps = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        for (App app : apps) {
            String id = app.getId();
            String name = app.getName();
            String version = app.getVersion();
            content.append("id:" + id + " name: " + name + "version: " + version + "\n");
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvXml.setText(content.toString());
            }
        });
    }


    private void parseJsonWithJSONObject(String jsonData) {
        final StringBuilder content = new StringBuilder();
        try {
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                final String id = jsonObject.getString("id");
                final String name = jsonObject.getString("name");
                final String version = jsonObject.getString("version");
                content.append("id:" + id + " name: " + name + "version: " + version + "\n");
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvXml.setText(content.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void parseXmlWithSax(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(new ContentHandler());
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                        if ("id".equals(node)) {
                            id = pullParser.nextText();
                        } else if ("name".equals(node)) {
                            name = pullParser.nextText();
                        } else if ("version".equals(node)) {
                            version = pullParser.getText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("app".equals(node)) {
                            Log.d(TAG, "id is " + id);
                            Log.d(TAG, "name is " + name);
                            Log.d(TAG, "version is " + version);
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
