package michealcob.ts.gidimobile.ui.detail;

import java.util.ArrayList;

import michealcob.ts.gidimobile.net.response.model.City;
import michealcob.ts.gidimobile.net.response.model.Forecast;

public interface InfoNavigator {
    void onBack();

    void forecast(ArrayList<Forecast> forecastArrayList, City city);

    void showLoader();

    void hideLoader();

    void showToastMsg(String resError);
}
