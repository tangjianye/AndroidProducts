package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/10.
 */

public class RSuggestCollect {
    private String BH;
    private String CJSJ;
    private String NR;
    private String HFNR;
    private int HFZT;

    @Override
    public String toString() {
        return "RSuggestCollect{" +
                "BH='" + BH + '\'' +
                ", CJSJ='" + CJSJ + '\'' +
                ", NR='" + NR + '\'' +
                ", HFNR='" + HFNR + '\'' +
                ", HFZT=" + HFZT +
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

    public String getHFNR() {
        return HFNR;
    }

    public void setHFNR(String HFNR) {
        this.HFNR = HFNR;
    }

    public int getHFZT() {
        return HFZT;
    }

    public void setHFZT(int HFZT) {
        this.HFZT = HFZT;
    }
}
