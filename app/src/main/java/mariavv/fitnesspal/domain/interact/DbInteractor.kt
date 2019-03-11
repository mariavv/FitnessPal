package mariavv.fitnesspal.domain.interact

import android.database.Cursor
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.data.Dish
import mariavv.fitnesspal.domain.data.Food
import mariavv.fitnesspal.other.eventbus.AddDishEvent
import mariavv.fitnesspal.other.eventbus.AddFoodEvent
import mariavv.fitnesspal.other.eventbus.Status
import org.greenrobot.eventbus.EventBus
import java.util.*

class DbInteractor {

    private var foodsListener: FoodsListener? = null

    val foodsFromHandbook: Cursor
        get() = DbRepository.instance.foodsFromHandbook

    val journalDaysCount: Int
        get() {
            val c = DbRepository.instance.journalDaysCount
            c.moveToFirst()
            val count = c.getInt(0)
            c.close()
            return count
        }

    val foodList: Cursor
        get() = DbRepository.instance.foodNamesFromHandbook

    val journalDates: LongArray
        get() {
            val c = DbRepository.instance.journalDates
            val dates = LongArray(c.count)
            var i = 0
            while (c.moveToNext()) {
                dates[i++] = c.getLong(0)
            }
            c.close()
            return dates
        }

    fun getJournal(date: Long): Cursor {
        return DbRepository.instance.getJournal(date)
    }

    fun getDateByIndex(i: Int): Long {
        val c = DbRepository.instance.journalDates
        return if (i > -1 && i < c.count) {
            c.moveToPosition(i)
            val date = c.getLong(0)
            c.close()
            date
        } else {
            c.close()
            Date().time
        }
    }

    //for test data
    fun insertDishInJournal(dish: Dish): Long {
        return DbRepository.instance.insertDishInJournal(dish)
    }

    fun addDish(date: Date, meal: String, dish: String, weight: Int): Long {
        var id = Long.MIN_VALUE
        try {
            id = DbRepository.instance.insertDishInJournal(Dish(date, meal, DbRepository.instance.getFoodIdByName(dish), weight))
            if (id > -1) {
                EventBus.getDefault().post(AddDishEvent(Status.SUCCESS))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return id
    }

    fun addFood(food: Food, listener: FoodsListener? = foodsListener) {
        val id = DbRepository.instance.insertFoodInHandbook(food)
        if (id > -1) {
            EventBus.getDefault().post(AddFoodEvent(Status.SUCCESS))
        }

        if (listener != foodsListener) {
            listener?.onAddFood(id)
        }
    }

    interface FoodsListener {
        fun onAddFood(id: Long)
    }
}
