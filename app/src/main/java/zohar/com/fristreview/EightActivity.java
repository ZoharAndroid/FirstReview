package zohar.com.fristreview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class EightActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EightActivity";

    private Button mBtnMusicStart;
    private Button mBtnMusicStop;
    private Button mBtnMusicPause;
    private Button mBtnMovieStart;
    private Button mBtnMovieStop;
    private Button mBtnMoviePause;
    private VideoView mVvMovie;

    private MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

        mBtnMusicStart = findViewById(R.id.btn_music_start);
        mBtnMusicPause = findViewById(R.id.btn_music_pause);
        mBtnMusicStop = findViewById(R.id.btn_music_stop);
        mBtnMovieStart = findViewById(R.id.btn_video_start);
        mBtnMoviePause = findViewById(R.id.btn_video_pause);
        mBtnMovieStop = findViewById(R.id.btn_video_stop);
        mVvMovie = findViewById(R.id.vv_movie);

        mBtnMusicStart.setOnClickListener(this);
        mBtnMusicPause.setOnClickListener(this);
        mBtnMusicStop.setOnClickListener(this);
        mBtnMovieStart.setOnClickListener(this);
        mBtnMovieStop.setOnClickListener(this);
        mBtnMoviePause.setOnClickListener(this);

        //判断全选
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            initMusicPlay();
            initMoviePlay();
        }

    }

    private void initMoviePlay(){
        File file = new File(Environment.getExternalStorageDirectory()+"/Music","wmxd.mp4");
        mVvMovie.setVideoPath(file.getPath());
    }

    private void initMusicPlay(){
        Log.d(TAG,Environment.getExternalStorageDirectory().toString()); ///storage/emulated/0
        File file = new File(Environment.getExternalStorageDirectory()+"/Music","music.mp3");
        player = new MediaPlayer();
        try {
            player.setDataSource(file.getPath());
            player.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMusicPlay();
                    initMoviePlay();
                }else{
                    Toast.makeText(EightActivity.this,"授权失败",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_music_start:
                if (!player.isPlaying()){
                    player.start();
                }
                break;
            case R.id.btn_music_pause:
                if (player.isPlaying()){
                    player.pause();
                }
                break;
            case R.id.btn_music_stop:
                if (player.isPlaying()){
                    player.reset();
                    initMusicPlay();
                }
                break;
            case R.id.btn_video_start:
                if (!mVvMovie.isPlaying()){
                    mVvMovie.start();
                }
                break;
            case R.id.btn_video_pause:
                if (mVvMovie.isPlaying()){
                    mVvMovie.pause();
                }
                break;
            case R.id.btn_video_stop:
                if (mVvMovie.isPlaying()){
                    mVvMovie.resume();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null){
            player.stop();
            player.release();
        }

        if (mVvMovie!=null){
            mVvMovie.suspend();
        }
    }
}
