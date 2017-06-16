package cn.aorise.petition.ui.bean;

/**
 * Created by Administrator on 2017/5/2.
 */

public class QueryEvaluationInfo {
    private String petition_letter_number;
    private String petition_letter_content;
    private String petition_date;
    private int icon;
    private int i;

    @Override
    public String toString() {
        return "QueryEvaluationInfo{" +
                "petition_letter_number='" + petition_letter_number + '\'' +
                ", petition_letter_content='" + petition_letter_content + '\'' +
                ", petition_date='" + petition_date + '\'' +
                ", icon='" + icon + '\'' +
                ", i=" + i +
                '}';
    }

    public String getPetition_letter_number() {
        return petition_letter_number;
    }

    public void setPetition_letter_number(String petition_letter_number) {
        this.petition_letter_number = petition_letter_number;
    }

    public String getPetition_letter_content() {
        return petition_letter_content;
    }

    public void setPetition_letter_content(String petition_letter_content) {
        this.petition_letter_content = petition_letter_content;
    }

    public String getPetition_date() {
        return petition_date;
    }

    public void setPetition_date(String petition_date) {
        this.petition_date = petition_date;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
