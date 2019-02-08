package mariavv.fitnesspal.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;

public class UiTools {

    public static final String LOG_D_TAG = FitnessPal.class.getSimpleName();

    public static void replaceFragment(Fragment fragment, FragmentManager supportFragmentManager) {
        final FragmentTransaction trans = supportFragmentManager.beginTransaction();
        trans.replace(R.id.main_menu_tabs_containier, fragment);
        trans.commit();
    }

    public static void showToast(String message) {
        Toast.makeText(FitnessPal.appContext, message, Toast.LENGTH_LONG).show();
    }
}
