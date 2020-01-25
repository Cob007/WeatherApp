package michealcob.ts.gidimobile.data.local.prefs

import android.content.Context
import android.content.SharedPreferences

import javax.inject.Inject

import michealcob.ts.gidimobile.di.PreferenceInfo


class AppPreferencesHelper : PreferencesHelper {

    private val mPrefs: SharedPreferences?
    private val cachedResponses: SharedPreferences?
    private var context: Context?= null

    override val isUserLoggedIn: Boolean
        get() = false


    @Inject
    internal constructor(context: Context, @PreferenceInfo prefFileName: String) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
        cachedResponses = context.getSharedPreferences(prefFileName + "_response_cached", Context.MODE_PRIVATE)


        //this.context = prefFileName
        }

    constructor(context: Context) {
        this.context = context
        cachedResponses = null
        mPrefs = null

        this.context = context
    }


    override fun setAccessToken(token: String) {

    }

    override fun Logout() {

    }

    companion object {

        private val PREF_KEY_LOCAL_DATABASE_UPDATED = "LOCAL_DATABASE_UPDATED_1"
        private val SHARED_PREF_KEY_NAME = "my_shared_pref"
        private var mInstance: AppPreferencesHelper? = null

        @Synchronized
        fun getInstance(context: Context): AppPreferencesHelper {
            if (mInstance == null) {
                mInstance = AppPreferencesHelper(context)
            }
            return mInstance as AppPreferencesHelper
        }
    }

}
