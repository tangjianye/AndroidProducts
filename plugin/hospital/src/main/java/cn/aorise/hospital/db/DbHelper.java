package cn.aorise.hospital.db;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import cn.aorise.common.core.db.DBManager;
import cn.aorise.hospital.config.Constant;
import cn.aorise.hospital.db.entity.HospitalNote;

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * 对应的getAbstractDao方法就行。
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 17/1/18 23:18.
 */
public class DbHelper {
    private static final String DB_NAME = Constant.DB_NAME;
    private static DbHelper sInstance;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private static DBManager<HospitalNote, Long> mHospitalNoteDao;

    private DbHelper() {
    }

    public static DbHelper getInstance() {
        if (sInstance == null) {
            synchronized (DbHelper.class) {
                if (sInstance == null) {
                    sInstance = new DbHelper();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        init(context, DB_NAME);
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    private void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public DBManager<HospitalNote, Long> getHospitalNoteDao() {
        if (mHospitalNoteDao == null) {
            mHospitalNoteDao = new DBManager<HospitalNote, Long>() {
                @Override
                public AbstractDao<HospitalNote, Long> getAbstractDao() {
                    return mDaoSession.getHospitalNoteDao();
                }
            };
        }
        return mHospitalNoteDao;
    }
}
