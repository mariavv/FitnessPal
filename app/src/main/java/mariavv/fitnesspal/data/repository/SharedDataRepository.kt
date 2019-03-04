package mariavv.fitnesspal.data.repository

import android.preference.PreferenceManager

import mariavv.fitnesspal.App

object SharedDataRepository {

    private val FIRST_RUN = "first_run"

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.context)

    val isNotFirstRun: Boolean
        get() = getBoolean(FIRST_RUN)

    fun saveNotFirstRun(isNotFirstRun: Boolean) {
        saveBoolean(FIRST_RUN, isNotFirstRun)
    }

    private fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply()
    }

    private fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}
