package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/23.
 */

public class TFoundPassword {
    private String DHHM;
    private String XMM;

    @Override
    public String toString() {
        return "TFoundPassword{" +
                "DHHM='" + DHHM + '\'' +
                ", XMM='" + XMM + '\'' +
                '}';
    }


    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }

    public String getXMM() {
        return XMM;
    }

    public void setXMM(String XMM) {
        this.XMM = XMM;
    }
}
