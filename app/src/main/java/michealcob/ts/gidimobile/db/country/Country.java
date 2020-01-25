package michealcob.ts.gidimobile.db.country;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "country")
public class Country {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city")
    @NonNull
    private String city;

    @ColumnInfo(name = "code")
    @NonNull
    private String cityCode;

    public Country(int id, String city, String cityCode) {
        this.id = id;
        this.city = city;
        this.cityCode = cityCode;
    }

    @Ignore
    public Country(@NonNull String city, @NonNull String cityCode) {
        this.city = city;
        this.cityCode = cityCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
