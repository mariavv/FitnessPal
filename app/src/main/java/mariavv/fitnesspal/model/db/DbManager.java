package mariavv.fitnesspal.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Food;

public class DbManager {

    public static final String FOOD_NAME = SQLiteHelper.HB_COLUMN_NAME;
    public static final String FOOD_PROTEIN = SQLiteHelper.HB_COLUMN_PROTEIN;
    public static final String FOOD_FAT = SQLiteHelper.HB_COLUMN_FAT;
    public static final String FOOD_CARB = SQLiteHelper.HB_COLUMN_CARB;
    public static final String JOURNAL_MEAL_NUM = SQLiteHelper.JOURNAL_COLUMN_MEAL_NUM;
    public static final String JOURNAL_DATE = SQLiteHelper.JOURNAL_COLUMN_DATE;
    public static final String JOURNAL_WEIGHT = SQLiteHelper.JOURNAL_COLUMN_WEIGHT;

    private static DbManager instance;

    private SQLiteHelper sqliteHelper;

    private DbManager() {
        sqliteHelper = new SQLiteHelper("", null, 1);
    }

    public static synchronized DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    private SQLiteDatabase getSQLiteDatabase() {
        return sqliteHelper.getWritableDatabase();
    }

    public Cursor getFoodsFromHandbook() {
        final String q = "select hb." + SQLiteHelper.HB_COLUMN_NAME
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb "
                + " order by " + SQLiteHelper.HB_COLUMN_NAME;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public void insertFoodInHandbook(Food food) {
        final String q = "insert or ignore into " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " ("
                + SQLiteHelper.HB_COLUMN_NAME + ", " + SQLiteHelper.HB_COLUMN_PROTEIN
                + ", " + SQLiteHelper.HB_COLUMN_FAT + ", " + SQLiteHelper.HB_COLUMN_CARB + ") "
                + " values ( "
                + "'" + food.name.value + "'" + ", " + food.macroNutrients.protein
                + ", " + food.macroNutrients.fat + ", " + food.macroNutrients.carb + ") ";

        getSQLiteDatabase().execSQL(q);
    }

    public void insertDishInJournal(Dish dish) {
        final String q = "insert into " + SQLiteHelper.JOURNAL_TABLE_NAME + " ("
                + SQLiteHelper.JOURNAL_COLUMN_MEAL_NUM + ", "
                + SQLiteHelper.JOURNAL_COLUMN_DATE + ", "
                + SQLiteHelper.JOURNAL_COLUMN_HB_ID + ", "
                + SQLiteHelper.JOURNAL_COLUMN_WEIGHT + ") "
                + " values ( " + String.valueOf(dish.mealNum.value) + ", "
                + "'" + dish.date.toString() + "'" + ", " + dish.foodId + ", " + dish.weight.value + ") ";
        getSQLiteDatabase().execSQL(q);
    }

    public Cursor getJournal(String date) {
        final String q = "select distinct " + " hb." + SQLiteHelper.HB_COLUMN_NAME
                + ", j." + SQLiteHelper.JOURNAL_COLUMN_MEAL_NUM
                + ", j." + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " , j." + SQLiteHelper.JOURNAL_COLUMN_WEIGHT
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT
                + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb, "
                + SQLiteHelper.JOURNAL_TABLE_NAME + " as j where j." + SQLiteHelper.JOURNAL_COLUMN_DATE + " = '" + date + "' and j."
                + SQLiteHelper.JOURNAL_COLUMN_HB_ID + " = hb." + SQLiteHelper.HB_COLUMN_ID
                + " order by j." + SQLiteHelper.JOURNAL_COLUMN_MEAL_NUM;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public void clearTables() {
        clearJournal();
        clearHandbook();
    }

    public void clearHandbook() {
        deleteTable(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME);
    }

    public void clearJournal() {
        deleteTable(SQLiteHelper.JOURNAL_TABLE_NAME);
    }

    private void deleteTable(String table) {
        getSQLiteDatabase().delete(table, null, null);
    }

    public Cursor getJournalDaysCount() {
        //иногда count = 0
        /*String qq = "select count( " + SQLiteHelper.JOURNAL_COLUMN_DATE + " ) as count"
                + " from ( select distinct " + SQLiteHelper.JOURNAL_COLUMN_DATE + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + ")";
        Cursor c = getSQLiteDatabase().rawQuery(qq, null);
        c.moveToFirst();
        int f = c.getInt(0);*/

        final String q = "select count( " + SQLiteHelper.JOURNAL_COLUMN_DATE + " ) as count"
                + " from ( select distinct " + SQLiteHelper.JOURNAL_COLUMN_DATE + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + ")";
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public Cursor getJournalDates() {
        final String q = "select distinct " + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + " order by " + SQLiteHelper.JOURNAL_COLUMN_DATE;
        return getSQLiteDatabase().rawQuery(q, null);
    }
}
