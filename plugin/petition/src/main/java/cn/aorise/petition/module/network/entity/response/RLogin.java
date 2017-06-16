package cn.aorise.petition.module.network.entity.response;

/**
 * Created by tangjy on 2017/4/27.
 */

public class RLogin {
    /**
     * XM : 奥昇
     * XB : null
     * ZJLX : null
     * ZJH : null
     * DHHM : null
     * CSRQ : null
     * SZDQ : null
     * XXDZ : null
     */
    private String XM;
    private Object XB;
    private Object ZJLX;
    private Object ZJHM;
    private Object DHHM;
    private Object CSRQ;
    private Object SZDQ;
    private Object XXDZ;

    public String getXM() {
        return XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public Object getXB() {
        return XB;
    }

    public void setXB(Object XB) {
        this.XB = XB;
    }

    public Object getZJLX() {
        return ZJLX;
    }

    public void setZJLX(Object ZJLX) {
        this.ZJLX = ZJLX;
    }

    public Object getZJH() {
        return ZJHM;
    }

    public void setZJH(Object ZJH) {
        this.ZJHM = ZJH;
    }

    public Object getDHHM() {
        return DHHM;
    }

    public void setDHHM(Object DHHM) {
        this.DHHM = DHHM;
    }

    public Object getCSRQ() {
        return CSRQ;
    }

    public void setCSRQ(Object CSRQ) {
        this.CSRQ = CSRQ;
    }

    public Object getSZDQ() {
        return SZDQ;
    }

    public void setSZDQ(Object SZDQ) {
        this.SZDQ = SZDQ;
    }

    public Object getXXDZ() {
        return XXDZ;
    }

    public void setXXDZ(Object XXDZ) {
        this.XXDZ = XXDZ;
    }

    @Override
    public String toString() {
        return "RLogin{" +
                "XM='" + XM + '\'' +
                ", XB=" + XB +
                ", ZJLX=" + ZJLX +
                ", ZJH=" + ZJHM +
                ", DHHM=" + DHHM +
                ", CSRQ=" + CSRQ +
                ", SZDQ=" + SZDQ +
                ", XXDZ=" + XXDZ +
                '}';
    }
}
