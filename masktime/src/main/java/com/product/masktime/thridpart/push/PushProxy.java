package com.product.masktime.thridpart.push;

/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class PushProxy {
    private static PushProxy ourInstance = new PushProxy();

    public static PushProxy getInstance() {
        return ourInstance;
    }

    private PushProxy() {
    }


}
