package com.wzcc.baseactivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import wzcc.com.helloworld.R;

public class BaseFragment extends Fragment{

	protected int statusBarHeight;
	protected View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		view = inflater.inflate(R.layout.fragment_search_main, null);
		return view;
	}
	protected void immerseLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusBarHeight = getStatusBarHeight(getActivity().getBaseContext());
            View viewStatusBarTop = view.findViewById(R.id.status_bar_top);
            if (viewStatusBarTop != null ) {
            	 RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewStatusBarTop.getLayoutParams();
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
