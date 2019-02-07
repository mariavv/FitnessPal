package mariavv.fitnesspal.ui.journal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.ui.journal.daycard.DayCardFragment;

class PagerAdapter extends FragmentPagerAdapter {

    PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return DayCardFragment.newInstance(FitnessPal.getRepo().getDateByIndex(i));
    }

    @Override
    public int getCount() {
        return FitnessPal.getRepo().getJournalDaysCount();
    }
}
