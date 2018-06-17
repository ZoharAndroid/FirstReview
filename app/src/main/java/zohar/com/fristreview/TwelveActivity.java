package zohar.com.fristreview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class TwelveActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TwelveActivity";

    private Button mBtnDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve);
        mBtnDrawerLayout = findViewById(R.id.btn_drawer_layout);

        mBtnDrawerLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_drawer_layout:
                Intent intent = new Intent(TwelveActivity.this,DrawerLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
