package michealcob.ts.gidimobile.ui.base;

import android.app.Activity;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import michealcob.ts.gidimobile.AppConstants;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.net.RetrofitInstance;
import michealcob.ts.gidimobile.utils.helper.NetworkUtils;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;


public abstract class BaseViewModel<N> extends ViewModel {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    public ObservableField<Integer> isLoadingVisible = new ObservableField<>(View.GONE);
    public ObservableField<Integer> isContentVisible = new ObservableField<>(View.GONE);
    private N mNavigator;
    private CompositeDisposable mCompositeDisposable;
    private RetrofitInstance retrofit;

    public BaseViewModel() {
        mDataManager = null;
        mSchedulerProvider = null;
    }

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    private N navigator;


    public N getNavigator() {
        return navigator;
    }

    public void setNavigator(N navigator) {
        this.navigator = navigator;
    }

    public RetrofitInstance getRetrofit(){
        return retrofit;
    }

    public void setRetrofit(RetrofitInstance retroInstance) {
        this.retrofit = retroInstance;
    }


    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public ObservableBoolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean value) {
        isLoading.set(value);
        isLoadingVisible.set(value ? View.VISIBLE : View.GONE);
        isContentVisible.set(value ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public boolean isUserLoggedIn() {
        return getDataManager().isUserLoggedIn();
    }

    public String getUserId() {
        if (!isUserLoggedIn())
            return "anonymous";
        return "";
        // return getDataManager().getCurrentUserId();
    }

    public boolean isOnline() {
        Activity activity = AppConstants.getFirstActivity();
        if (activity != null)
            return NetworkUtils.isNetworkConnected(activity);
        return false;
    }

    protected String getString(int idStr) {
        Activity activity = AppConstants.getFirstActivity();
        if (activity != null)
            return activity.getString(idStr);
        return "";
    }
}
