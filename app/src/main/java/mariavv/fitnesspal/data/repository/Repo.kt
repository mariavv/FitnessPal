package mariavv.fitnesspal.data.repository

import android.database.Cursor
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.Food
import java.util.*

class Repo /*private constructor()*/ {

    private val db: DbRepository

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

    init {
        db = DbRepository()
    }

    fun getJournal(date: Long): Cursor {
        return db.getJournal(date)
    }

    fun insertFoodInHandbook(food: Food) {
        db.insertFoodInHandbook(food)
    }

    fun getDateByIndex(i: Int): Long {
        val c = db.journalDates
        if (i > -1 && i < c.count) {
            c.moveToPosition(i)
            val date = c.getLong(0)
            c.close()
            return date
        } else {
            c.close()
            return Date().time
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

    fun addFood(food: Food): Long {
        return db.insertFoodInHandbook(food)
    }

    companion object {

        val instance: Repo
            get() = Repo()


        /*@Synchronized
        fun getInstance(): Repo {
            if (instance == null) {
                instance = Repo()
            }
            return instance
        }*/
    }
}
