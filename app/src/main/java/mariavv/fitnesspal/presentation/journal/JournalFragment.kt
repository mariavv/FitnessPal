package mariavv.fitnesspal.presentation.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_journal.*
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric
import mariavv.fitnesspal.presentation.common.BaseFragment

class JournalFragment : BaseFragment(), JournalView, FrmFabric.IFragment {

    private lateinit var pagerAdapter: PagerAdapter

    @InjectPresenter
    lateinit var presenter: JournalPresenter

    private val pageCount: Int
        get() = pagerAdapter.count

    private val currentPage: Int
        get() = viewPager.currentItem

    override val name: String
        get() = Const.Screen.JOURNAL

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_journal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureViews()
    }

    private fun configureViews() {
        pagerAdapter = PagerAdapter(childFragmentManager)
        viewPager.adapter = pagerAdapter

        left.setOnClickListener { presenter.onPrevDayClick(currentPage, pageCount) }
        right.setOnClickListener { presenter.onNextDayClick(currentPage, pageCount) }

        fab.setOnClickListener { presenter.onFabClick() }
    }

    override fun moveToPosition(position: Int, date: String) {
        viewPager.currentItem = position
        date_tv.text = date
    }

    override fun setAdapterItems(journalDates: LongArray) {
        pagerAdapter.setItems(journalDates)
    }

    override fun setPrevDayEnable() {
        left.setImageDrawable(App.context.getDrawable(R.drawable.ic_chevron_left_black_24dp))
    }

    override fun setNextDayEnable() {
        right.setImageDrawable(App.context.getDrawable(R.drawable.ic_chevron_right_black_24dp))
    }

    override fun setPrevDayDisable() {
        left.setImageDrawable(App.context.getDrawable(R.drawable.ic_chevron_left_black_inactive_24dp))
    }

    override fun setNextDayDisable() {
        right.setImageDrawable(App.context.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp))
    }

    override fun setDate(date: String) {
        date_tv.text = date
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
