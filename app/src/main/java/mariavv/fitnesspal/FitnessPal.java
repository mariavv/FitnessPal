package mariavv.fitnesspal;

import android.app.Application;
import android.content.Context;

import mariavv.fitnesspal.model.repository.Repo;

public class FitnessPal extends Application {

    public static Context appContext;

    public static Repo getRepo() {
        return Repo.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
