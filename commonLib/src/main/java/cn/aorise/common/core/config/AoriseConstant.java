package cn.aorise.common.core.config;

/**
 * Created by tangjy on 2015/11/3 0003.
 */
public class AoriseConstant {
    /**
     * 数据库名字
     */
    // public static final String DATABASE_NAME = "aorise.db";

    /**
     * 渠道标志
     */
    public static final String CHANNEL = "UMENG_CHANNEL";

    /**
     * 百度PUSH
     */
    public static final String PUSH_KEY = "PUSH_KEY";

    /**
     * T卡文件夹
     */
    public static class Folder {
        // 根目录
        public static final String LOG_PATH = "log";
        public static final String CACHE_PATH = "cache";
        public static final String DOWNLOAD_PATH = "download";
    }

    /**
     * 透传关键字
     */
    public static class TransportKey {
        // 导航页
        public static final String GUIDE_KEY = "guide_key";
        // intent关键字
        public static final String INTENT_KEY = "intent_key";
        // bundle关键字
        public static final String BUNDLE_KEY = "bundle_key";
        // URL
        public static final String BUNDLE_URL = "bundle_url_key";
        // 标题
        public static final String BUNDLE_TITLE = "bundle_title";
    }

    /**
     * 广播action
     */
    public static class BroadcastKey {
        // 登录后的用户信息
        public static final String CN_AORISE_PLATFORM_LOGIN_ACCOUNT = "cn.aorise.platform.login.ACCOUNT";
    }

    /**
     * 用户信息
     */
    public static class AccountKey {
        public static final String USER_ACCOUNT = "user_account";
        public static final String USER_ID = "user_id";
        public static final String USER_SEX = "user_sex";
    }
}
