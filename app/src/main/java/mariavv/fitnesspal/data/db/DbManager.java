package mariavv.fitnesspal.data.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;

public class DbManager {
    private SQLiteHelper sqliteHelper;

    public DbManager() {
        sqliteHelper = new SQLiteHelper(null);
    }

    private SQLiteDatabase getSQLiteDatabase() {
        return sqliteHelper.getWritableDatabase();
    }

    public Cursor getFoodsFromHandbook() {
        final String q = "select hb." + CName.NAME
                + " , hb." + CName.PROTEIN
                + " , hb." + CName.FAT + " , hb." + CName.CARB
                + " from " + TName.FOODS + " as hb "
                + " order by " + CName.NAME;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public Cursor getFoodNamesFromHandbook() {
        final String q = "select hb." + CName.NAME
                + " from " + TName.FOODS + " as hb "
                + " order by " + CName.NAME;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public void insertFoodInHandbook(Food food) {
        final String q = "insert or ignore into " + TName.FOODS + " ("
                + CName.NAME + ", " + CName.PROTEIN
                + ", " + CName.FAT + ", " + CName.CARB + ") "
                + " values ( "
                + "'" + food.getName() + "'" + ", " + food.getProtein()
                + ", " + food.getFat() + ", " + food.getCarb() + ") ";

        getSQLiteDatabase().execSQL(q);
    }

    public void insertDishInJournal(Dish dish) {
        final String q = "insert into " + TName.DISHES + " ("
                + CName.MEAL + ", "
                + CName.DATE + ", "
                + CName.DISH_ID + ", "
                + CName.WEIGHT + ") "
                + " values ( " + String.valueOf(dish.meal) + ", "
                + "'" + dish.date.toString() + "'" + ", " + dish.foodId + ", " + dish.weight + ") ";
        getSQLiteDatabase().execSQL(q);
    }

    public Cursor getJournal(String date) {
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

    public void clearJournal() {
        deleteTable(TName.DISHES);
    }

    private void deleteTable(String table) {
        getSQLiteDatabase().delete(table, null, null);
    }

    public Cursor getJournalDaysCount() {
        final String q = "select count( " + CName.DATE + " ) as count"
                + " from ( select distinct " + CName.DATE + " from " + TName.DISHES + ")";
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public Cursor getJournalDates() {
        final String q = "select distinct " + CName.DATE
                + " from " + TName.DISHES + " order by " + CName.DATE;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public int getFoodIdByName(String dish) throws Exception {
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
