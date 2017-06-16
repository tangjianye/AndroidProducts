package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/15.
 */

public class TVerificationCode {
    private String DHHM;
    //private String LX;


    @Override
    public String toString() {
        return "TVerificationCode{" +
                "DHHM='" + DHHM + '\'' +
                '}';
    }

    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }
}
