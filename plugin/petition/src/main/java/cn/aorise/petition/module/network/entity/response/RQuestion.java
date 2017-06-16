package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/5.
 */

public class RQuestion {
    private String MC;
    private String DM;

    @Override
    public String toString() {
        return "RQuestion{" +
                "MC='" + MC + '\'' +
                ", DM=" + DM +
                '}';
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public Object getDM() {
        return DM;
    }

    public void setDM(String DM) {
        this.DM = DM;
    }
}
