package cn.aorise.petition.module.network.entity.request;

/**
 * Created by Administrator on 2017/5/11.
 */

public class TQueryEvaluateDetail {
    private String BH;

    @Override
    public String toString() {
        return "TQueryEvaluateDetail{" +
                "BH='" + BH + '\'' +
                '}';
    }

    public String getBH() {
        return BH;
    }

    public void setBH(String BH) {
        this.BH = BH;
    }
}
