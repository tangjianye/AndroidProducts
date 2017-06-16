package cn.aorise.sample.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Entity mapped to table "NOTE".
 */
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class SampleNote {
    @Id
    private Long id;
    @NotNull
    @Unique
    private String text;
    private String comment;
    private java.util.Date date;

    @Generated(hash = 1257657372)
    public SampleNote(Long id, @NotNull String text, String comment,
                      java.util.Date date) {
        this.id = id;
        this.text = text;
        this.comment = comment;
        this.date = date;
    }

    @Generated(hash = 881177879)
    public SampleNote() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SampleNote{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
