package michealcob.ts.gidimobile.net.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cord {
    @Expose
    @SerializedName("lat")
    public double lat;

    @Expose
    @SerializedName("lon")
    public double lon;
}
