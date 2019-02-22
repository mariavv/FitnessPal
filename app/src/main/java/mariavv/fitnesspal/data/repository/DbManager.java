package mariavv.fitnesspal.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mariavv.fitnesspal.data.db.CName;
import mariavv.fitnesspal.data.db.SQLiteHelper;
import mariavv.fitnesspal.data.db.TName;
import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;

public class DbManager {
    private SQLiteHelper sqliteHelper;

    DbManager() {
        sqliteHelper = new SQLiteHelper(null);
    }

    private SQLiteDatabase getSQLiteDatabase() {
        return sqliteHelper.getWritableDatabase();
    }

    Cursor getFoodsFromHandbook() {
        final String q = "select hb." + CName.NAME
                + " , hb." + CName.PROTEIN
                + " , hb." + CName.FAT + " , hb." + CName.CARB
                + ", hb." + CName.SORTABLE_NAME
                + " from " + TName.FOODS + " as hb "
                + " order by " + CName.SORTABLE_NAME + ", " + CName.NAME;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    Cursor getFoodNamesFromHandbook() {
        final String q = "select hb." + CName.NAME + ", hb." + CName.SORTABLE_NAME
                + " from " + TName.FOODS + " as hb "
                + " order by " + CName.SORTABLE_NAME + ", " + CName.NAME;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    long insertFoodInHandbook(Food food) {
        /*final String q = "insert or ignore into " + TName.FOODS + " ("
                + CName.NAME + ", " + CName.PROTEIN
                + ", " + CName.FAT + ", " + CName.CARB + ", " + CName.SORTABLE_NAME + ") "
                + " values ( "
                + "'" + food.getName() + "'" + ", " + food.getProtein()
                + ", " + food.getFat() + ", " + food.getCarb() + ", " + "'" + food.getName().toLowerCase() + "'" + ") ";*/

        final ContentValues cv = new ContentValues();
        cv.put(CName.NAME, food.getName());
        cv.put(CName.PROTEIN, food.getProtein());
        cv.put(CName.FAT, food.getFat());
        cv.put(CName.CARB, food.getCarb());
        cv.put(CName.SORTABLE_NAME, food.getName().toLowerCase());
        return getSQLiteDatabase().insert(TName.FOODS, null, cv);
    }

    long insertDishInJournal(Dish dish) {
        final String q = "insert into " + TName.DISHES + " ("
                + CName.MEAL + ", "
                + CName.DATE + ", "
                + CName.DISH_ID + ", "
                + CName.WEIGHT + ") "
                + " values ( '" + dish.meal + "', "
                + "'" + dish.date.toString() + "'" + ", " + dish.foodId + ", " + dish.weight + ") ";

        final ContentValues cv = new ContentValues();
        cv.put(CName.MEAL, dish.meal);
        cv.put(CName.DATE, dish.date.toString());
        cv.put(CName.DISH_ID, dish.foodId);
        cv.put(CName.WEIGHT, dish.weight);
        return getSQLiteDatabase().insert(TName.DISHES, null, cv);
    }

    Cursor getJournal(String date) {
        final String q = "select distinct " + " hb." + CName.NAME
                + ", j." + CName.MEAL
                + ", j." + CName.DATE
                + " , j." + CName.WEIGHT
                + " , hb." + CName.PROTEIN
                + " , hb." + CName.FAT
                + " , hb." + CName.CARB
                + " from " + TName.FOODS + " as hb, "
                + TName.DISHES + " as j where j." + CName.DATE + " = '" + date + "' and j."
                + CName.DISH_ID + " = hb." + CName.ID
                + " order by j." + CName.MEAL;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public void clearHandbook() {
        deleteTable(TName.FOODS);
    }

    void clearJournal() {
        deleteTable(TName.DISHES);
    }

    private void deleteTable(String table) {
        getSQLiteDatabase().delete(table, null, null);
    }

    Cursor getJournalDaysCount() {
        final String q = "select count( " + CName.DATE + " ) as count"
                + " from ( select distinct " + CName.DATE + " from " + TName.DISHES + ")";
        return getSQLiteDatabase().rawQuery(q, null);
    }

    Cursor getJournalDates() {
        final String q = "select distinct " + CName.DATE
                + " from " + TName.DISHES + " order by " + CName.DATE + " desc ";
        return getSQLiteDatabase().rawQuery(q, null);
    }

    int getFoodIdByName(String dish) throws Exception {
        final String q = "select " + CName.ID
                + " from " + TName.FOODS
                + " where " + CName.NAME + " = '" + dish + "'";
        final Cursor c = getSQLiteDatabase().rawQuery(q, null);
        c.moveToFirst();
        if (c.getCount() == 0) {
            throw new Exception("error");
        }
        int id = c.getInt(0);
        c.close();
        return id;
    }
}
