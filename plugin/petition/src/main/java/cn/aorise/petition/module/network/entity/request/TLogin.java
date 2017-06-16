package cn.aorise.petition.module.network.entity.request;

/**
 * Created by tangjy on 2017/4/27.
 */

public class TLogin {
    /**
     * YHM : 13988888888
     * MM : 123456
     */
    private String YHM;
    private String MM;

    public String getYHM() {
        return YHM;
    }

    public void setYHM(String YHM) {
        this.YHM = YHM;
    }

    public String getMM() {
        return MM;
    }

    public void setMM(String MM) {
        this.MM = MM;
    }

    @Override
    public String toString() {
        return "TLogin{" +
                "YHM='" + YHM + '\'' +
                ", MM='" + MM + '\'' +
                '}';
    }
}
