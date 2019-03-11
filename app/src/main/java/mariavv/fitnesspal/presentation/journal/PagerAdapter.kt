package mariavv.fitnesspal.presentation.journal

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import mariavv.fitnesspal.presentation.journal.daycard.DayCardFragment

internal class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var items: ArrayList<Long>? = null

    override fun getItem(i: Int): Fragment {
        return DayCardFragment.newInstance(items!![i])
    }

    override fun getCount(): Int {
        return if (items != null) {
            items!!.size
        } else 0
    }

    fun setItems(journalDays: ArrayList<Long>) {
        items = journalDays
        notifyDataSetChanged()
    }

    fun add(position: Int, value: Long) {
        items?.add(position, value)
        notifyDataSetChanged()
    }
}
