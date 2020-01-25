package michealcob.ts.gidimobile.data;


import michealcob.ts.gidimobile.data.local.prefs.PreferencesHelper;
import michealcob.ts.gidimobile.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {
    void updateApiHeader(String token);

    void setUserAsLoggedOut();


    void updateUserInfo(
            String accessToken,
            LoggedInMode loggedInMode,
            String userName,
            String phoneNumber,
            String email,
            String profilePicPath,
            String token);


    enum LoggedInMode {
        LOGGED_OUT(0),
        LOGGED_IN(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
