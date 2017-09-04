package wzcc.com.helloworld.animtaion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ditclear.swipelayout.SwipeDragLayout;
import com.wzcc.toollibary_jlj.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import wzcc.com.helloworld.R;

public class AnimationActivity extends AppCompatActivity {

    @BindView(R.id.star)
    ImageView star;
    @BindView(R.id.trash)
    ImageView trash;
    @BindView(R.id.iv_type)
    ImageView ivType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.item_surface)
    LinearLayout itemSurface;
    @BindView(R.id.swip_layout)
    SwipeDragLayout swipLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);
        swipLayout.addListener(new SwipeDragLayout.SwipeListener() {
            @Override
            public void onUpdate(SwipeDragLayout layout, float offset) {

            }

            @Override
            public void onOpened(SwipeDragLayout layout) {
                UIHelper.showToast(AnimationActivity.this,"opened");
            }

            @Override
            public void onClosed(SwipeDragLayout layout) {
                UIHelper.showToast(AnimationActivity.this,"closed");
            }

            @Override
            public void onClick(SwipeDragLayout layout) {
                UIHelper.showToast(AnimationActivity.this,"onclick");
            }
        });
    }

}
