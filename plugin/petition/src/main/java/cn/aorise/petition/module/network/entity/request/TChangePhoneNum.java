package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/22.
 */

public class TChangePhoneNum {
    private String ZJHM;
    private String XSJH;

    @Override
    public String toString() {
        return "TChangePhoneNum{" +
                "ZJHM='" + ZJHM + '\'' +
                ", XSJH='" + XSJH + '\'' +
                '}';
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getXSJH() {
        return XSJH;
    }

    public void setXSJH(String XSJH) {
        this.XSJH = XSJH;
    }
}
