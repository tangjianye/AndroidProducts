package cn.aorise.petition.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.aorise.common.core.ui.base.BaseActivity;
import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.petition.R;


/**
 * Created by Administrator on 2016/3/14 0014.
 */
public abstract class PetitionBaseActivity extends BaseActivity {
    private static final String TAG = PetitionBaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoadingDialog() {
        super.showLoadingDialog();
        AoriseLog.i(TAG, "showLoadingDialog");
    }

    @Override
    public void dismissLoadingDialog() {
        super.dismissLoadingDialog();
        AoriseLog.i(TAG, "dismissLoadingDialog");
    }
    //--------------------沉浸式状态栏------------------
    public static void setColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }

    }
    /** * 生成一个和状态栏大小相同的矩形条 * * @param activity 需要设置的activity * @param color 状态栏颜色值 * @return 状态栏矩形条 */
    private static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
    /** * 使状态栏透明 * <p> * 适用于图片作为背景的界面,此时需要图片填充到状态栏 * * @param activity 需要设置的activity */
    public static void setTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }
    public  void setPetition_title(final Activity activity, String midText, String rightText, final Class<?> targetClass){
        TextView txtTitle= (TextView) findViewById(R.id.txt_title);
        TextView txtRight= (TextView) findViewById(R.id.txt_right);
        ImageView imgReturn= (ImageView) findViewById(R.id.img_return);
        RelativeLayout rl_petition_return= (RelativeLayout) findViewById(R.id.rl_petition_return);
        RelativeLayout rl_petition_right= (RelativeLayout) findViewById(R.id.rl_petition_right);
        if (null != txtTitle && !TextUtils.isEmpty(midText)) {
            txtTitle.setText(midText);
        }
        if (null != txtRight && !TextUtils.isEmpty(rightText)) {
            txtRight.setText(rightText);
        }
        rl_petition_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        rl_petition_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,targetClass);
                startActivity(intent);
            }
        });
    }
    public  void setPetition_title(final Activity activity, String midText, String rightText){
        TextView txtTitle= (TextView) findViewById(R.id.txt_title);
        TextView txtRight= (TextView) findViewById(R.id.txt_right);
        ImageView imgReturn= (ImageView) findViewById(R.id.img_return);
        RelativeLayout rl_petition_return= (RelativeLayout) findViewById(R.id.rl_petition_return);
        RelativeLayout rl_petition_right= (RelativeLayout) findViewById(R.id.rl_petition_right);
        if (null != txtTitle && !TextUtils.isEmpty(midText)) {
            txtTitle.setText(midText);
        }
        if (null != txtRight && !TextUtils.isEmpty(rightText)) {
            txtRight.setText(rightText);
        }
        rl_petition_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        txtRight.setVisibility(View.INVISIBLE);
    }
    //----------------------------隐藏软键盘-----------------------------------
    //当有edittext时 点击空白区域即可关闭软键盘， 集成即可  不用调用方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
