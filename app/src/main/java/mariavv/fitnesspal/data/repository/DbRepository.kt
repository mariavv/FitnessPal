package mariavv.fitnesspal.data.repository

import android.arch.persistence.room.Room
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import mariavv.fitnesspal.App
import mariavv.fitnesspal.data.db.AppDatabase
import mariavv.fitnesspal.data.db.CName
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

    //private var db: AppDatabase = Room.databaseBuilder(App.context,
    //        AppDatabase::class.java, SQLiteHelper.DB_NAME).build()

    private var foodsListener: FoodsListener? = null

    interface FoodsListener {
        fun onAddFood(id: Long)
    }

    private val sqLiteDatabase: SQLiteDatabase
        get() = sqliteHelper.writableDatabase

    internal val foodsFromHandbook: /*Flowable<List<mariavv.fitnesspal.data.db.handbook.Food>>*/Cursor
        get() {
            var db: AppDatabase = Room.databaseBuilder(App.context,
                    AppDatabase::class.java, /*SQLiteHelper.DB_NAME*/"fit").fallbackToDestructiveMigration().build()
            val handbookDao = db.handbookDao()
            //Completable.fromRunnable { handbookDao.insert(mariavv.fitnesspal.data.db.handbook.Food(1,"f", 1, 1,1,  "f")) }
            val foods = handbookDao.getAll()

            val q = ("select hb." + CName.NAME
                    + " , hb." + CName.PROTEIN
                    + " , hb." + CName.FAT + " , hb." + CName.CARB
                    + ", hb." + CName.SORTABLE_NAME
                    + " from " + TName.FOODS + " as hb "
                    + " order by " + CName.SORTABLE_NAME + ", " + CName.NAME)
            return sqLiteDatabase.rawQuery(q, null)
        }

    internal val foodNamesFromHandbook: Cursor
        get() {
            val q = ("select hb." + CName.NAME + ", hb." + CName.SORTABLE_NAME
                    + " from " + TName.FOODS + " as hb "
                    + " order by " + CName.SORTABLE_NAME + ", " + CName.NAME)
            return sqLiteDatabase.rawQuery(q, null)
        }

    internal val journalDaysCount: Int
        get() {
            val q = ("select count( " + CName.DATE + " ) as count"
                    + " from ( select distinct " + CName.DATE + " from " + TName.DISHES + ")")
            val c = sqLiteDatabase.rawQuery(q, null)

            c.moveToFirst()
            val count = c.getInt(0)
            c.close()
            return count
        }

    private val journalDatesCursor: Cursor
        get() {
            val q = ("select distinct " + CName.DATE
                    + " from " + TName.DISHES + " order by " + CName.DATE)
            return sqLiteDatabase.rawQuery(q, null)
        }

    internal val journalDates: ArrayList<Long>
        get() {
            val c = journalDatesCursor

            val dates = ArrayList<Long>()
            while (c.moveToNext()) {
                dates.add(c.getLong(0))
            }
            c.close()
            return dates
        }

    fun getDateByIndex(i: Int): Long {
        val c = journalDatesCursor
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

    internal fun insertFoodInHandbook(food: Food, listener: FoodsListener? = foodsListener): Long {
        val cv = ContentValues()
        cv.put(CName.NAME, food.name)
        cv.put(CName.PROTEIN, food.protein)
        cv.put(CName.FAT, food.fat)
        cv.put(CName.CARB, food.carb)
        cv.put(CName.SORTABLE_NAME, food.name.toLowerCase())

        val id = sqLiteDatabase.insert(TName.FOODS, null, cv)
        if (id > -1) {
            EventBus.getDefault().post(AddFoodEvent())
        }
        if (listener != foodsListener) {
            listener?.onAddFood(id)
        }
        return id
    }

    internal fun insertDishInJournal(dish: Dish): Long {
        val cv = ContentValues()
        cv.put(CName.MEAL, dish.meal)
        cv.put(CName.DATE, dish.date.time)
        cv.put(CName.DISH_ID, dish.foodId)
        cv.put(CName.WEIGHT, dish.weight)

        var id = Long.MIN_VALUE
        try {
            id = sqLiteDatabase.insert(TName.DISHES, null, cv)
            if (id > -1) {
                EventBus.getDefault().post(AddDishEvent())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return id
    }

    internal fun getJournal(date: Long): Cursor {
        val q = ("select " + " hb." + CName.NAME
                + ", j." + CName.MEAL
                + ", j." + CName.DATE
                + " , j." + CName.WEIGHT
                + " , hb." + CName.PROTEIN
                + " , hb." + CName.FAT
                + " , hb." + CName.CARB
                + " from " + TName.FOODS + " as hb, "
                + TName.DISHES + " as j where j." + CName.DATE + " = " + date + " and j."
                + CName.DISH_ID + " = hb." + CName.ID
                + " order by j." + CName.MEAL)
        return sqLiteDatabase.rawQuery(q, null)
    }

    fun clearHandbook() {
        deleteTable(TName.FOODS)
    }

    internal fun clearJournal() {
        deleteTable(TName.DISHES)
    }

    private fun deleteTable(table: String) {
        sqLiteDatabase.delete(table, null, null)
    }

    @Throws(Exception::class)
    internal fun getFoodIdByName(dish: String): Int {
        val q = ("select " + CName.ID
                + " from " + TName.FOODS
                + " where " + CName.NAME + " = '" + dish + "'")
        val c = sqLiteDatabase.rawQuery(q, null)
        c.moveToFirst()
        if (c.count == 0) {
            throw Exception("error")
        }
        val id = c.getInt(0)
        c.close()
        return id
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
