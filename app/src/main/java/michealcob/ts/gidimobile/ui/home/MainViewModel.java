package michealcob.ts.gidimobile.ui.home;


import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.ui.base.BaseViewModel;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setNavigator(MainNavigator navigator) {
        super.setNavigator(navigator);
    }

}
