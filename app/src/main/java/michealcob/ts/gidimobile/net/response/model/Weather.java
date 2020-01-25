package michealcob.ts.gidimobile.net.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {
    @Expose
    @SerializedName("main")
    public String main;

    @Expose
    @SerializedName("icon")
    public String icon;
}
