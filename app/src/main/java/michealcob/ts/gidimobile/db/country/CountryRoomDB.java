package michealcob.ts.gidimobile.db.country;



import androidx.room.Database;

import androidx.room.RoomDatabase;


@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryRoomDB extends RoomDatabase {
    public abstract CountryDAO countryDAO();
}
