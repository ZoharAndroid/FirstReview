package zohar.com.fristreview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zohar.com.fristreview.twelve.Fruit;
import zohar.com.fristreview.twelve.FruitAdapter;


public class NavigationViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFloationgButton;
    private RecyclerView mRecyclerView;

    private Fruit[] fruits = {new Fruit("苹果",R.mipmap.apple),new Fruit("草莓",R.mipmap.strawberry),new Fruit("梨子",R.mipmap.pear),new Fruit("芒果",R.mipmap.mango)};
    private List<Fruit> listFrusts = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mToolbar = findViewById(R.id.tool_bar_navigation);
        mNavigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = findViewById(R.id.navigation_drawer_layout);
        mFloationgButton = findViewById(R.id.floating_button);
        mRecyclerView = findViewById(R.id.recycler_view_navigation);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);
        }

        mNavigationView.setCheckedItem(R.id.call);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.call:
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(NavigationViewActivity.this, "call", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.friends:
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(NavigationViewActivity.this, "friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(NavigationViewActivity.this, "location", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.task:
                        mDrawerLayout.closeDrawers();
                        Toast.makeText(NavigationViewActivity.this, "task", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


        mFloationgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(NavigationViewActivity.this, "Floating Button", Toast.LENGTH_SHORT).show();
                 Snackbar.make(view,"删除数据",Snackbar.LENGTH_SHORT).setAction("撤回", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(NavigationViewActivity.this, "数据恢复", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });

        initFruits();
        GridLayoutManager manager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(new FruitAdapter(this,listFrusts));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFruits(){
        listFrusts.clear();
        for (int i = 0;i<50;i++){
            Random random  = new Random();
            int index = random.nextInt(fruits.length);
            listFrusts.add(fruits[index]);
        }
    }
}
