package mariavv.fitnesspal.data.repository

import android.arch.persistence.room.Room
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import io.reactivex.Flowable
import mariavv.fitnesspal.App
import mariavv.fitnesspal.data.db.AppDatabase
import mariavv.fitnesspal.data.db.SQLiteHelper
import mariavv.fitnesspal.data.db.TName
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.other.eventbus.AddDishEvent
import mariavv.fitnesspal.other.eventbus.AddFoodEvent
import org.greenrobot.eventbus.EventBus
import java.util.*


class DbRepository {
    private val sqliteHelper: SQLiteHelper = SQLiteHelper()

    private var db: AppDatabase = Room.databaseBuilder(App.context,
            AppDatabase::class.java, SQLiteHelper.DB_NAME + "_room").fallbackToDestructiveMigration().build()

    private var foodsListener: FoodsListener? = null

    interface FoodsListener {
        fun onAddFood(id: Long)
    }

    private val sqLiteDatabase: SQLiteDatabase
        get() = sqliteHelper.writableDatabase

    internal val foodsFromHandbook: Flowable<Cursor>
        get() {
            return Flowable.fromCallable { db.handbookDao().getAll() }
        }

    internal val foodNamesFromHandbook: Flowable<Cursor>
        get() {
            return Flowable.fromCallable { db.handbookDao().getFoodNames() }
        }

    //todo  = foodsListener
    internal fun insertFoodInHandbook(food: Food, listener: FoodsListener? = foodsListener): Long {
        val id = db.handbookDao().insert(
                mariavv.fitnesspal.data.db.handbook.Food(
                        name = food.name, protein = food.protein, fat = food.fat,
                        carb = food.carb, sortableName = food.name.toLowerCase()
                )
        )

        if (id > -1) {
            EventBus.getDefault().post(AddFoodEvent())
        }
        if (listener != foodsListener) {
            listener?.onAddFood(id)
        }
        return id
    }

    @Throws(Exception::class)
    internal fun getFoodIdByName(dish: String): Flowable<Int> {
        return db.handbookDao().getFoodIdByName(dish)
    }

    fun clearHandbook() {
        deleteTable(TName.FOODS)
    }

    internal fun clearJournal() {
        deleteTable(TName.DISHES)
    }

    internal fun journalDaysCount(): Flowable<Int> {
        return db.dishDao().getJournalDaysCount()
    }

    internal fun journalDates(): Flowable<ArrayList<Long>> {
        return Flowable.fromCallable {
            val c = db.dishDao().getJournalDates()

            val dates = ArrayList<Long>()
            c.moveToFirst()
            while (c.moveToNext()) {
                dates.add(c.getLong(0))
            }
            c.close()
            dates
        }
    }

    fun getDateByIndex(i: Int): Flowable<Long> {
        return Flowable.fromCallable {
            val c = db.dishDao().getJournalDates()
            if (i > -1 && i < c.count) {
                c.moveToPosition(i)
                val date = c.getLong(0)
                c.close()
                date
            } else {
                c.close()
                Date().time
            }
        }
    }

    /*fun getDateByIndex(i: Int): Flowable<Long> {
        val c = db.dishDao().getJournalDates()
        return if (i > -1 && i < c.count) {
            c.moveToPosition(i)
            val date = c.getLong(0)
            c.close()
            Flowable.fromCallable { date }
        } else {
            c.close()
            Flowable.fromCallable { Date().time }
        }
    }*/

    internal fun insertDishInJournal(dish: Dish): Long {
        val id = db.dishDao().insert(
                mariavv.fitnesspal.data.db.jounal.Dish(
                        mealNum = dish.meal, date = dish.date.time,
                        handbookId = dish.foodId, mass = dish.weight
                )
        )
        /*val cv = ContentValues()
        cv.put(CName.MEAL, dish.meal)
        cv.put(CName.DATE, dish.date.time)
        cv.put(CName.DISH_ID, dish.foodId)
        cv.put(CName.WEIGHT, dish.weight)

        var id = Long.MIN_VALUE
        try {
            id = sqLiteDatabase.insert(TName.DISHES, null, cv)*/
        if (id > -1) {
            EventBus.getDefault().post(AddDishEvent())
        }
        /*} catch (e: Exception) {
            e.printStackTrace()
        }*/

        return id
    }

    internal fun getJournal(date: Long): Flowable<Cursor> {
        return Flowable.fromCallable {
            db.dishDao().getJournal(date)
        }
    }

    private fun deleteTable(table: String) {
        sqLiteDatabase.delete(table, null, null)
    }

    private object Holder {
        val INSTANCE = DbRepository()
    }

    companion object {
        val instance: DbRepository by lazy {
            Holder.INSTANCE
        }
    }
}
