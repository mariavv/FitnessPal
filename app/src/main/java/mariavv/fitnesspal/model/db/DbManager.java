package mariavv.fitnesspal.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Objects;

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

    public void clearTables() {
        getSQLiteDatabase().delete(SQLiteHelper.JOURNAL_TABLE_NAME, null, null);
        getSQLiteDatabase().delete(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, null);
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

    // todo
    public class HandBookCursor {
        private final Cursor mCursor;

        private HandBookCursor(Cursor c) {
            mCursor = c;
        }

        public int getCount() {
            return mCursor.getCount();
        }

        public boolean moveToPosition(int pos) {
            return mCursor.moveToPosition(pos);
        }

        public String getName() {
            return mCursor.getString(mCursor.getColumnIndex(SQLiteHelper.HB_COLUMN_NAME));
        }
    }
}
