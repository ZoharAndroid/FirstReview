package zohar.com.fristreview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class TwelveActivity extends AppCompatActivity {

    private static final String TAG = "TwelveActivity";

    private Toolbar mTbBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve);
        mTbBar = findViewById(R.id.tb_action_bar);
        setSupportActionBar(mTbBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(TwelveActivity.this,"备份",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(TwelveActivity.this,"share",Toast.LENGTH_SHORT).show();
                break;
            case R.id.love:
                Toast.makeText(TwelveActivity.this,"love",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(TwelveActivity.this,"setting",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
