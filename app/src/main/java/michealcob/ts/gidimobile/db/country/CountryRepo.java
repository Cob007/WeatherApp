package michealcob.ts.gidimobile.db.country;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class CountryRepo{
    private String DB_NAME = "db_country";
    private Context context;
    private static CountryRepo mInstance;
    private CountryRoomDB countryRoomDB;

    public CountryRepo(Context context) {
        this.context = context;
        countryRoomDB = Room.databaseBuilder(context, CountryRoomDB.class, DB_NAME).build();
    }

    public static synchronized CountryRepo getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new CountryRepo(mCtx);
        }
        return mInstance;
    }

    public CountryRoomDB getAppDatabase() {
        return countryRoomDB;
    }




}
