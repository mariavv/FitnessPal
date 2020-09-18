package mariavv.fitnesspal.presentation.handbook

import android.database.Cursor
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_hand_book.*
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric
import mariavv.fitnesspal.presentation.common.BaseFragment

class HandbookFragment : BaseFragment(), HandBookView, FrmFabric.IFragment {

    private lateinit var adapter: FeedAdapter

    @InjectPresenter
    lateinit var presenter: HandBookPresenter

    override val name: String
        get() = Const.Screen.HANDBOOK

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hand_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FeedAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.addItemDecoration(DividerItemDecoration(recycler.context, DividerItemDecoration.VERTICAL))

        fab.setOnClickListener { presenter.onFabClick() }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun updateFeed(c: Cursor) {
        adapter.updateItems(c)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
