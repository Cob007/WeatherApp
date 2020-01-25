package michealcob.ts.gidimobile.ui.home;

import dagger.Module;
import dagger.Provides;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

@Module
public class MainActivityModule {

    @Provides
    MainViewModel providesMainViewModel(DataManager dataManager,
                                        SchedulerProvider schedulerProvider) {
        return new MainViewModel(dataManager, schedulerProvider);
    }

    @Provides
    int provideLayoutId() {
        return R.layout.activity_main;
    }
}
