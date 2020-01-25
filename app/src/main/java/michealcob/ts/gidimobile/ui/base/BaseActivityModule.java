package michealcob.ts.gidimobile.ui.base;


import dagger.Module;
import dagger.Provides;
import michealcob.ts.gidimobile.net.RetrofitInstance;

@Module
public class BaseActivityModule  {

    @Provides
    RetrofitInstance provideRetrofit(){
        return  new RetrofitInstance();
    }
}