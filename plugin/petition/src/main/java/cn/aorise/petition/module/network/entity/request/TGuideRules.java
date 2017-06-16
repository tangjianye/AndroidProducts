package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/16.
 */

public class TGuideRules {
    private String GGLX;
    private String CXTJ;
    private int currentPage;
    private int pageSize;

    @Override
    public String toString() {
        return "TGuideRules{" +
                "GGLX='" + GGLX + '\'' +
                ", CXTJ='" + CXTJ + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }

    public String getGGLX() {
        return GGLX;
    }

    public void setGGLX(String GGLX) {
        this.GGLX = GGLX;
    }

    public String getCXTJ() {
        return CXTJ;
    }

    public void setCXTJ(String CXTJ) {
        this.CXTJ = CXTJ;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
