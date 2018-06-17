package zohar.com.fristreview.twelve;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import zohar.com.fristreview.R;

public class FruitActivity extends AppCompatActivity {

    private String mFruitName;
    private int mFruitImageId;

    private ImageView mImageView;
    private TextView mTextViewContent;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        mFruitImageId = getIntent().getIntExtra("fruit_id",0);
        mFruitName = getIntent().getStringExtra("fruit_name");

        mImageView = findViewById(R.id.image_view_detail);
        mToolbar = findViewById(R.id.tool_bar_fruit);
        mTextViewContent = findViewById(R.id.text_view_content);
        mCollapsingBar = findViewById(R.id.collapsing_tool_bar);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingBar.setTitle(mFruitName);
        mTextViewContent.setText(getContentString(mFruitName));
        mImageView.setImageResource(mFruitImageId);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getContentString(String mFruitName){
        StringBuilder content = new StringBuilder();
        for (int i = 0 ;i<500;i++){
            content.append(mFruitName);
        }
        return content.toString();
    }

}
