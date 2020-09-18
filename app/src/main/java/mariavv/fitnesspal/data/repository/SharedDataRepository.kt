package mariavv.fitnesspal.data.repository

import android.preference.PreferenceManager

import mariavv.fitnesspal.App

object SharedDataRepository {

    private const val FIRST_RUN = "first_run"

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.context)

    val isFirstRun: Boolean
        get() = !getBoolean(FIRST_RUN)

    private fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply()
    }

    private fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun saveFirstRun() {
        saveBoolean(FIRST_RUN, true)
    }
}
