package mariavv.fitnesspal.presentation.addfood

import android.text.Editable
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import mariavv.fitnesspal.App
import mariavv.fitnesspal.R
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.other.Const

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

        if (protein + fat + carb > MAX_NUTRIENTS) {
            App.getRouter().showSystemMessage(App.getAppString(R.string.error_pfc_summ_message))
            return
        }

        /*Single.fromCallable {
            DbRepository.instance.db.handbookDao().insert(mariavv.fitnesspal.data.db.handbook.Food(
                    name = "f", protein = 1, fat = 1, carb = 1, sortable_name = "f"
            ))
        }.subscribeOn(
                Schedulers.io()
        ).subscribeBy(
                onError = { t ->
                    Log.d(Const.LOG_TAG, "-insert error ${t.message}")
                },
                onSuccess = { id ->
                    Log.d(Const.LOG_TAG, "-insert success, id = $id")
                }
        ).addTo(CompositeDisposable())*/
        Single.fromCallable {
            DbRepository.instance.insertFoodInHandbook(Food(foodEdText.toString(), protein, fat, carb), this)
        }.subscribeOn(
                Schedulers.io()
        ).subscribeBy(
                onError = { t ->
                    Log.d(Const.LOG_TAG, "insert error ${t.message}")
                },
                onSuccess = { id ->
                    Log.d(Const.LOG_TAG, "insert success, id = $id")
                }
        ).addTo(CompositeDisposable())
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

    companion object {
        val MAX_NUTRIENTS = 100
    }
}
