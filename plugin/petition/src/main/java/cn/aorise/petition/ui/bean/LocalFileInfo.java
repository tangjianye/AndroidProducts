package cn.aorise.petition.ui.bean;

/**
 * Created by Administrator on 2017/4/28.
 */

public class LocalFileInfo {
    private String fileName;

    @Override
    public String toString() {
        return "LocalFileInfo{" +
                "fileName='" + fileName + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
