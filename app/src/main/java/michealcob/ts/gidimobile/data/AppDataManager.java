package michealcob.ts.gidimobile.data;

import android.content.Context;

import javax.inject.Inject;

import michealcob.ts.gidimobile.data.local.prefs.PreferencesHelper;
import michealcob.ts.gidimobile.data.remote.ApiHeader;
import michealcob.ts.gidimobile.data.remote.ApiHelper;


public class AppDataManager implements DataManager {
    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


   /* @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }


    @Override
    public Single<LoginResponse> doUserLogin(LoginRequest request) {
        return mApiHelper.doUserLogin(request);
    }

    @Override
    public Single<SignupResponse> doUserSignUp(SignUpRequest request) {
        return null;
    }


    @Override
    public void updateApiHeader(String token) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAuthorization(token);
    }
*/
    @Override
    public void updateUserInfo(
            String userId,
            DataManager.LoggedInMode loggedInMode,
            String username,
            String phoneNumber,
            String email,
            String profilePicPath, String token) {
        updateApiHeader(token);
    }

    @Override
    public void updateApiHeader(String token) {

    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(
                null,
                DataManager.LoggedInMode.LOGGED_OUT,
                null,
                null,
                null,
                null,
                null);
    }



    @Override
    public void setAccessToken(String token) {

    }

    @Override
    public void Logout() {

    }

    @Override
    public boolean isUserLoggedIn() {
        return false;
    }

    @Override
    public ApiHeader getApiHeader() {
        return null;
    }
}
