package mariavv.fitnesspal.ui.navigate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.ui.UiTools;
import mariavv.fitnesspal.ui.handbook.HandBookFragment;
import mariavv.fitnesspal.ui.journal.JournalFragment;

public class NavigateActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    UiTools.replaceFragment(JournalFragment.newInstance(), getSupportFragmentManager());
                    return true;
                case R.id.navigation_notifications:
                    UiTools.replaceFragment(HandBookFragment.newInstance(), getSupportFragmentManager());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
