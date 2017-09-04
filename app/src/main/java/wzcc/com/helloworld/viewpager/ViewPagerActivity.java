package wzcc.com.helloworld.viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import wzcc.com.helloworld.R;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.circleIndicator)
    CirclePageIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        FragmentPagerAdapter mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);

        circleIndicator.setViewPager(viewPager);
    }
}
