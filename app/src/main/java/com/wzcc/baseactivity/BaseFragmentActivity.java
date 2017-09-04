package com.wzcc.baseactivity;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import wzcc.com.helloworld.R;

public class BaseFragmentActivity extends FragmentActivity{
	protected int statusBarHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	protected void immerseLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBarHeight = getStatusBarHeight(this.getBaseContext());
            View viewStatusBarTop = findViewById(R.id.status_bar_top);
            if (viewStatusBarTop != null) {
            	 RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) viewStatusBarTop.getLayoutParams();
                 params2.height = statusBarHeight;
                 viewStatusBarTop.setLayoutParams(params2);
                 viewStatusBarTop.setVisibility(View.VISIBLE);
			}
        }
    }

    public int getStatusBarHeight(Context context) {
        if (statusBarHeight != 0)
            return statusBarHeight;

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

}
