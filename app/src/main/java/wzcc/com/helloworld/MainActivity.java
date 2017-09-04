package wzcc.com.helloworld;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.squareup.picasso.Picasso;
import com.wzcc.toollibary_jlj.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wzcc.com.helloworld.VideoPlayer.VideoPlayerActivity;
import wzcc.com.helloworld.animtaion.AnimationActivity;
import wzcc.com.helloworld.model.ModelSample;
import wzcc.com.helloworld.slidingmenu.SampleListFragment;
import wzcc.com.helloworld.viewpager.ViewPagerActivity;

public class MainActivity extends SlidingFragmentActivity {

    //    @BindView(R.id.txtResult)
//    TextView _txtResult;
//    @BindView(R.id.imgResult)
//    ImageView _imgResult;
    @BindView(R.id.list_main)
    RecyclerView listMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setBehindContentView(R.layout.left_menu_frame);
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        SampleListFragment leftMenu = new SampleListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu, leftMenu).commit();

        setList();
    }

    private void setList() {
        final List<ModelSample> datas = new ArrayList<ModelSample>();
        ModelSample sample = new ModelSample("访问url");
        datas.add(sample);
        sample = new ModelSample("打开自定义dialog");
        datas.add(sample);
        sample = new ModelSample("跳转Activity");
        datas.add(sample);
        sample = new ModelSample("ViewPager");
        datas.add(sample);
        sample = new ModelSample("VideoPlayer");
        datas.add(sample);
        SampleAdapter adapter = new SampleAdapter(R.layout.item_sample,datas);
        listMain.setAdapter(adapter);
        listMain.setLayoutManager(new LinearLayoutManager(this));
        listMain.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        new MaterialDialog.Builder(MainActivity.this).title("我为歌狂").titleGravity(GravityEnum.CENTER).content("菊花残 满地伤 你的影子已彷徨").positiveText("确定").negativeText("取消").onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, VideoPlayerActivity.class));
                        break;
                }
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        ImageView headView = new ImageView(MainActivity.this);
//        headView.setScaleType(ImageView.ScaleType.FIT_XY);
        headView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        RecyclerView.LayoutParams rp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,500);
        headView.setLayoutParams(rp);
        String imgurl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503565558277&di=09803f4fd1ab7a2b86c84e3feeda2f4c&imgtype=0&src=http%3A%2F%2Fphotos.tuchong.com%2F331716%2Ff%2F4645640.jpg";
        Picasso.with(this).load(imgurl).into(headView);
        adapter.addHeaderView(headView);
        adapter.bindToRecyclerView(listMain);
        adapter.setEmptyView(R.layout.layout_empty);
        adapter.setHeaderFooterEmpty(true,true);
        View emptyView = adapter.getEmptyView();
        int evhbefore = ScreenUtils.getScreenHeight(MainActivity.this);
        int evhafter = evhbefore-500-ScreenUtils.dip2px(MainActivity.this,44);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,evhafter);
        emptyView.setLayoutParams(lp);
    }

    public class SampleAdapter extends BaseQuickAdapter<ModelSample,BaseViewHolder>{
        List<ModelSample> mdatas ;
        public SampleAdapter(@LayoutRes int layoutResId, @Nullable List<ModelSample> data) {
            super(layoutResId, data);
            this.mdatas = data;
        }

        @Override
        protected void startAnim(Animator anim, int index) {
            if (index < mdatas.size())
                anim.setStartDelay(index * 150);
            super.startAnim(anim, index);
        }

        @Override
        protected void convert(BaseViewHolder helper, ModelSample item) {
            helper.setText(R.id.txt_title,item.title);
        }
    }

//    @OnClick(R.id.btnOperate)
//    public void btnOperate(View view) {
//        String credentials = "user" + ":" + "password";
//        final String basic =
//                "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public okhttp3.Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Authorization", basic)
//                        .header("Accept", "application/json")
//                        .method(original.method(), original.body());
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://115.236.161.139:7090")
//                .addConverterFactory(GsonConverterFactory.create());
//        OkHttpClient client = httpClient.build();
//        Retrofit retrofit = builder.client(client).build();
//        AsyncInterface asyncInterface = retrofit.create(AsyncInterface.class);
//        Call<BaseCallResultModel<ModelUser>> call = asyncInterface.doLogin("18317897448", "a");
//        call.enqueue(new Callback<BaseCallResultModel<ModelUser>>() {
//            @Override
//            public void onResponse(Call<BaseCallResultModel<ModelUser>> call, Response<BaseCallResultModel<ModelUser>> response) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("\nbody:" + response.code() + "," + response.toString());
//                sb.append(response.body().toString());
//                BaseCallResultModel<ModelUser> user = response.body();
//                sb.append("\n");
//                sb.append("userName = " + user.data.userName + ";\n");
//                _txtResult.setText(sb.toString());
//            }
//
//            @Override
//            public void onFailure(Call<BaseCallResultModel<ModelUser>> call, Throwable t) {
//                _txtResult.setText("出现异常");
//
//            }
//        });
//        Picasso.with(this).load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3434671155,3476540791&fm=200&gp=0.jpg").into(_imgResult);
//    }


//    public interface AsyncInterface {
//        @FormUrlEncoded
//        @POST("/zfapp/login")
//        Call<BaseCallResultModel<ModelUser>> doLogin(@Field("phone") String phone, @Field("password") String password);
//    }
}
