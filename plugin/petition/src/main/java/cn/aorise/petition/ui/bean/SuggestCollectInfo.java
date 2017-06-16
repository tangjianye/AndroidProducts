package cn.aorise.petition.ui.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import cn.aorise.petition.R;

/**
 * Created by Administrator on 2017/4/28.
 */

public class SuggestCollectInfo {
    private String suggestNum;
    private String suggestContent;
    private String suggestDate;
    private String answerContent;
    private int icon;
    private int i;//1为已回复  2为未回复

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "SuggestCollectInfo{" +
                "suggestNum='" + suggestNum + '\'' +
                ", suggestContent='" + suggestContent + '\'' +
                ", suggestDate='" + suggestDate + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", icon=" + icon +
                '}';
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getSuggestNum() {
        return suggestNum;
    }

    public void setSuggestNum(String suggestNum) {
        this.suggestNum = suggestNum;
    }

    public String getSuggestContent() {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent) {
        this.suggestContent = suggestContent;
    }

    public String getSuggestDate() {
        return suggestDate;
    }

    public void setSuggestDate(String suggestDate) {
        this.suggestDate = suggestDate;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }



}
