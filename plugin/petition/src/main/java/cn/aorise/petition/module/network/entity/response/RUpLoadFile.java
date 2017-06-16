package cn.aorise.petition.module.network.entity.response;

/**
 * Created by Administrator on 2017/5/5.
 */

public class RUpLoadFile {
    private String filename;
    private String filepath;
    private String id;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RUpLoadFile{" +
                "filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
