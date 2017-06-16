package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/19.
 */

public class TVerifyPassword {
    private String ZJHM;
    private String MM;

    @Override
    public String toString() {
        return "TVerifyPassword{" +
                "ZJHM='" + ZJHM + '\'' +
                ", MM='" + MM + '\'' +
                '}';
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getMM() {
        return MM;
    }

    public void setMM(String MM) {
        this.MM = MM;
    }
}
