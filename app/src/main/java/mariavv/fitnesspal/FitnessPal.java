package mariavv.fitnesspal;

import android.app.Application;

import mariavv.fitnesspal.model.repository.Repo;

public class FitnessPal extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Repo.getInstance(this);
    }
}
