package michealcob.ts.gidimobile.ui.search;

import dagger.Module;
import dagger.Provides;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

@Module
public class SearchActivityModule {
    @Provides
    SearchViewModel providesSearchViewModel(DataManager dataManager,
                                            SchedulerProvider schedulerProvider){
        return new SearchViewModel(dataManager, schedulerProvider);
    }

    @Provides
    int provideLayoutId(){
        return R.layout.activity_search;
    }
}


