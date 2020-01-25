package michealcob.ts.gidimobile.net.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import michealcob.ts.gidimobile.net.response.model.City;
import michealcob.ts.gidimobile.net.response.model.Forecast;

public class BaseResponse {
    @Expose
    @SerializedName("cod")
    public String cod;

    @Expose
    @SerializedName("message")
    public String message;

    @Expose
    @SerializedName("cnt")
    public int cnt;

    @Expose
    @SerializedName("list")
    private ArrayList<Forecast> list;

    public ArrayList<Forecast> getList() {
        return list;
    }

    public void setList(ArrayList<Forecast> list) {
        this.list = list;
    }

    @Expose
    @SerializedName("city")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
