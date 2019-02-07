package mariavv.fitnesspal.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Objects;

import mariavv.fitnesspal.model.entity.Dish;
import mariavv.fitnesspal.model.entity.Food;

public class DbManager {

    public static final String FOOD_NAME = SQLiteHelper.HB_COLUMN_NAME;
    public static final String FOOD_PROTEIN = SQLiteHelper.HB_COLUMN_PROTEIN;
    public static final String FOOD_FAT = SQLiteHelper.HB_COLUMN_FAT;
    public static final String FOOD_CARB = SQLiteHelper.HB_COLUMN_CARB;

    private static DbManager instance;

    private SQLiteHelper sqliteHelper;

    private DbManager(Context context) {
        sqliteHelper = new SQLiteHelper(context, "", null, 1);
    }

    public static synchronized DbManager getInstance(Context context) {
        if (instance == null) {
            instance = new DbManager(context);
        }
        return instance;
    }

    public Cursor getFoodsFromHandbook() {
        String sqlQuery = "select distinct hb." + SQLiteHelper.HB_COLUMN_NAME
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb "
                + " order by " + SQLiteHelper.HB_COLUMN_NAME;
        return getSQLiteDatabase().rawQuery(sqlQuery, null);
    }

    public long insertFoodInHandbook(Food food) {
        if (!hasFood(food)) {
            return insertFood(food);
        }
        return -1;
    }

    public Cursor getJournal() {
        String sqlQuery = "select " + " hb." + SQLiteHelper.HB_COLUMN_NAME
                + ", j." + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " , j." + SQLiteHelper.JOURNAL_COLUMN_MASS
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT
                + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb, "
                + SQLiteHelper.JOURNAL_TABLE_NAME + " as j "
                + " order by " + SQLiteHelper.JOURNAL_COLUMN_DATE;
        return getSQLiteDatabase().rawQuery(sqlQuery, null);
    }

    public void clearTables() {
        getSQLiteDatabase().delete(SQLiteHelper.JOURNAL_TABLE_NAME, null, null);
        getSQLiteDatabase().delete(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, null);
    }

    public long insertDishInJournal(Dish dish) {
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.JOURNAL_COLUMN_DATE, dish.getDate().toString());
        cv.put(SQLiteHelper.JOURNAL_COLUMN_HB_ID, dish.getFoodId());
        cv.put(SQLiteHelper.JOURNAL_COLUMN_MASS, dish.getMass());
        return getSQLiteDatabase().insert(SQLiteHelper.JOURNAL_TABLE_NAME, null, cv);
    }

    private long insertFood(Food food) {
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.HB_COLUMN_NAME, food.getName());
        cv.put(SQLiteHelper.HB_COLUMN_PROTEIN, food.getProtein());
        cv.put(SQLiteHelper.HB_COLUMN_FAT, food.getFat());
        cv.put(SQLiteHelper.HB_COLUMN_CARB, food.getCarb());
        return getSQLiteDatabase().insert(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, cv);
    }

    private boolean hasFood(Food food) {
        Cursor c = getSQLiteDatabase().query(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, null, null,
                null, null, null);

        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex(SQLiteHelper.HB_COLUMN_NAME);

            do {
                if (Objects.equals(food.getName(), c.getString(idColIndex))) {
                    c.close();
                    return true;
                }
            } while (c.moveToNext());
        }

        c.close();
        return false;
    }

    private SQLiteDatabase getSQLiteDatabase() {
        return sqliteHelper.getWritableDatabase();
    }

    public Cursor getJournalDaysCount() {
        String sqlQuery = "select count(*) as count"
                + " from ( select distinct " + SQLiteHelper.JOURNAL_COLUMN_DATE + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + ")";
        return getSQLiteDatabase().rawQuery(sqlQuery, null);
    }

    public Cursor getJournalDateByIndex(int i) {
        String sqlQuery = "select " + SQLiteHelper.JOURNAL_COLUMN_DATE + "from ("
                + "select counter(0) as i, " + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + " order by " + SQLiteHelper.JOURNAL_COLUMN_DATE
                + ") where i = " + String.valueOf(i);
        return getSQLiteDatabase().rawQuery(sqlQuery, null);
    }

    // todo
    public class HandBookCursor {
        private final Cursor сursor;

        private HandBookCursor(Cursor c) {
            сursor = c;
        }

        public int getCount() {
            return сursor.getCount();
        }

        public boolean moveToPosition(int pos) {
            return сursor.moveToPosition(pos);
        }

        public String getName() {
            return сursor.getString(сursor.getColumnIndex(SQLiteHelper.HB_COLUMN_NAME));
        }
    }
}
