package michealcob.ts.gidimobile.di.module;



import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import michealcob.ts.gidimobile.AppConstants;
import michealcob.ts.gidimobile.config.BuildConfig;
import michealcob.ts.gidimobile.data.AppDataManager;
import michealcob.ts.gidimobile.data.DataManager;
import michealcob.ts.gidimobile.data.local.prefs.AppPreferencesHelper;
import michealcob.ts.gidimobile.data.local.prefs.PreferencesHelper;
import michealcob.ts.gidimobile.data.remote.ApiHelper;
import michealcob.ts.gidimobile.data.remote.AppApiHelper;
import michealcob.ts.gidimobile.di.ApiInfo;
import michealcob.ts.gidimobile.di.PreferenceInfo;
import michealcob.ts.gidimobile.utils.rx.AppSchedulerProvider;
import michealcob.ts.gidimobile.utils.rx.SchedulerProvider;

@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.INSTANCE.getAPI_KEY();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    //release on api header addition
/*
    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(preferencesHelper.getAccessToken());
    }

    */


}
