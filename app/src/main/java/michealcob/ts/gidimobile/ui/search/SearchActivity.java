package michealcob.ts.gidimobile.ui.search;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import michealcob.ts.gidimobile.BR;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.databinding.ActivitySearchBinding;
import michealcob.ts.gidimobile.db.country.Country;
import michealcob.ts.gidimobile.ui.adapter.SearchAdapter;
import michealcob.ts.gidimobile.ui.base.BaseActivity;
import michealcob.ts.gidimobile.ui.detail.InfoActivity;

public class SearchActivity extends
        BaseActivity<ActivitySearchBinding, SearchViewModel>
        implements SearchNavigator{

    @Inject
    SearchViewModel searchViewModel;

    @Inject
    int layoutId = R.layout.activity_search;

    ActivitySearchBinding mBinder;

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
        mBinder.setSearchnavigator(this);
        mBinder.setSearchViewModel(searchViewModel);
        searchViewModel.setNavigator(this);
        init();
    }

    private void init() {
        //loading country from json list
        searchViewModel.loadJson(this);
    }

    @Override
    public SearchViewModel getViewModel() {
        return searchViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.searchViewModel;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public void passList(List<Country> countryList) {
        SearchAdapter.ItemClickListener itemClickListener = (country, name) -> {
            Intent intent = new Intent(SearchActivity.this, InfoActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("search", country);
            SearchActivity.this.startActivity(intent);
        };

        SearchAdapter searchAdapter = new SearchAdapter(itemClickListener,
                countryList, SearchActivity.this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                SearchActivity.this);

        mBinder.rvSearch.setLayoutManager(layoutManager);
        mBinder.rvSearch.setAdapter(searchAdapter);
    }
}
