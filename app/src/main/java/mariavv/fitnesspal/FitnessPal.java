package mariavv.fitnesspal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.StringRes;

public class FitnessPal extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static String getAppString(@StringRes int stringRes) {
        return appContext.getString(stringRes);
    }
}
