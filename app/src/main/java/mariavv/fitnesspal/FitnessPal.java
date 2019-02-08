package mariavv.fitnesspal;

import android.app.Application;
import android.content.Context;

public class FitnessPal extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
