package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/11.
 */

public class RQueryEvaluate {
    private String BH;
    private String CJSJ;
    private String NR;
    private int PJZT;
    private String PJLX;
    private String PJNR;

    @Override
    public String toString() {
        return "RQueryEvaluate{" +
                "BH='" + BH + '\'' +
                ", CJSJ='" + CJSJ + '\'' +
                ", NR='" + NR + '\'' +
                ", PJZT=" + PJZT +
                ", PJLX='" + PJLX + '\'' +
                ", PJNR='" + PJNR + '\'' +
                '}';
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
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

    public int getPJZT() {
        return PJZT;
    }

    public void setPJZT(int PJZT) {
        this.PJZT = PJZT;
    }

    public String getPJLX() {
        return PJLX;
    }

    public void setPJLX(String PJLX) {
        this.PJLX = PJLX;
    }

    public String getPJNR() {
        return PJNR;
    }

    public void setPJNR(String PJNR) {
        this.PJNR = PJNR;
    }
}
