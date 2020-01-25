package michealcob.ts.gidimobile.ui.detail;

import dagger.Module;
import dagger.Provides;
import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

@Module
public class InfoActivityModule {
    @Provides
    InfoViewModel provideInfoViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider){
        return new InfoViewModel(dataManager, schedulerProvider);
    }

    @Provides
    int provideLayoutId(){ return R.layout.activity_info;}
}
