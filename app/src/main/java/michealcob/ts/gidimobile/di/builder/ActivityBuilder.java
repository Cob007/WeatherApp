package michealcob.ts.gidimobile.di.builder;



import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import michealcob.ts.gidimobile.ui.detail.InfoActivity;
import michealcob.ts.gidimobile.ui.detail.InfoActivityModule;
import michealcob.ts.gidimobile.ui.home.MainActivity;
import michealcob.ts.gidimobile.ui.home.MainActivityModule;
import michealcob.ts.gidimobile.ui.search.SearchActivity;
import michealcob.ts.gidimobile.ui.search.SearchActivityModule;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {SearchActivityModule.class})
    abstract SearchActivity searchActivity();

    @ContributesAndroidInjector(modules = {InfoActivityModule.class})
    abstract InfoActivity infoActivity();
}


