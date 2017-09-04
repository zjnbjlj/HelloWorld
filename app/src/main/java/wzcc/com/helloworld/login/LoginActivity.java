package wzcc.com.helloworld.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.wzcc.common.AsyncActivity;
import com.wzcc.toollibary_jlj.StringHelper;
import com.wzcc.toollibary_jlj.UIHelper;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import wzcc.com.helloworld.CommonString;
import wzcc.com.helloworld.MainActivity;
import wzcc.com.helloworld.MyApp;
import wzcc.com.helloworld.R;
import wzcc.com.helloworld.model.ModelData;
import wzcc.com.helloworld.model.ModelResult;
import wzcc.com.helloworld.model.ModelUser;

public class LoginActivity extends AsyncActivity {

    private CheckBox _cbShowPwd;
    private EditText _etUser, _etPwd;
    private SharedPreferences _sp;
    private TextView _btnLogin;
    private static final String TAG = "JPush";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		immerseLayout();
        setView();
        loadPageData();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    private void setView() {
        _cbShowPwd = (CheckBox) findViewById(R.id.cb_showpwd);
        _etPwd = (EditText) findViewById(R.id.et_pwd);
        _etUser = (EditText) findViewById(R.id.et_phone);
        _btnLogin = (TextView) findViewById(R.id.btn_login);
        _cbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // 显示密码
                    _etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // 隐藏密码
                    _etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        View.OnKeyListener listener = new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    _btnLogin.performClick();
                    return true;
                }
                return false;
            }
        };
        _etPwd.setOnKeyListener(listener);
        _btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                String username = _etUser.getText().toString().trim();
//                String password = _etPwd.getText().toString().trim();
//                if (StringHelper.isEmpty(username)) {
//                    UIHelper.showToast(LoginActivity.this, "请输入用户名");
//                } else if (StringHelper.isEmpty(password)) {
//                    UIHelper.showToast(LoginActivity.this, "请输入密码");
//                } else {
//                    LoginActivity.this.runAsyncTask();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
//                }
            }
        });
    }

    // ------------------------------------------------
    // 界面数据
    // ------------------------------------------------
    /** 加载界面数据 */
    public void loadPageData() {
        _sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String username = _sp.getString("username", "");
        String pwd = _sp.getString("pwd", "");
        _etUser.setText(username);
        _etPwd.setText(pwd);
    }

    /** 保存界面数据 */
    public void savePageData() {
        String username = _etUser.getText().toString().trim();
        String pwd = _etPwd.getText().toString().trim();
        SharedPreferences.Editor editor = _sp.edit();
        editor.putString("username", username);
        editor.putString("pwd", pwd);
        editor.commit();
    }
    OkHttpClient client = new OkHttpClient.Builder().authenticator(new Authenticator() {
        @Override public Request authenticate(Route route, Response response) throws IOException {
            System.out.println("Authenticating for response: " + response);
            System.out.println("Challenges: " + response.challenges());
            String credential = Credentials.basic("user", "password");
            return response.request().newBuilder()
                    .header("Authorization", credential)
                    .build();
        }
    })
            .build();
    @Override
    protected String asyncGetData(String... params) throws Exception {
        //?phone=18317897552&password=a
        String phone = _etUser.getText().toString().trim();
        String password = _etPwd.getText().toString().trim();
        String url = new CommonString().LoginUrl;
        RequestBody body = new FormBody.Builder().add("phone",phone).add("password",password).build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    protected void onPostAsyncTask(String result) throws Exception {
        if (StringHelper.isEmpty(result)) {
            UIHelper.showToast(this, "服务器连接失败");
            return;
        }
        Log.e("LoginActivity",result);
        try {
            ModelResult modelResult = JSON.parseObject(result, ModelResult.class);
            if (modelResult.success) {
                ModelData modelData = JSON.parseObject(modelResult.data, ModelData.class);
                MyApp.getInstance().setUser(modelData.data);
                String type = getIntent().getStringExtra("type");
                String extras = getIntent().getStringExtra("extras");
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                if (!StringHelper.isEmpty(type)) {
                    if (type.equals("jpush")) {//推送启动 传递相应的推送参数
                        intent.putExtra("type", type);
                        intent.putExtra("extras", extras);
                        startActivity(intent);
                    }else if (type.equals("relogin")) {//else如果type为relogin则不启动main
                        LoginActivity.this.setResult(1);
                    }
                }else { //正常打开登录启动main
                    startActivity(intent);
                }
                LoginActivity.this.finish();
                savePageData();
                ModelUser user = MyApp.getInstance().getUser();
//                setTag(user.userId);
                UIHelper.showToast(this, "登录成功");
            }else
                UIHelper.showToast(this, modelResult.msg);
        } catch (Exception e) {
            UIHelper.showToast(this, "解析异常");
            e.printStackTrace();
        }
    }


//    private static final int MSG_SET_ALIAS = 1001;
//    private static final int MSG_SET_TAGS = 1002;
//
//
//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(android.os.Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case MSG_SET_ALIAS:
//                    Logger.d(TAG, "Set alias in handler.");
//                    JPushInterface.setAliasAndTags(getApplicationContext(), (String) msg.obj, null, mAliasCallback);
//                    break;
//
//                case MSG_SET_TAGS:
//                    Logger.d(TAG, "Set tags in handler.");
//                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
//                    break;
//
//                default:
//                    Logger.i(TAG, "Unhandled msg - " + msg.what);
//            }
//        }
//    };
//
//    private void setTag(String tag) {
//        // 检查 tag 的有效性
//        if (StringHelper.isEmpty(tag)) {
//            Toast.makeText(this, R.string.error_tag_empty,Toast.LENGTH_SHORT).show();
//            return;
//        }
//        // ","隔开的多个 转换成 Set
//        String[] sArray = tag.split(",");
//        Set<String> tagSet = new LinkedHashSet<String>();
//        for (String sTagItme : sArray) {
//            if (!ExampleUtil.isValidTagAndAlias(sTagItme)) {
//                Toast.makeText(this, R.string.error_tag_empty, Toast.LENGTH_SHORT).show();
//                return;
//            }
//            tagSet.add(sTagItme);
//        }
//        //调用JPush API设置Tag
//        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));
//    }
//
//    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
//
//        @Override
//        public void gotResult(int code, String alias, Set<String> tags) {
//            String logs;
//            switch (code) {
//                case 0:
//                    logs = "Set tag and alias success";
//                    Logger.i(TAG, logs);
//                    break;
//
//                case 6002:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    Logger.i(TAG, logs);
//                    if (ExampleUtil.isConnected(getApplicationContext())) {
//                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
//                    } else {
//                        Logger.i(TAG, "No network");
//                    }
//                    ExampleUtil.showToast(logs, getApplicationContext());
//                    break;
//
//                default:
//                    logs = "Failed with errorCode = " + code;
//                    Logger.e(TAG, logs);
//                    ExampleUtil.showToast(logs, getApplicationContext());
//                    break;
//            }
//
//        }
//    };
//
//    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {
//
//        @Override
//        public void gotResult(int code, String alias, Set<String> tags) {
//            String logs;
//            switch (code) {
//                case 0:
//                    logs = "Set tag and alias success";
//                    Logger.i(TAG, logs);
//                    break;
//
//                case 6002:
//                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
//                    Logger.i(TAG, logs);
//                    if (ExampleUtil.isConnected(getApplicationContext())) {
//                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
//                    } else {
//                        Logger.i(TAG, "No network");
//                    }
//                    ExampleUtil.showToast(logs, getApplicationContext());
//                    break;
//
//                default:
//                    logs = "Failed with errorCode = " + code;
//                    Logger.e(TAG, logs);
//                    ExampleUtil.showToast(logs, getApplicationContext());
//                    break;
//            }
//
//        }
//
//    };
}
