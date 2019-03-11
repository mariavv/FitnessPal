package mariavv.fitnesspal.presentation.addfood

import android.text.Editable
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.Food

@InjectViewState
class AddFoodPresenter : MvpPresenter<AddFoodView>(), DbRepository.FoodsListener {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
    }

    internal fun onAddClick(foodEdText: Editable, proteinEdText: Editable, fatEdText: Editable, carbEdText: Editable) {
        if (proteinEdText.isEmpty() || fatEdText.isEmpty() || carbEdText.isEmpty()) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.some_empty_fields_message))
            return
        }

        val protein = Integer.valueOf(proteinEdText.toString())
        val fat = Integer.valueOf(fatEdText.toString())
        val carb = Integer.valueOf(carbEdText.toString())

        if (protein + fat + carb > 100) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.error_pfc_summ_message))
            return
        }

        val food = Food(foodEdText.toString(), protein, fat, carb)
        DbRepository.instance.insertFoodInHandbook(food, this)
    }

    internal fun onBackPressed() {
        App.getRouter().exit()
    }

    override fun onAddFood(id: Long) {
        if (id > -1) {
            App.getRouter().exitWithMessage(App.getAppString(R.string.add_message))
        } else {
            App.getRouter().showSystemMessage(App.getAppString(R.string.add_fail_message))
        }
    }
}
