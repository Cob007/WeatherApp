package michealcob.ts.gidimobile.net;

import java.util.ArrayList;

import michealcob.ts.gidimobile.net.response.BaseResponse;
import michealcob.ts.gidimobile.net.response.model.City;
import michealcob.ts.gidimobile.net.response.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkInterface {

    @GET("data/2.5/forecast")
    Call<BaseResponse> getForecast(
            @Query("q") String search,
            @Query("mode") String mode,
            @Query("APPID") String appId
    );
}


