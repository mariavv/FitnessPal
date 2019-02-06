package mariavv.fitnesspal.ui.journal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Date;

import mariavv.fitnesspal.ui.journal.daycard.DayCardFragment;

class PagerAdapter extends FragmentPagerAdapter {

    PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //todo
        return DayCardFragment.newInstance(new Date());
    }

    @Override
    public int getCount() {
        //todo
        return 2;
    }
}
