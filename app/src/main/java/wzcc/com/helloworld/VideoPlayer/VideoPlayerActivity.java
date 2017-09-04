package wzcc.com.helloworld.VideoPlayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import wzcc.com.helloworld.R;

public class VideoPlayerActivity extends AppCompatActivity {

    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);
        videoplayer.setUp("http://video.jiecao.fm/11/23/xu/%E5%A6%B9%E5%A6%B9.mp4", JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"嫂子闭眼睛");
        Picasso.with(this)
                .load("http://img4.jiecaojingxuan.com/2016/11/23/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png@!640_360")
                .into(videoplayer.thumbImageView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (JCVideoPlayer.backPress()) {
            return;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
