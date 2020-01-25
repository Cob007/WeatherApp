package michealcob.ts.gidimobile.ui.detail;


import android.content.Context;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import javax.inject.Inject;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import michealcob.ts.gidimobile.BR;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.databinding.ActivityInfoBinding;
import michealcob.ts.gidimobile.net.response.model.City;
import michealcob.ts.gidimobile.net.response.model.Forecast;
import michealcob.ts.gidimobile.ui.base.BaseActivity;
import michealcob.ts.gidimobile.utils.manager.AlertManager;

public class InfoActivity extends BaseActivity<ActivityInfoBinding,
        InfoViewModel> implements InfoNavigator {
    
    @Inject
    InfoViewModel infoViewModel;
    
    @Inject
    int layoutId = R.layout.activity_info;
    
    ActivityInfoBinding mBinder;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBinding();
    }

    private void doBinding() {
        mBinder = getViewDataBinding();
        mBinder.setInfoNavigator(this);
        mBinder.setInfoViewModel(infoViewModel);
        infoViewModel.setNavigator(this);
        init();
    }

    private void init() {
        Glide.with(InfoActivity.this)
                .load(R.drawable.ic_bk_lagos)
                .into(mBinder.image);
        String city = getIntent().getStringExtra("name");
        String search = getIntent().getStringExtra("search");
        mBinder.tvName.setText(city);
        alertManager = new AlertManager(this);
        if(isOnline()){
            getViewModel().fetchForecast(search);
        }else{
            showToastMsg("Not Online");
        }

    }

    AlertManager alertManager;

    @Override
    public InfoViewModel getViewModel() {
        return infoViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.infoViewModel;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void forecast(ArrayList<Forecast> forecastArrayList, City city) {

        mBinder.tvLat.setText(String.valueOf(city.getCoord().lat));
        mBinder.tvLon.setText(String.valueOf(city.getCoord().lon));

        String url = "http://openweathermap.org/img/wn/";

        Forecast forecast1 = forecastArrayList.get(0);
        Forecast forecast2 = forecastArrayList.get(8);
        Forecast forecast3 = forecastArrayList.get(16);
        Forecast forecast4 = forecastArrayList.get(24);
        Forecast forecast5 = forecastArrayList.get(32);

        mBinder.tvCurrent.setText(forecast1.getWeather().get(0).main);
        String url1 = url + forecast1.getWeather().get(0).icon + "@2x.png";
        Glide.with(InfoActivity.this).load(url1).into(mBinder.ivCurrent);

        mBinder.tvForecast1.setText(forecast1.getWeather().get(0).main);
        Glide.with(InfoActivity.this).load(url1).into(mBinder.ivForcast1);

        mBinder.tvForecast2.setText(forecast2.getWeather().get(0).main);
        String url2 = url + forecast2.getWeather().get(0).icon + "@2x.png";
        Glide.with(InfoActivity.this).load(url2).into(mBinder.ivForcast2);

        mBinder.tvForecast3.setText(forecast3.getWeather().get(0).main);
        String url3 = url + forecast3.getWeather().get(0).icon + "@2x.png";
        Glide.with(InfoActivity.this).load(url3).into(mBinder.ivForcast3);

        mBinder.tvForecast4.setText(forecast4.getWeather().get(0).main);
        String url4 = url + forecast4.getWeather().get(0).icon + "@2x.png";
        Glide.with(InfoActivity.this).load(url4).into(mBinder.ivForcast4);

        mBinder.tvForecast5.setText(forecast5.getWeather().get(0).main);
        String url5 = url + forecast5.getWeather().get(0).icon + "@2x.png";
        Glide.with(InfoActivity.this).load(url5).into(mBinder.ivForcast5);


    }

    @Override
    public void showLoader() {
        alertManager.showLoading();
    }

    @Override
    public void hideLoader() {
        alertManager.closeLoading();
    }

    @Override
    public void showToastMsg(String msg) {
        alertManager.showQuickToast(msg);
    }
}
