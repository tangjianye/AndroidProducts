package cn.aorise.petition.ui.bean;

/**
 * Created by Administrator on 2017/5/12.
 */

public class QueryEvaluatePingjiaInfo {
    private String BMZT;
    private String BMJG;
    private String BMBZ;
    private String SXZT;
    private String SXJG;
    private String SXBZ;

    @Override
    public String toString() {
        return "QueryEvaluatePingjiaInfo{" +
                "BMZT='" + BMZT + '\'' +
                ", BMJG='" + BMJG + '\'' +
                ", BMBZ='" + BMBZ + '\'' +
                ", SXZT='" + SXZT + '\'' +
                ", SXJG='" + SXJG + '\'' +
                ", SXBZ='" + SXBZ + '\'' +
                '}';
    }

    public String getBMZT() {
        return BMZT;
    }

    public void setBMZT(String BMZT) {
        this.BMZT = BMZT;
    }

    public String getBMJG() {
        return BMJG;
    }

    public void setBMJG(String BMJG) {
        this.BMJG = BMJG;
    }

    public String getBMBZ() {
        return BMBZ;
    }

    public void setBMBZ(String BMBZ) {
        this.BMBZ = BMBZ;
    }

    public String getSXZT() {
        return SXZT;
    }

    public void setSXZT(String SXZT) {
        this.SXZT = SXZT;
    }

    public String getSXJG() {
        return SXJG;
    }

    public void setSXJG(String SXJG) {
        this.SXJG = SXJG;
    }

    public String getSXBZ() {
        return SXBZ;
    }

    public void setSXBZ(String SXBZ) {
        this.SXBZ = SXBZ;
    }
}
