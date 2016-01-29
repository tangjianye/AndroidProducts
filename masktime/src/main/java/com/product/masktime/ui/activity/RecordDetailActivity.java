package com.product.masktime.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.product.common.utils.LogUtils;
import com.product.common.utils.TimeUtils;
import com.product.masktime.R;
import com.product.masktime.common.Constants;
import com.product.masktime.common.interfaces.IInit;
import com.product.masktime.db.DBRecordHelper;
import com.product.masktime.db.gen.Record;
import com.product.masktime.ui.base.BaseTitleActivity;
import com.product.masktime.utils.CommonUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordDetailActivity extends BaseTitleActivity implements IInit {
    private static final String TAG = RecordDetailActivity.class.getSimpleName();
    private Record mRecord;

    @Bind(R.id.txt_record_title)
    TextView mTxtRecordTitle;
    @Bind(R.id.txt_record_date)
    TextView mTxtRecordDate;
    @Bind(R.id.txt_record_content)
    TextView mTxtRecordContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        LogUtils.i(TAG, "onCreate");
        initDatas();
        initTitles();
        initViews();
        initEvents();
    }

    @Override
    protected void setContentLayer() {
        setContentView(R.layout.activity_record_detail);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void initDatas() {
        mRecord = (Record) CommonUtils.getMaskSerializable(getIntent());
    }

    @Override
    public void initTitles() {
        if (null != mRecord) {
            mTitleTips.setText(TimeUtils.getTime(mRecord.getDate(), TimeUtils.DATE_FORMAT_DAY));
        } else {
            mTitleTips.setText(TimeUtils.getCurrentTimeInString(TimeUtils.DATE_FORMAT_DAY));
        }

        mTitleMore.setVisibility(View.VISIBLE);
        // mTitleMore.setImageResource(R.drawable.btn_plan);
        mTitleMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityForResult(RecordingActivity.class,
                        Constants.COMMON_REQUEST_CODE, CommonUtils.getMaskBundle(mRecord));
            }
        });
    }

    @Override
    public void initViews() {
        if (null != mRecord) {
            mTxtRecordTitle.setText(mRecord.getTitle());
            mTxtRecordDate.setText(TimeUtils.getTime(mRecord.getDate(), TimeUtils.DEFAULT_DATE_FORMAT));
            mTxtRecordContent.setText(mRecord.getContent());
        }
    }

    @Override
    public void initEvents() {
//        findViewById(R.id.btn_submit).setOnClickListener(this);
    }

    private void refresh() {
        LogUtils.i(TAG, "refresh");
        mRecord = DBRecordHelper.getInstance().load(DBRecordHelper.getInstance().getKey(mRecord));

        initTitles();
        initViews();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.i(TAG, "onActivityResult requestCode = " + requestCode + " ;resultCode = " + resultCode);
        if (Activity.RESULT_OK == resultCode) {
            if (Constants.COMMON_REQUEST_CODE == requestCode) {
                refresh();
                setResult(Activity.RESULT_OK);
            }
        }
    }
}
