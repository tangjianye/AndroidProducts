package cn.aorise.common.component.network.entity.response;

/**
 * Created by tangjy on 2017/3/22.
 */

public class AccountInfo {
    private String account;
    private String id;
    private String sex;

    public AccountInfo() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "account='" + account + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
