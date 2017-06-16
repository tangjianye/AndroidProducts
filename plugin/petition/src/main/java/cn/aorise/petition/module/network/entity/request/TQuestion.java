package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/5.
 */

public class TQuestion {
    private String JB;
    private String DM;

    @Override
    public String toString() {
        return "TQuestion{" +
                "JB='" + JB + '\'' +
                ", DM=" + DM +
                '}';
    }

    public String getJB() {
        return JB;
    }

    public void setJB(String JB) {
        this.JB = JB;
    }

    public String getDM() {
        return DM;
    }

    public void setDM(String DM) {
        this.DM = DM;
    }
}
