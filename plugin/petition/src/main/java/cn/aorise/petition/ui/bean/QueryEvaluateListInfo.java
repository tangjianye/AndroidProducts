package cn.aorise.petition.ui.bean;

/**
 * Created by Administrator on 2017/5/11.
 */

public class QueryEvaluateListInfo {
    private String JBJG;
    private String JBRY;
    private String CZSJ;
    private String BLSC;
    private String CZLX;

    @Override
    public String toString() {
        return "QueryEvaluateListInfo{" +
                "JBJG='" + JBJG + '\'' +
                ", JBRY='" + JBRY + '\'' +
                ", CZSJ='" + CZSJ + '\'' +
                ", BLSC='" + BLSC + '\'' +
                ", CZLX='" + CZLX + '\'' +
                '}';
    }

    public String getJBJG() {
        return JBJG;
    }

    public void setJBJG(String JBJG) {
        this.JBJG = JBJG;
    }

    public String getJBRY() {
        return JBRY;
    }

    public void setJBRY(String JBRY) {
        this.JBRY = JBRY;
    }

    public String getCZSJ() {
        return CZSJ;
    }

    public void setCZSJ(String CZSJ) {
        this.CZSJ = CZSJ;
    }

    public String getBLSC() {
        return BLSC;
    }

    public void setBLSC(String BLSC) {
        this.BLSC = BLSC;
    }

    public String getCZLX() {
        return CZLX;
    }

    public void setCZLX(String CZLX) {
        this.CZLX = CZLX;
    }
}
