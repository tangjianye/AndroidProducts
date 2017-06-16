package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/18.
 */

public class TZJHM {
    private String ZJHM;

    @Override
    public String toString() {
        return "TZJHM{" +
                "ZJHM='" + ZJHM + '\'' +
                '}';
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }
}
