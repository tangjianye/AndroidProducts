package cn.aorise.petition.module.network.entity.request;

import java.util.List;

import cn.aorise.petition.ui.bean.Petition_contact_people;

/**
 * Created by Administrator on 2017/5/9.
 */

public class TSubmit {
    private String XFR;
    private String ZJHM;
    private String SFD_SHDM;
    private String SFD_SH;
    private String SFD_SDM;
    private String SFD_S;
    private String SFD_XDM;
    private String SFD_X;
    private String LXY;
    private String LXYDM;
    private String LXE;
    private String LXEDM;
    private String LXS;
    private String LXSDM;
    private String SFFH;
    private String FYSFSL;
    private String SFXZFY;
    private String XZJGSFSL;
    private String SFGK;
    private String NR;
    private String FJ;
    private List<Petition_contact_people> peoplAll;

    @Override
    public String toString() {
        return "TSubmit{" +
                "XFR='" + XFR + '\'' +
                ", ZJHM='" + ZJHM + '\'' +
                ", SFD_SHDM='" + SFD_SHDM + '\'' +
                ", SFD_SH='" + SFD_SH + '\'' +
                ", SFD_SDM='" + SFD_SDM + '\'' +
                ", SFD_S='" + SFD_S + '\'' +
                ", SFD_XDM='" + SFD_XDM + '\'' +
                ", SFD_X='" + SFD_X + '\'' +
                ", LXY='" + LXY + '\'' +
                ", LXYDM='" + LXYDM + '\'' +
                ", LXE='" + LXE + '\'' +
                ", LXEDM='" + LXEDM + '\'' +
                ", LXS='" + LXS + '\'' +
                ", LXSDM='" + LXSDM + '\'' +
                ", SFFH='" + SFFH + '\'' +
                ", FYSFSL='" + FYSFSL + '\'' +
                ", SFXZFY='" + SFXZFY + '\'' +
                ", XZJGSFSL='" + XZJGSFSL + '\'' +
                ", SFGK='" + SFGK + '\'' +
                ", NR='" + NR + '\'' +
                ", FJ='" + FJ + '\'' +
                ", peoplAll=" + peoplAll +
                '}';
    }

    public String getXFR() {
        return XFR;
    }

    public void setXFR(String XFR) {
        this.XFR = XFR;
    }

    public String getZJHM() {
        return ZJHM;
    }

    public void setZJHM(String ZJHM) {
        this.ZJHM = ZJHM;
    }

    public String getSFD_SHDM() {
        return SFD_SHDM;
    }

    public void setSFD_SHDM(String SFD_SHDM) {
        this.SFD_SHDM = SFD_SHDM;
    }

    public String getSFD_SH() {
        return SFD_SH;
    }

    public void setSFD_SH(String SFD_SH) {
        this.SFD_SH = SFD_SH;
    }

    public String getSFD_SDM() {
        return SFD_SDM;
    }

    public void setSFD_SDM(String SFD_SDM) {
        this.SFD_SDM = SFD_SDM;
    }

    public String getSFD_S() {
        return SFD_S;
    }

    public void setSFD_S(String SFD_S) {
        this.SFD_S = SFD_S;
    }

    public String getSFD_XDM() {
        return SFD_XDM;
    }

    public void setSFD_XDM(String SFD_XDM) {
        this.SFD_XDM = SFD_XDM;
    }

    public String getSFD_X() {
        return SFD_X;
    }

    public void setSFD_X(String SFD_X) {
        this.SFD_X = SFD_X;
    }

    public String getLXY() {
        return LXY;
    }

    public void setLXY(String LXY) {
        this.LXY = LXY;
    }

    public String getLXYDM() {
        return LXYDM;
    }

    public void setLXYDM(String LXYDM) {
        this.LXYDM = LXYDM;
    }

    public String getLXE() {
        return LXE;
    }

    public void setLXE(String LXE) {
        this.LXE = LXE;
    }

    public String getLXEDM() {
        return LXEDM;
    }

    public void setLXEDM(String LXEDM) {
        this.LXEDM = LXEDM;
    }

    public String getLXS() {
        return LXS;
    }

    public void setLXS(String LXS) {
        this.LXS = LXS;
    }

    public String getLXSDM() {
        return LXSDM;
    }

    public void setLXSDM(String LXSDM) {
        this.LXSDM = LXSDM;
    }

    public String getSFFH() {
        return SFFH;
    }

    public void setSFFH(String SFFH) {
        this.SFFH = SFFH;
    }

    public String getFYSFSL() {
        return FYSFSL;
    }

    public void setFYSFSL(String FYSFSL) {
        this.FYSFSL = FYSFSL;
    }

    public String getSFXZFY() {
        return SFXZFY;
    }

    public void setSFXZFY(String SFXZFY) {
        this.SFXZFY = SFXZFY;
    }

    public String getXZJGSFSL() {
        return XZJGSFSL;
    }

    public void setXZJGSFSL(String XZJGSFSL) {
        this.XZJGSFSL = XZJGSFSL;
    }

    public String getSFGK() {
        return SFGK;
    }

    public void setSFGK(String SFGK) {
        this.SFGK = SFGK;
    }

    public String getNR() {
        return NR;
    }

    public void setNR(String NR) {
        this.NR = NR;
    }

    public String getFJ() {
        return FJ;
    }

    public void setFJ(String FJ) {
        this.FJ = FJ;
    }

    public List<Petition_contact_people> getPeoplAll() {
        return peoplAll;
    }

    public void setPeoplAll(List<Petition_contact_people> peoplAll) {
        this.peoplAll = peoplAll;
    }
}
