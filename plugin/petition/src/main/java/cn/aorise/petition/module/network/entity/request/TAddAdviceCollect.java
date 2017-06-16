package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/11.
 */

public class TAddAdviceCollect {
    private String JYLXY ;
    private String JYLXYDM ;
    private String JYLXE ;
    private String JYLXEDM ;
    private String JYLXS ;
    private String JYLXSDM ;
    private String JYRZJH ;
    private String NR ;
    private String SFGK ;
    private String FJ;

    @Override
    public String toString() {
        return "TAddAdviceCollect{" +
                "JYLXY='" + JYLXY + '\'' +
                ", JYLXYDM='" + JYLXYDM + '\'' +
                ", JYLXE='" + JYLXE + '\'' +
                ", JYLXEDM='" + JYLXEDM + '\'' +
                ", JYLXS='" + JYLXS + '\'' +
                ", JYLXSDM='" + JYLXSDM + '\'' +
                ", JYRZJH='" + JYRZJH + '\'' +
                ", NR='" + NR + '\'' +
                ", SFGK='" + SFGK + '\'' +
                ", FJ='" + FJ + '\'' +
                '}';
    }

    public String getJYLXY() {
        return JYLXY;
    }

    public void setJYLXY(String JYLXY) {
        this.JYLXY = JYLXY;
    }

    public String getJYLXYDM() {
        return JYLXYDM;
    }

    public void setJYLXYDM(String JYLXYDM) {
        this.JYLXYDM = JYLXYDM;
    }

    public String getJYLXE() {
        return JYLXE;
    }

    public void setJYLXE(String JYLXE) {
        this.JYLXE = JYLXE;
    }

    public String getJYLXEDM() {
        return JYLXEDM;
    }

    public void setJYLXEDM(String JYLXEDM) {
        this.JYLXEDM = JYLXEDM;
    }

    public String getJYLXS() {
        return JYLXS;
    }

    public void setJYLXS(String JYLXS) {
        this.JYLXS = JYLXS;
    }

    public String getJYLXSDM() {
        return JYLXSDM;
    }

    public void setJYLXSDM(String JYLXSDM) {
        this.JYLXSDM = JYLXSDM;
    }

    public String getJYRZJH() {
        return JYRZJH;
    }

    public void setJYRZJH(String JYRZJH) {
        this.JYRZJH = JYRZJH;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public String getSFGK() {
        return SFGK;
    }

    public void setSFGK(String SFGK) {
        this.SFGK = SFGK;
    }

    public String getFJ() {
        return FJ;
    }

    public void setFJ(String FJ) {
        this.FJ = FJ;
    }
}
