package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/22.
 */

public class TChangePassword {
    private String ZJHM;
    private String JMM;
    private String XMM;

    @Override
    public String toString() {
        return "TChangePassword{" +
                "ZJHM='" + ZJHM + '\'' +
                ", JMM='" + JMM + '\'' +
                ", XMM='" + XMM + '\'' +
                '}';
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getJMM() {
        return JMM;
    }

    public void setJMM(String JMM) {
        this.JMM = JMM;
    }

    public String getXMM() {
        return XMM;
    }

    public void setXMM(String XMM) {
        this.XMM = XMM;
    }
}
