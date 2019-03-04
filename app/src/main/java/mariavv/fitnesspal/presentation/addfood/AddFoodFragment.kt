package mariavv.fitnesspal.presentation.addfood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_add_food.*
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric

class AddFoodFragment : MvpAppCompatFragment(), AddFoodView, FrmFabric.IFragment {

    @InjectPresenter
    lateinit var presenter: AddFoodPresenter

    override val name: String
        get() = Const.Screen.ADD_FOOD

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViews()
    }

    private fun configureViews() {
        addBtn.setOnClickListener { presenter.onAddClick(foodEd.text, proteinEd.text, fatEd.text, carbEd.text) }

        carbEd.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                addBtn.callOnClick()
            }
            false
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun setTitle() {
        val activity = activity
        activity?.setTitle(R.string.add_food_title)
    }
}
