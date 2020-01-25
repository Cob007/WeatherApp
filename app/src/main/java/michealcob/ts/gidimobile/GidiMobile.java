package michealcob.ts.gidimobile;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import michealcob.ts.gidimobile.di.component.DaggerAppComponent;

public class GidiMobile extends Application implements HasActivityInjector {
  @Inject
  DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


  @Override
  public void onCreate() {
    super.onCreate();
    DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this);

  }

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

}
