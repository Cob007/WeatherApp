package michealcob.ts.gidimobile.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import michealcob.ts.gidimobile.GidiMobile;
import michealcob.ts.gidimobile.di.builder.ActivityBuilder;
import michealcob.ts.gidimobile.di.module.AppModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
    void inject(GidiMobile app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
