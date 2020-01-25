package michealcob.ts.gidimobile.ui.detail;

import android.util.Log;

import java.util.ArrayList;

import michealcob.ts.gidimobile.config.BuildConfig;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.net.RetrofitInstance;
import michealcob.ts.gidimobile.net.response.BaseResponse;
import michealcob.ts.gidimobile.net.response.model.City;
import michealcob.ts.gidimobile.net.response.model.Forecast;
import michealcob.ts.gidimobile.ui.base.BaseViewModel;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoViewModel extends BaseViewModel<InfoNavigator> {
    public InfoViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setNavigator(InfoNavigator navigator) {
        super.setNavigator(navigator);
    }

    void fetchForecast(String search){
        getNavigator().showLoader();
        RetrofitInstance retrofitInstance = new RetrofitInstance();
        Call<BaseResponse> responseCall =
                retrofitInstance.getApi().getForecast(search, "json",
                        BuildConfig.INSTANCE.getAPP_ID());
        responseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call,
                                   Response<BaseResponse> response) {
                getNavigator().hideLoader();
                if (response.isSuccessful()){
                    BaseResponse res = response.body();
                    Log.v("TAG", res.cod);
                    ArrayList<Forecast> forecastArrayList = res.getList();
                    City city = res.getCity();
                    getNavigator().forecast(forecastArrayList, city);
                }else{
                    String resError = response.message();
                    getNavigator().showToastMsg(resError);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.v("error", t.getMessage()+ " ");
                getNavigator().hideLoader();
                getNavigator().showToastMsg("please, Check your internet connection");

            }
        });
    }
}
