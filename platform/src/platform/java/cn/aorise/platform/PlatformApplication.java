package cn.aorise.platform;

import android.util.Log;

import cn.aorise.common.BaseApplication;
import cn.aorise.common.core.manager.AppManager;
//import cn.aorise.education.EducationApplication;
//import cn.aorise.hospital.HospitalApplication;
import cn.aorise.petition.PetitionApplication;
import cn.aorise.sample.SampleApplication;

public class PlatformApplication extends BaseApplication {
    private static final String TAG = PlatformApplication.class.getSimpleName();
//    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        AppManager.getInstance().add(new SampleApplication());
        //AppManager.getInstance().add(new EducationApplication());
        //AppManager.getInstance().add(new HospitalApplication());
        AppManager.getInstance().add(new PetitionApplication());
        super.onCreate();
        Log.i(TAG, "onCreate");
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        mRefWatcher = LeakCanary.install(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

//    public static RefWatcher getRefWatcher(Context context) {
//        PlatformApplication application = (PlatformApplication) context.getApplicationContext();
//        return application.mRefWatcher;
//    }
}
