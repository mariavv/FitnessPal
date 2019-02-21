package mariavv.fitnesspal;

import android.app.Application;
import android.content.Context;
import android.support.annotation.StringRes;

import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class FitnessPal extends Application {

    public static FitnessPal instance;
    public static Context appContext;
    private Cicerone<Router> cicerone;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instance = this;
        initCicerone();
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }

    public static String getAppString(@StringRes int stringRes) {
        return appContext.getString(stringRes);
    }
}
