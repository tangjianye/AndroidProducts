package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/22.
 */

public class TVerifyCode {
    private String DHHM;
    private String YZM;

    @Override
    public String toString() {
        return "TVerifyCode{" +
                "DHHM='" + DHHM + '\'' +
                ", YZM='" + YZM + '\'' +
                '}';
    }

    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }

    public String getYZM() {
        return YZM;
    }

    public void setYZM(String YZM) {
        this.YZM = YZM;
    }
}
