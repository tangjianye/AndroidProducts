package cn.aorise.common.core.module.network;

public abstract class APICallback<T> {
    /**
     * 开始下载
     */
    public abstract void onStart();

    /**
     * 下载发生错误
     *
     * @param e
     */
    public abstract void onError(Throwable e);

    /**
     * 下载完成
     */
    public abstract void onCompleted();

    /**
     * 下载完成
     *
     * @param t
     */
    public abstract void onNext(T t);

    /**
     * 测试数据
     *
     * @param t
     */
    public abstract void onMock(T t);
}
