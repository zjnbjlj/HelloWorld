package wzcc.com.helloworld.slidingmenu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;
import com.wzcc.toollibary_jlj.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wzcc.com.helloworld.R;

/**
 * Created by jlj on 2017/8/10.
 */

public class TestFragmentMenu extends Fragment {

    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.testText)
    TextView testText;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_test,null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        testText.setText("Hello world！");

        Picasso.with(getActivity()).load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3434671155,3476540791&fm=200&gp=0.jpg").into(imgLogo);
        ImageLoader.getInstance().displayImage("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3434671155,3476540791&fm=200&gp=0.jpg", imgLogo, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                UIHelper.showToast(getActivity(),"开始获取图片");
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                UIHelper.showToast(getActivity(),"获取图片完毕");
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
    @OnClick(R.id.imgLogo)
    public void imglogo(View view){
        UIHelper.showToast(getActivity(),"图片点击");
    }
    @OnClick(R.id.testText)
    public void testText(View view){
        UIHelper.showToast(getActivity(),"文本点击");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
