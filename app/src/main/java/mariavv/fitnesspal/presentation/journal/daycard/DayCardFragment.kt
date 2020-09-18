package mariavv.fitnesspal.presentation.journal.daycard

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.day_header_item.*
import kotlinx.android.synthetic.main.fragment_day_card.*
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const.BundleArg.ARG_DATE
import mariavv.fitnesspal.presentation.common.BaseFragment


class DayCardFragment : BaseFragment(), DayCardView {

    @InjectPresenter
    lateinit var presenter: DayCardPresenter

    private lateinit var adapter: FeedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_day_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            //val dr = PieChatView()
            //customPieChat.setBackgroundDrawable(dr)

            configureRecyclerView()
            presenter.onGetDateArg(args.getLong(ARG_DATE))
        }
    }

    private fun configureRecyclerView() {
        adapter = FeedAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)

        val decoration = DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL)
        recycler.addItemDecoration(decoration)
    }

    override fun updateCard(dataSet: List<ItemType>, dayProtein: Int, dayFat: Int, dayCarb: Int, dayEnergy: Int) {
        val weightPostfix = getString(R.string.weight_postfix)

        protein_tv.text = String.format("%s %s%s",
                getString(R.string.protein_full_prefix),
                dayProtein.toString(),
                weightPostfix)

        fat_tv.text = String.format("%s %s%s",
                getString(R.string.fat_full_prefix),
                dayFat.toString(),
                weightPostfix)

        carb_tv.text = String.format("%s %s%s",
                getString(R.string.carb_full_prefix),
                dayCarb.toString(),
                weightPostfix)

        energy_tv.text = String.format("%s %s %s",
                getString(R.string.energy_prefix),
                dayEnergy.toString(),
                getString(R.string.energy_postfix))

        adapter.updateItems(dataSet)
    }

    companion object {

        fun newInstance(date: Long): DayCardFragment {
            val fragment = DayCardFragment()
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            fragment.arguments = args
            return fragment
        }
    }
}
