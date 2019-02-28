package mariavv.fitnesspal.data.repository;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mariavv.fitnesspal.FitnessPal;

public class SharedDataRepository {

    private static final String FIRST_RUN = "first_run";

    private static SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FitnessPal.appContext);

    public static void saveNotFirstRun(boolean isNotFirstRun) {
        saveBoolean(FIRST_RUN, isNotFirstRun);
    }

    private static void saveBoolean(String key, boolean value) {
        sharedPreferences
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    public static boolean isNotFirstRun() {
        return getBoolean(FIRST_RUN);
    }

    private static boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }
}
