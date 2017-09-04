package wzcc.com.helloworld;



import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.wzcc.toollibary_jlj.StringHelper;

import wzcc.com.helloworld.model.ModelUser;

//import cn.jpush.android.api.JPushInterface;
//import com.baidu.mapapi.SDKInitializer;

public class MyApp extends Application{
	// 单例对象
		private static MyApp instance;
		private String ServiceUrl = "http://115.236.161.139:7090";
	    private SharedPreferences _sp; // 保存用户信息
	    
	    public static MyApp getInstance() {
			return instance;
		}
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		 _sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		initImageLoader(getApplicationContext());
//		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
//	    JPushInterface.init(this);     		// 初始化 JPush
//        SDKInitializer.initialize(getApplicationContext());
	}
	
	public  String getServerUrl(){
		if (ServiceUrl.contains("http://")) {
			return ServiceUrl;
		}else 
			return "http://"+ServiceUrl;
	}
	
	public ModelUser getUser(){
		try {
			String json = _sp.getString("jsonUser", "");
			return JSON.parseArray(json, ModelUser.class).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	public void setUser(String json){
		Editor editor = _sp.edit();
		editor.putString("jsonUser", json);
		editor.commit();
	}
	
	public boolean isLoginOut(){
		String pwd = _sp.getString("pwd", "");
		if (StringHelper.isEmpty(pwd)) {
			return true;
		}else
			return false;
	}
	
	public void LoginOut(){
    	Editor editor = _sp.edit();
		editor.putString("jsonUser", "");
		editor.putString("pwd", "");
		editor.commit();
//		offJpushTag();
    }
	
	
//	public  void setJpushTag(String... params){
//	//推送绑定
//	Set<String> tags = new HashSet<String>();
//	for (int i = 0; i < params.length; i++) {
//		tags.add(params[i]);
//	}
//	JPushInterface.setTags(MyApp.getInstance(), tags, new TagAliasCallback() {
//		
//		@Override
//		public void gotResult(int arg0, String arg1, Set<String> arg2) {
//			Log.e("tag", arg0 +" "+ arg1);
//		}
//	});
//}
//public  void setJpushAlias(String alias){
//	//推送绑定
//	JPushInterface.setAlias(MyApp.getInstance(), alias, new TagAliasCallback() {
//		
//		@Override
//		public void gotResult(int arg0, String arg1, Set<String> arg2) {
//			Log.e("tag", arg0 +" "+ arg1);
//		}
//	});
//}

//public void setJpushTagsAndAlias(Context context,Set<String> tags,String alias){
//	JPushInterface.setAliasAndTags(context, alias, tags,new TagAliasCallback() {
//		
//		@Override
//		public void gotResult(int arg0, String arg1, Set<String> arg2) {
//			Log.e("tag", arg0 +" "+ arg1);
//		}
//	});
//}
//
//public void offJpushTag(){
//	//解除绑定
//	Set<String> tags = new HashSet<String>();
//	JPushInterface.setTags(MyApp.getInstance(), tags, null);
//	JPushInterface.setAlias(MyApp.getInstance(), "", null);
//}
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.writeDebugLogs(); // Remove for release app

		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
}
