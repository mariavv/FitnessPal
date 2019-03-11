package mariavv.fitnesspal.presentation.adddish

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_add_dish.*
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.other.Const
import mariavv.fitnesspal.other.FrmFabric
import mariavv.fitnesspal.presentation.common.BaseFragment

class AddDishFragment : BaseFragment(), AddDishView, FrmFabric.IFragment, DatePickerDialog.OnDateSetListener /*setOnDateSetListener 24api required*/ {
    private lateinit var datePickerDialog: DatePickerDialog

    @InjectPresenter
    lateinit var presenter: AddDishPresenter

    override val name: String
        get() = Const.Screen.ADD_DISH

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarIv.setOnClickListener { presenter.onDateChangeClick() }
        addBtn.setOnClickListener { presenter.onAddClick(fatEd.editableText, carbEd.text) }
        carbEd.setOnEditorActionListener { _, actionId, _ -> presenter.onEditorAction(actionId) }
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        presenter.onDateChange(year, month, dayOfMonth)
    }

    override fun clearFields() {
        carbEd.setText("")
        fatEd.setText("")
    }


    override fun showSelectedDate(date: String) {
        foodEd.text = date
    }

    override fun showDatePickerDialog() {
        datePickerDialog.show()
    }

    override fun initDatePickerDialog(currentYear: Int, currentMonth: Int, currentDay: Int) {
        val context = activity
        if (context != null) {
            datePickerDialog = DatePickerDialog(context, this, currentYear, currentMonth, currentDay)
        }
    }

    override fun configureFoodList(foodList: Array<String>) {
        val context = activity
        if (context != null) {
            val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, foodList)
            fatEd.setAdapter(adapter)
        }
    }

    override fun configureMealsSpinner(meals: Array<String>) {
        val adapter = ArrayAdapter(App.context, android.R.layout.simple_spinner_item, meals)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        proteinEd.adapter = adapter
        proteinEd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.onMealSelected(position)
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {}
        }
    }

    override fun addBtnCallOnClick() {
        addBtn.callOnClick()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}
