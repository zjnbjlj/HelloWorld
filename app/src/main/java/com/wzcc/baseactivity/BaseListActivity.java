package com.wzcc.baseactivity;


import android.app.ListActivity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;

import wzcc.com.helloworld.R;

public class BaseListActivity extends ListActivity{
	protected int statusBarHeight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	protected void immerseLayout( ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBarHeight = getStatusBarHeight(this.getBaseContext());
            View viewStatusBarTop = findViewById(R.id.status_bar_top);
            if (viewStatusBarTop != null) {
            	LayoutParams params = (LayoutParams) viewStatusBarTop.getLayoutParams();
            	 params.height = statusBarHeight;
                 viewStatusBarTop.setLayoutParams(params);
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
