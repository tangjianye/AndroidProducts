package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/15.
 */

public class RGuideRules {
    private String BH;
    private String BT;
    private String CJSJ;
    private String NR;

    @Override
    public String toString() {
        return "RGuideRules{" +
                "BH='" + BH + '\'' +
                ", BT='" + BT + '\'' +
                ", CJSJ='" + CJSJ + '\'' +
                ", NR='" + NR + '\'' +
                '}';
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }

    public String getBT() {
        return BT;
    }

    public void setBT(String BT) {
        this.BT = BT;
    }

    public String getCJSJ() {
        return CJSJ;
    }

    public void setCJSJ(String CJSJ) {
        this.CJSJ = CJSJ;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }
}
