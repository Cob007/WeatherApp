package michealcob.ts.gidimobile.db.country;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Country country);

    @Query("SELECT * FROM country")
    LiveData<List<Country>> getAllCountry();
}
