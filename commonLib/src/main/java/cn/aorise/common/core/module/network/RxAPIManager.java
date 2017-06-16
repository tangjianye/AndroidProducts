package cn.aorise.common.core.module.network;

import java.util.HashMap;
import java.util.Set;

import rx.Subscription;

/**
 * Created by pc on 2017/3/14.
 */
public class RxAPIManager implements IRxAction<Object> {
    private static RxAPIManager sInstance = null;
    private HashMap<Object, Subscription> maps;

    public static RxAPIManager getInstance() {
        if (sInstance == null) {
            synchronized (RxAPIManager.class) {
                if (sInstance == null) {
                    sInstance = new RxAPIManager();
                }
            }
        }
        return sInstance;
    }

    private RxAPIManager() {
        maps = new HashMap<>();
    }

    @Override
    public void add(Object tag, Subscription subscription) {
        maps.put(tag, subscription);
    }

    @Override
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    @Override
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isUnsubscribed()) {
            maps.get(tag).unsubscribe();
            maps.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
