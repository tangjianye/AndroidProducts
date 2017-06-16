package cn.aorise.common.core.utils.assist;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Gson单例操作
 */
public class GsonUtil {
    private static Gson sGson = new Gson();

    private GsonUtil() {
        throw new AssertionError();
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return sGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return sGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        } catch (JsonIOException e) {
            AoriseLog.e(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        return sGson.toJson(object);
    }
}
