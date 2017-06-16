package cn.aorise.sample.module.network.entity.response;

/**
 * Created by pc on 2017/3/15.
 */
public class Hospital {
    /**
     * hospitalId : 101
     * hospitalName : 佛山市第一人民医院
     * styleColor : FF0032
     * tel : 0757-88888888
     * city : 佛山市
     */
    private String hospitalId;
    private String hospitalName;
    private String styleColor;
    private String tel;
    private String city;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId='" + hospitalId + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", styleColor='" + styleColor + '\'' +
                ", tel='" + tel + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
