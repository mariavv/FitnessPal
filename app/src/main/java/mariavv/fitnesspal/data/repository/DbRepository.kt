package mariavv.fitnesspal.data.repository

import android.arch.persistence.room.Room
import android.database.Cursor
import io.reactivex.Flowable
import mariavv.fitnesspal.App
import mariavv.fitnesspal.data.db.Db
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.Food
import mariavv.fitnesspal.other.eventbus.AddDishEvent
import mariavv.fitnesspal.other.eventbus.AddFoodEvent
import org.greenrobot.eventbus.EventBus
import java.util.*


class DbRepository {
    private var db: Db = Room.databaseBuilder(App.context,
            Db::class.java, DB_NAME).fallbackToDestructiveMigration().build()

    private var foodsListener: FoodsListener? = null

    interface FoodsListener {
        fun onAddFood(id: Long)
    }

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
                Food(
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

    internal fun getJournalDaysCount(): Flowable<Int> {
        return db.dishDao().getJournalDaysCount()
    }

    internal fun getJournalDates(): Flowable<ArrayList<Long>> {
        return Flowable.fromCallable {
            val c = db.dishDao().getJournalDates()
            val dates = ArrayList<Long>()
            if (c.moveToFirst()) {
                do {
                    dates.add(c.getLong(0))
                } while (c.moveToNext())
            }
            c.close()
            dates
        }
    }

    fun getDateByIndex(i: Int): Flowable<Long> {
        return Flowable.fromCallable {
            val c = db.dishDao().getJournalDates()
            if (c.moveToPosition(i)) {
                val date = c.getLong(0)
                c.close()
                date
            } else {
                c.close()
                Date().time
            }
        }
    }

    internal fun insertDishInJournal(dish: Dish): Long {
        val id = db.dishDao().insert(dish)

        if (id > -1) {
            EventBus.getDefault().post(AddDishEvent())
        }
        return id
    }

    internal fun getJournal(date: Long): Flowable<Cursor> {
        return Flowable.fromCallable {
            db.dishDao().getJournal(date)
        }
    }

    private object Holder {
        val INSTANCE = DbRepository()
    }

    companion object {
        private const val DB_NAME = "fitness_pal"

        val instance: DbRepository by lazy {
            Holder.INSTANCE
        }
    }
}
