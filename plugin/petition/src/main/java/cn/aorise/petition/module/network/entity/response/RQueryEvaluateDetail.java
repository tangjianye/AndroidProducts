package cn.aorise.petition.module.network.entity.response;

import java.util.List;

import cn.aorise.petition.ui.bean.Petition_contact_people;
import cn.aorise.petition.ui.bean.QueryEvaluateListInfo;
import cn.aorise.petition.ui.bean.QueryEvaluatePeopleInfo;
import cn.aorise.petition.ui.bean.QueryEvaluatePingjiaInfo;

/**
 * Created by Administrator on 2017/5/11.
 */

public class RQueryEvaluateDetail {
    private String XFJID ;
    private String SFD ;
    private String WTLX ;
    private String XXDZ ;
    private String SFFH ;
    private String FYSFSL ;
    private String SFXZFY ;
    private String ZCJGSFSL ;
    private String SFGK ;
    private List<QueryEvaluatePeopleInfo> peopleAll;
    private String NR;
    private String FJ;
    private List<QueryEvaluateListInfo> Opretion;
    private QueryEvaluatePingjiaInfo pingjia;

    @Override
    public String toString() {
        return "RQueryEvaluateDetail{" +
                "XFJID='" + XFJID + '\'' +
                ", SFD='" + SFD + '\'' +
                ", WTLX='" + WTLX + '\'' +
                ", XXDZ='" + XXDZ + '\'' +
                ", SFFH='" + SFFH + '\'' +
                ", FYSFSL='" + FYSFSL + '\'' +
                ", SFXZFY='" + SFXZFY + '\'' +
                ", ZCJGSFSL='" + ZCJGSFSL + '\'' +
                ", SFGK='" + SFGK + '\'' +
                ", peopleAll=" + peopleAll +
                ", NR='" + NR + '\'' +
                ", FJ='" + FJ + '\'' +
                ", Opretion=" + Opretion +
                ", pingjia=" + pingjia +
                '}';
    }

    public String getXFJID() {
        return XFJID;
    }

    public void setXFJID(String XFJID) {
        this.XFJID = XFJID;
    }

    public String getSFD() {
        return SFD;
    }

    public void setSFD(String SFD) {
        this.SFD = SFD;
    }

    public String getWTLX() {
        return WTLX;
    }

    public void setWTLX(String WTLX) {
        this.WTLX = WTLX;
    }

    public String getXXDZ() {
        return XXDZ;
    }

    public void setXXDZ(String XXDZ) {
        this.XXDZ = XXDZ;
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

    public String getZCJGSFSL() {
        return ZCJGSFSL;
    }

    public void setZCJGSFSL(String ZCJGSFSL) {
        this.ZCJGSFSL = ZCJGSFSL;
    }

    public String getSFGK() {
        return SFGK;
    }

    public void setSFGK(String SFGK) {
        this.SFGK = SFGK;
    }

    public List<QueryEvaluatePeopleInfo> getPeopleAll() {
        return peopleAll;
    }

    public void setPeopleAll(List<QueryEvaluatePeopleInfo> peopleAll) {
        this.peopleAll = peopleAll;
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

    public List<QueryEvaluateListInfo> getOpretion() {
        return Opretion;
    }

    public void setOpretion(List<QueryEvaluateListInfo> opretion) {
        Opretion = opretion;
    }

    public QueryEvaluatePingjiaInfo getPingjia() {
        return pingjia;
    }

    public void setPingjia(QueryEvaluatePingjiaInfo pingjia) {
        this.pingjia = pingjia;
    }
}
