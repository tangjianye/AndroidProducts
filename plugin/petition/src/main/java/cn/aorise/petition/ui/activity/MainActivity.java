package cn.aorise.petition.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.aorise.common.core.manager.ActivityManager;
import cn.aorise.petition.R;
import cn.aorise.petition.databinding.PetitionActivityMainBinding;
import cn.aorise.petition.ui.adapter.TabPagerAdapter;
import cn.aorise.petition.ui.base.PetitionBaseActivity;
import cn.aorise.petition.ui.fragment.ChatFragment;
import cn.aorise.petition.ui.fragment.HomeFragment;
import cn.aorise.petition.ui.fragment.NoticeFragment;
import cn.aorise.petition.ui.fragment.PersonalFragment;

public class MainActivity extends PetitionBaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    // 时间间隔
    private static final long EXIT_INTERVAL = 2000L;
    // 需要监听几次点击事件数组的长度就为几
    // 如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
    private long[] mHints = new long[2];

    private PetitionActivityMainBinding mBinding;
    private ArrayList<Fragment> mFragments;
    private TabPagerAdapter mAdapter;

    private int[] mTitles = new int[]{R.string.petition_tab_label_home, R.string.petition_tab_label_notice,
            R.string.petition_tab_label_chat, R.string.petition_tab_label_personal};
    private int[] mIcons = new int[]{R.drawable.petition_tab_home_selector, R.drawable.petition_tab_notice_selector,
            R.drawable.petition_tab_chat_selector, R.drawable.petition_tab_personal_selector};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NoticeFragment.newInstance());
        mFragments.add(ChatFragment.newInstance());
        mFragments.add(PersonalFragment.newInstance());
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.petition_activity_main);
        mBinding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        getToolBar().setNavigationIcon(null);
    }

    @Override
    protected void initEvent() {
        mAdapter = new TabPagerAdapter(getSupportFragmentManager(), mFragments);
        mBinding.viewPager.setAdapter(mAdapter);
        mBinding.viewPager.addOnPageChangeListener(new PageChangeListener());
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);

        initTabView();
    }

    private void initTabView() {
        for (int i = 0, size = mBinding.tabLayout.getTabCount(); i < size; i++) {
            TabLayout.Tab tab = mBinding.tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(creatTabView(mTitles[i], mIcons[i]));
            }
        }
    }

    private View creatTabView(@StringRes int title, @DrawableRes int imageResId) {
        View view = LayoutInflater.from(this).inflate(R.layout.petition_content_tab_indicator, null);
        TextView tv = (TextView) view.findViewById(R.id.txt_tips);
        tv.setText(title);
        ImageView image = (ImageView) view.findViewById(R.id.iv_icon);
        image.setImageResource(imageResId);
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 将mHints数组内的所有元素左移一个位置
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            // 获得当前系统已经启动的时间
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            if ((SystemClock.uptimeMillis() - mHints[0]) > EXIT_INTERVAL) {
                showToast(getString(R.string.aorise_label_double_exit));
            } else {
                finish();
                ActivityManager.getInstance().appExit(getApplicationContext());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setToolBarTitle(getString(mTitles[position]));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
