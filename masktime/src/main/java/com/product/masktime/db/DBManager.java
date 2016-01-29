package com.product.masktime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.product.masktime.BaseApplication;
import com.product.masktime.config.GlobalSetting;
import com.product.masktime.db.gen.DaoMaster;
import com.product.masktime.db.gen.DaoSession;
import com.product.masktime.db.gen.NoteDao;
import com.product.masktime.db.gen.RecordDao;

/**
 * DBManager 数据库统一管理类<br> 负责数据库表的统一创建，获取和销毁的动作。不负责具体表的添删改查操作。
 * Created by tangjy on 2015/3/2.
 */
public class DBManager {
    private static final String TAG = DBManager.class.getSimpleName();

    private static Context sCtx;
    private static DBManager sINSTANTCE;
    private SQLiteDatabase mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private NoteDao mNoteDao;
    private RecordDao mRecordDao;

    private DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (sINSTANTCE == null) {
            sINSTANTCE = new DBManager();
        }
        return sINSTANTCE;
    }

    public void init(Context context) {
        if (!(context instanceof BaseApplication)) {
            throw new AssertionError();
        }
        sCtx = context;

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(sCtx, GlobalSetting.DATABASE_NAME, null);
        mDb = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();

        mNoteDao = mDaoSession.getNoteDao();
        mRecordDao = mDaoSession.getRecordDao();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }

    public NoteDao getNoteDao() {
        return mNoteDao;
    }

    public RecordDao getRecordDao() {
        return mRecordDao;
    }

    public void deleteAll() {
        mNoteDao.deleteAll();
        mRecordDao.deleteAll();
    }
}
