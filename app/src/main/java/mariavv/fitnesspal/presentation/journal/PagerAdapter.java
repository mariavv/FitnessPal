package mariavv.fitnesspal.presentation.journal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mariavv.fitnesspal.data.repository.Repo;
import mariavv.fitnesspal.presentation.journal.daycard.DayCardFragment;

class PagerAdapter extends FragmentPagerAdapter {

    PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return DayCardFragment.newInstance(Repo.getInstance().getDateByIndex(i));
    }

    @Override
    public int getCount() {
        return Repo.getInstance().getJournalDaysCount();
    }

    /*@Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Repo.getInstance().getDateByIndex(position);
    }*/
}
