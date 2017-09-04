package com.wzcc.common;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzcc.baseactivity.BaseActivity;
import com.wzcc.toollibary_jlj.StringHelper;
import com.wzcc.toollibary_jlj.UIHelper;

import wzcc.com.helloworld.CommonString;
import wzcc.com.helloworld.R;
import wzcc.com.helloworld.login.LoginActivity;

public abstract class AsyncActivity extends BaseActivity {

    //子类必须要实现的函数
    protected abstract int getLayoutResource();
    protected abstract String asyncGetData(String... params) throws Exception;
    protected boolean isRunning;
    protected InnerAsyncTask task;
    protected ProgressBar pbLoading;
//    private CustomProgressDialog      progressDialog;
    protected RelativeLayout _layouthint;
    protected FrameLayout layoutLoading;
    protected TextView txtLoadingMsg;
    
    protected View loading;
    private TextView  newsResultListEmptyText;
    private int INT_NULL = 0;
    private int INT_FALSE = 1;
	private ProgressBar _progressBar;
	protected String _result = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutResource());           
        loading = AsyncActivity.this.findViewById(android.R.id.empty);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        // 如果没有右上角的loading提示，就用自定义的loading.
//        if(pbLoading == null)
//        	progressDialog = CustomProgressDialog.createDialog(this).setMessage("正在加载中...");
        _layouthint = (RelativeLayout) this.findViewById(R.id.layout_hint);
        layoutLoading = (FrameLayout) findViewById(R.id.layout_loading);
        txtLoadingMsg = (TextView) findViewById(R.id.id_tv_loadingmsg);
        newsResultListEmptyText = (TextView)this.findViewById(R.id.resultListEmptyText);
        
        _progressBar = (ProgressBar) findViewById(R.id.pbload);
		if (_progressBar != null) {
			if (android.os.Build.VERSION.SDK_INT > 22) {
				final Drawable drawable = getResources().getDrawable(R.drawable.frame_loading2);
				_progressBar.setIndeterminateDrawable(drawable);
			}
		}
	}
    
    //layout返回按钮事件
    public void ToBack(View view){    	
         if(task != null){
             task.cancel(true);
         }
         AsyncActivity.this.finish();         
    }
    
    protected void setTitle(String text){
        TextView txtTitle;
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        if(txtTitle != null){
        	if(text.length()>12){
        		txtTitle.setText(text.substring(0, 10)+"..");
			}else{
				txtTitle.setText(text);
			}
            
        }
    }
    
    // list没有数据的时候，判断 。注：由于list=0没有值的时候addfootview无法显示出来，只能用这种方式。
    protected void showResult(int result){
    	if(newsResultListEmptyText!=null){
    		newsResultListEmptyText.setVisibility(View.VISIBLE);
    		if (_layouthint != null) {
    			_layouthint.setVisibility(View.VISIBLE);
    		}
    		View v = this.findViewById(R.id.imgHint);
    		if (v != null) {
        		v.setVisibility(View.VISIBLE);
			}
    		switch(result){
    		case 0:newsResultListEmptyText.setText("没有数据");break;
    		case 1:newsResultListEmptyText.setText("执行错误，请重试");break;
    		}			
		}   
        return;
    }
    
    protected void onPostAsyncTask(String result) throws Exception{
        return;
    }
    
    protected void onPreAsyncTask(){
        return;
    }
    
    protected void runAsyncTask(String... params) {
        //如果进度条可见，说明任务正在执行
        if(isRunning == true){
            return;
        }
        
        if(pbLoading!=null && pbLoading.getVisibility() == View.VISIBLE){
            return;
        }
        
        task = new InnerAsyncTask();
        task.execute(params);
    }
    /**
     * 异步获取列表要显示的数据
     * 
     * @author Leo Chen
     * 
     */
    protected class InnerAsyncTask extends AsyncTask<String, Integer, String> {
        private boolean mErrorFlag = false;

        @Override
        protected void onPreExecute() {
            isRunning = true;
            if(pbLoading !=null)
            	pbLoading.setVisibility(View.VISIBLE);
            if(loading!=null)
            	loading.setVisibility(View.VISIBLE);
//            else if(progressDialog!=null)
//            	progressDialog.show();
            else {
				if (layoutLoading != null) {
					layoutLoading.setVisibility(View.VISIBLE);
				}
			}
    	if (newsResultListEmptyText != null) {
    		newsResultListEmptyText.setVisibility(View.GONE);
		}
    	View v = AsyncActivity.this.findViewById(R.id.imgHint);
    	if(v!=null)
    		v.setVisibility(View.GONE);
    	
    	onPreAsyncTask();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {

            isRunning = false;
            
            // 数据加载完毕后，隐藏进度控件
            if(loading!=null){
            	loading.setVisibility(View.GONE);
            }
            if(pbLoading !=null)
            	pbLoading.setVisibility(View.GONE);
        	if (layoutLoading != null) {
				layoutLoading.setVisibility(View.GONE);
			}
//            else if(progressDialog!=null)
//        		progressDialog.dismiss();
        	 hideHintView();
            if (mErrorFlag == true) {
            	AsyncActivity.this.showResult(INT_FALSE);
            	UIHelper.showToast(AsyncActivity.this, "执行失败，请重试");                
                return;
            }

            if (result == null) {
            	AsyncActivity.this.showResult(INT_NULL);  
            	hideResultTip();
                return;
            }

            try {
				AsyncActivity.this.onPostAsyncTask(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
            if (!StringHelper.isEmpty(_result) && CommonString.isNeedRelogin(_result)) {
    			startActivityForResult(new Intent(AsyncActivity.this,LoginActivity.class).putExtra("type", "relogin"), CommonString.GOTOLOGIN);
    			UIHelper.showToast(AsyncActivity.this, "登录信息过期，请重新登录");
    		}

        }

		@Override
        protected String doInBackground(String... params) {
            String result = null;

            try {
                result = asyncGetData(params);
            } catch (Exception e) {
                mErrorFlag = true;
                LocalLog.writeLog(e);
                e.printStackTrace();
            }

            return result;
        }
    }
    
    private void hideHintView() {
    	if (_layouthint !=null) {
    		_layouthint.setVisibility(View.GONE);
		}
	}
    
    protected void hideResultTip(){
    	if (newsResultListEmptyText != null) {
    		newsResultListEmptyText.setVisibility(View.GONE);
		}
    }
    
    protected void setTipTextView (String tip){
    	if (txtLoadingMsg != null) {
        	txtLoadingMsg.setText(tip);
		}
    }
	@Override
	protected void onDestroy() {
		if(task != null){
            task.cancel(true);
        }
		super.onDestroy();
	}
    
}
