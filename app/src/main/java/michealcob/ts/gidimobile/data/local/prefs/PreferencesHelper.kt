package michealcob.ts.gidimobile.data.local.prefs

interface PreferencesHelper {

    val isUserLoggedIn: Boolean

    fun setAccessToken(token: String)

    fun Logout()

}
