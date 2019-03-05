package mariavv.fitnesspal.domain.interact

import android.annotation.SuppressLint
import android.database.Cursor
import mariavv.fitnesspal.data.repository.DbRepository
import mariavv.fitnesspal.domain.data.Dish
import mariavv.fitnesspal.domain.data.Food
import java.util.*

class DbInteractor {

    private val db: DbRepository = DbRepository()

    val foodsFromHandbook: Cursor
        get() = db.foodsFromHandbook

    val journalDaysCount: Int
        get() {
            val c = db.journalDaysCount
            c.moveToFirst()
            val count = c.getInt(0)
            c.close()
            return count
        }

    val foodList: Cursor
        get() = db.foodNamesFromHandbook

    val journalDates: LongArray
        get() {
            val c = db.journalDates
            val dates = LongArray(c.count)
            var i = 0
            while (c.moveToNext()) {
                dates[i++] = c.getLong(0)
            }
            c.close()
            return dates
        }

    fun getJournal(date: Long): Cursor {
        return db.getJournal(date)
    }

    fun getDateByIndex(i: Int): Long {
        val c = db.journalDates
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

    fun insertDishInJournal(dish: Dish): Long {
        return db.insertDishInJournal(dish)
    }

    fun clearJournal() {
        db.clearJournal()
    }

    fun addDish(date: Date, meal: String, dish: String, weight: Int): Long {
        try {
            return db.insertDishInJournal(Dish(date, meal, db.getFoodIdByName(dish), weight))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return -1
    }

    @SuppressLint("CheckResult")
    fun addFood(food: Food): Long {
        val id = db.insertFoodInHandbook(food)
//todo listener-HandBook.refrash
        return id
    }

    companion object {
        val instance: DbInteractor
            get() = DbInteractor()
    }
}
