package cn.aorise.petition.ui.bean;

/**
 * Created by Administrator on 2017/4/26.
 */

public class Petition_contact_people {
    private String name;
    private String num;
    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Petition_contact_people{" +
                "name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
