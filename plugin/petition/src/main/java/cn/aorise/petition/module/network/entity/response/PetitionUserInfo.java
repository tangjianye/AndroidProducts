package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/4/27.
 */

public class PetitionUserInfo {
    private String YHM;
    private String MM;
    private String XM;
    private String XB;
    private String ZJLX;
    private String ZJH;
    private String DHHM;
    private String CSRQ;
    private String SZDQ;
    private String XXDZ;

    @Override
    public String toString() {
        return "PetitionUserInfo{" +
                "YHM='" + YHM + '\'' +
                ", MM='" + MM + '\'' +
                ", XM='" + XM + '\'' +
                ", XB='" + XB + '\'' +
                ", ZJLX='" + ZJLX + '\'' +
                ", ZJH='" + ZJH + '\'' +
                ", DHHM='" + DHHM + '\'' +
                ", CSRQ='" + CSRQ + '\'' +
                ", SZDQ='" + SZDQ + '\'' +
                ", XXDZ='" + XXDZ + '\'' +
                '}';
    }

    public String getYHM() {
        return YHM;
    }

    public void setYHM(String YHM) {
        this.YHM = YHM;
    }

    public String getMM() {
        return MM;
    }

    public void setMM(String MM) {
        this.MM = MM;
    }

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public String getXB() {
        return XB;
    }

    public void setXB(String XB) {
        this.XB = XB;
    }

    public String getZJLX() {
        return ZJLX;
    }

    public void setZJLX(String ZJLX) {
        this.ZJLX = ZJLX;
    }

    public String getZJH() {
        return ZJH;
    }

    public void setZJH(String ZJH) {
        this.ZJH = ZJH;
    }

    public String getDHHM() {
        return DHHM;
    }

    public void setDHHM(String DHHM) {
        this.DHHM = DHHM;
    }

    public String getCSRQ() {
        return CSRQ;
    }

    public void setCSRQ(String CSRQ) {
        this.CSRQ = CSRQ;
    }

    public String getSZDQ() {
        return SZDQ;
    }

    public void setSZDQ(String SZDQ) {
        this.SZDQ = SZDQ;
    }

    public String getXXDZ() {
        return XXDZ;
    }

    public void setXXDZ(String XXDZ) {
        this.XXDZ = XXDZ;
    }
}
