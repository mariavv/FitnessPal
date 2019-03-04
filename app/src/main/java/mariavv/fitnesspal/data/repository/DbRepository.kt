package mariavv.fitnesspal.data.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import mariavv.fitnesspal.data.db.CName
import mariavv.fitnesspal.data.db.SQLiteHelper
import mariavv.fitnesspal.data.db.TName
import mariavv.fitnesspal.domain.Dish
import mariavv.fitnesspal.domain.Food

class DbRepository internal constructor() {
    private val sqliteHelper: SQLiteHelper

    private val sqLiteDatabase: SQLiteDatabase
        get() = sqliteHelper.writableDatabase

    internal val foodsFromHandbook: Cursor
        get() {
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

    internal val journalDaysCount: Cursor
        get() {
            val q = ("select count( " + CName.DATE + " ) as count"
                    + " from ( select distinct " + CName.DATE + " from " + TName.DISHES + ")")
            return sqLiteDatabase.rawQuery(q, null)
        }

    internal val journalDates: Cursor
        get() {
            val q = ("select distinct " + CName.DATE
                    + " from " + TName.DISHES + " order by " + CName.DATE)
            return sqLiteDatabase.rawQuery(q, null)
        }

    init {
        sqliteHelper = SQLiteHelper()
    }

    internal fun insertFoodInHandbook(food: Food): Long {
        val cv = ContentValues()
        cv.put(CName.NAME, food.name)
        cv.put(CName.PROTEIN, food.protein)
        cv.put(CName.FAT, food.fat)
        cv.put(CName.CARB, food.carb)
        cv.put(CName.SORTABLE_NAME, food.name.toLowerCase())
        return sqLiteDatabase.insert(TName.FOODS, null, cv)
    }

    internal fun insertDishInJournal(dish: Dish): Long {
        val cv = ContentValues()
        cv.put(CName.MEAL, dish.meal)
        cv.put(CName.DATE, dish.date.time)
        cv.put(CName.DISH_ID, dish.foodId)
        cv.put(CName.WEIGHT, dish.weight)
        return sqLiteDatabase.insert(TName.DISHES, null, cv)
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
}
