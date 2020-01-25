package michealcob.ts.gidimobile.net.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {
    @Expose
    @SerializedName("coord")
    private Cord coord;

    public Cord getCoord() {
        return coord;
    }

    public void setCoord(Cord coord) {
        this.coord = coord;
    }
}
