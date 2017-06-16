package cn.aorise.sample.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.greendao.DaoException;

import java.util.Date;
import java.util.List;

import cn.aorise.common.core.utils.assist.AoriseLog;
import cn.aorise.sample.R;
import cn.aorise.sample.databinding.SampleActivityDbBinding;
import cn.aorise.sample.db.DbHelper;
import cn.aorise.sample.db.SampleNoteDao;
import cn.aorise.sample.db.entity.SampleNote;
import cn.aorise.sample.ui.base.SampleBaseActivity;

public class DbActivity extends SampleBaseActivity implements View.OnClickListener {
    private static final String TAG = DbActivity.class.getSimpleName();
    private static int mCount;
    private SampleActivityDbBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initData() {
        mCount = 0;
    }

    @Override
    protected void initView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.sample_activity_db);
        mBinding.btnAdd.setOnClickListener(this);
        mBinding.btnDelete.setOnClickListener(this);
        mBinding.btnQuery.setOnClickListener(this);
        mBinding.btnModify.setOnClickListener(this);
        mBinding.btnClear.setOnClickListener(this);
        mBinding.btnQueryAll.setOnClickListener(this);
    }

    @Override
    protected void initEvent() {
        print();
    }

    @Override
    public void onClick(View v) {
        SampleNote sampleNote = null;
        int id = v.getId();

        if (R.id.btn_add == id) {
            DbHelper.getInstance().getSampleNoteDao().insert(create(++mCount));
            print();
        } else if (R.id.btn_delete == id) {
            sampleNote = getNote(mCount--);
            DbHelper.getInstance().getSampleNoteDao().delete(sampleNote);
            print();
        } else if (R.id.btn_query == id) {
            // 查找最新的插入记录
            sampleNote = getNote(mCount);
            print(sampleNote);
        } else if (R.id.btn_modify == id) {
            sampleNote = getNote(mCount);
            sampleNote.setComment("这是修改记录");
            DbHelper.getInstance().getSampleNoteDao().update(sampleNote);
            print();
        } else if (R.id.btn_clear == id) {
            mCount = 0;
            DbHelper.getInstance().getSampleNoteDao().deleteAll();
            print();
        } else if (R.id.btn_query_all == id) {
            print();
        } else if (R.id.btn_close == id) {
            // DbHelper.getInstance().close();
        } else {
            AoriseLog.i(TAG, "onClick");
        }
    }

    private SampleNote getNote(int count) {
        SampleNote sampleNote = null;
        try {
            sampleNote = DbHelper.getInstance().getSampleNoteDao()
                    .queryBuilder()
                    .where(SampleNoteDao.Properties.Text.eq(Integer.toString(count)))
                    .build()
                    .uniqueOrThrow();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return sampleNote;
    }

    private void print(SampleNote sampleNote) {
        if (null != sampleNote) {
            mBinding.etStream.setText(sampleNote.toString());
        } else {
            mBinding.etStream.setText("Empty");
        }
    }

    private void print() {
        boolean isOrder = true;
        if (isOrder) {
            printOrder();
        } else {
            printAll();
        }
    }

    /**
     * 普通获取数据库数据直接打印
     */
    private void printAll() {
        List<SampleNote> sampleNoteList = DbHelper.getInstance().getSampleNoteDao().loadAll();
        StringBuilder sb = new StringBuilder();
        for (SampleNote sampleNote : sampleNoteList) {
            AoriseLog.i(TAG, sampleNote.toString());
            sb.append(sampleNote.toString()).append("\r\n");
        }
        mBinding.etStream.setText(sb.toString());
    }

    /**
     * 排序获取数据库数据打印
     */
    private void printOrder() {
        List<SampleNote> sampleNoteList = DbHelper.getInstance().getSampleNoteDao()
                .queryBuilder()
                .orderDesc(SampleNoteDao.Properties.Text)
                .build()
                .list();
        StringBuilder sb = new StringBuilder();
        for (SampleNote sampleNote : sampleNoteList) {
            AoriseLog.i(TAG, sampleNote.toString());
            sb.append(sampleNote.toString()).append("\r\n");
        }
        mBinding.etStream.setText(sb.toString());
    }

    private SampleNote create(int count) {
        SampleNote sampleNote = new SampleNote();
        String str = Integer.toString(count);
        AoriseLog.i(TAG, str);
        sampleNote.setText(str);
        sampleNote.setComment(str + "号记录");
        sampleNote.setDate(new Date());
        return sampleNote;
    }
}
