package michealcob.ts.gidimobile.ui.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.bumptech.glide.Glide;


import javax.inject.Inject;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import michealcob.ts.gidimobile.BR;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.databinding.ActivityMainBinding;
import michealcob.ts.gidimobile.db.country.CountryRepo;
import michealcob.ts.gidimobile.ui.base.BaseActivity;
import michealcob.ts.gidimobile.ui.detail.InfoActivity;
import michealcob.ts.gidimobile.ui.search.SearchActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements MainNavigator{

    @Inject
    MainViewModel mainViewModel;

    @Inject
    int layoutId = R.layout.activity_main;

    ActivityMainBinding mBinder;

    CountryRepo countryRepo;

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
        mBinder.setViewmodel(mainViewModel);
        mainViewModel.setNavigator(this);
        mBinder.setNavigator(this);
        init();
    }

    private void init() {
        Glide.with(MainActivity.this)
                .load(R.drawable.ic_bk_lagos)
                .into(mBinder.ivBk);
        countryRepo = new CountryRepo(MainActivity.this);

    }

    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewmodel;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void current() {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        intent.putExtra("name", "LAGOS, NIGERIA");
        intent.putExtra("search", "lagos, NG");
        startActivity(intent);
    }

    @Override
    public void search() {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }
}
