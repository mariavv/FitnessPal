package mariavv.fitnesspal.presentation.journal;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mariavv.fitnesspal.presentation.journal.daycard.DayCardFragment;

class PagerAdapter extends FragmentPagerAdapter {

    private long[] items;

    PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return DayCardFragment.newInstance(items[i]);
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.length;
        }
        return 0;
    }

    void setItems(@NonNull long[] journalDaysCount) {
        items = journalDaysCount;
        notifyDataSetChanged();
    }
}
