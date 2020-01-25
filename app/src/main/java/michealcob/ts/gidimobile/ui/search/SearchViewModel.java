package michealcob.ts.gidimobile.ui.search;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.db.country.Country;
import michealcob.ts.gidimobile.ui.base.BaseViewModel;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

public class SearchViewModel extends BaseViewModel<SearchNavigator> {

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setNavigator(SearchNavigator navigator) {
        super.setNavigator(navigator);
    }

    public void loadJson(Context context){
        try
        {
            JSONArray array = new JSONArray(loadJSONFromAsset(context));
            List<Country> countryList = new ArrayList<>();
            for (int i=0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String cityName = jsonObject.getString("name");
                String cityCode = jsonObject.getString("country");
                Log.v("name", cityName);
                Country country = new Country(cityName, cityCode);
                countryList.add(country);
            }
            getNavigator().passList(countryList);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("city.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
