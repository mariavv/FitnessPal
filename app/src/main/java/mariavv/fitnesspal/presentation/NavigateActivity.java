package mariavv.fitnesspal.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.other.Const;
import mariavv.fitnesspal.other.FrmFabric;
import mariavv.fitnesspal.presentation.adddish.AddDishFragment;
import mariavv.fitnesspal.presentation.addfood.AddFoodFragment;
import mariavv.fitnesspal.presentation.handbook.HandbookFragment;
import mariavv.fitnesspal.presentation.journal.JournalFragment;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class NavigateActivity extends MvpAppCompatActivity implements NavigateView {

    @InjectPresenter
    NavigatePresenter presenter;

    private BottomNavigationView navigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        navigationMenu = findViewById(R.id.navigation);
        navigationMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .addOnBackStackChangedListener(() -> presenter.changeFragment(getSupportFragmentManager(), R.id.container));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void setNavigator() {
        FitnessPal.instance.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    public void removeNavigator() {
        FitnessPal.instance.getNavigatorHolder().removeNavigator();
    }

    @Override
    public void hideBottomMenu() {
        navigationMenu.setVisibility(View.GONE);
    }

    @Override
    public void showBottomMenu() {
        navigationMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHomeAsUp(boolean enabled) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(enabled);
            getSupportActionBar().setDisplayShowHomeEnabled(enabled);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof FrmFabric.IFragment) {
            ((FrmFabric.IFragment) fragment).onBackPressed();
        } else {
            super.onBackPressed();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    presenter.onNavigationJournalSelected();
                    return true;
                case R.id.navigation_notifications:
                    presenter.onNavigationHandbookSelected();
                    return true;
            }
            return false;
        }
    };

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            Log.d("test", screenKey);
            switch (screenKey) {
                case Const.Screen.JOURNAL:
                    return new JournalFragment();
                case Const.Screen.HANDBOOK:
                    return new HandbookFragment();
                case Const.Screen.ADD_DISH:
                    return new AddDishFragment();
                case Const.Screen.ADD_FOOD:
                    return new AddFoodFragment();
                default:
                    throw new RuntimeException("Unknown screen key!");
            }
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(NavigateActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }
    };
}
