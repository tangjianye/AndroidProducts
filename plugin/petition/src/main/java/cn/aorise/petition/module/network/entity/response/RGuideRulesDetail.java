package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/16.
 */

public class RGuideRulesDetail {
    private String BT;
    private String NR;
    private String BH;

    @Override
    public String toString() {
        return "RGuideRulesDetail{" +
                "BT='" + BT + '\'' +
                ", NR='" + NR + '\'' +
                ", BH='" + BH + '\'' +
                '}';
    }

    public String getBT() {
        return BT;
    }

    public void setBT(String BT) {
        this.BT = BT;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }
}
