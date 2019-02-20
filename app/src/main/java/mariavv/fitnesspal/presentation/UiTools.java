package mariavv.fitnesspal.presentation;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import mariavv.fitnesspal.R;

public class UiTools {

    public static void replaceFragment(Fragment fragment, @Nullable FragmentManager fm) {
        if (fm != null) {
            final FragmentTransaction trans = fm.beginTransaction();
            trans.replace(R.id.main_menu_tabs_containier, fragment);
            trans.commit();
        }
    }
}
