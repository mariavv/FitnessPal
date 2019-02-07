package mariavv.fitnesspal.ui.journal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Date;

import mariavv.fitnesspal.model.repository.Repo;
import mariavv.fitnesspal.ui.journal.daycard.DayCardFragment;

class PagerAdapter extends FragmentPagerAdapter {

    PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        //todo
        Repo repo = Repo.getInstance();
        if (repo != null) {
            return DayCardFragment.newInstance(repo.getDateByIndex(i));
        }
        return DayCardFragment.newInstance(new Date());
    }

    @Override
    public int getCount() {
        //todo
        Repo repo = Repo.getInstance();
        if (repo != null) {
            return repo.getJournalDaysCount();
        }
        return 0;
    }
}
