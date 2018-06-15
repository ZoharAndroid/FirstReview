package zohar.com.fristreview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnEight;
    private Button mBtnNine;
    private Button mBtnTen;
    private Button mBtnTwelve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnEight = findViewById(R.id.btn_eight);
        mBtnNine = findViewById(R.id.btn_nine);
        mBtnTen = findViewById(R.id.btn_ten);
        mBtnTwelve  = findViewById(R.id.btn_twelve);

        mBtnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EightActivity.class);
                startActivity(intent);
            }
        });

        mBtnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NineActivity.class);
                startActivity(intent);
            }
        });

        mBtnTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TenActivity.class);
                startActivity(intent);
            }
        });

        mBtnTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TwelveActivity.class);
                startActivity(intent);
            }
        });
    }

}
