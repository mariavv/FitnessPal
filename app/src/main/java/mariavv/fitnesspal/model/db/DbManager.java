package mariavv.fitnesspal.model.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Food;

public class DbManager {

    public static final String FOOD_NAME = SQLiteHelper.HB_COLUMN_NAME;
    public static final String FOOD_PROTEIN = SQLiteHelper.HB_COLUMN_PROTEIN;
    public static final String FOOD_FAT = SQLiteHelper.HB_COLUMN_FAT;
    public static final String FOOD_CARB = SQLiteHelper.HB_COLUMN_CARB;

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

    public Cursor getFoodsFromHandbook() {
        final String q = "select distinct hb." + SQLiteHelper.HB_COLUMN_NAME
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
                + "'" + food.name + "'" + ", " + food.protein + ", " + food.fat + ", " + food.carb + ") ";

        getSQLiteDatabase().execSQL(q);
    }

    public void insertDishInJournal(Dish dish) {
        final String q = "insert or ignore into " + SQLiteHelper.JOURNAL_TABLE_NAME
                + " values ( "
                + "'" + dish.date.toString() + "'" + ", " + dish.foodId + ", " + dish.mass + ") ";
        getSQLiteDatabase().execSQL(q);
    }

    public Cursor getJournal() {
        final String q = "select " + " hb." + SQLiteHelper.HB_COLUMN_NAME
                + ", j." + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " , j." + SQLiteHelper.JOURNAL_COLUMN_MASS
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT
                + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb, "
                + SQLiteHelper.JOURNAL_TABLE_NAME + " as j "
                + " order by " + SQLiteHelper.JOURNAL_COLUMN_DATE;
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public void clearTables() {
        getSQLiteDatabase().delete(SQLiteHelper.JOURNAL_TABLE_NAME, null, null);
        getSQLiteDatabase().delete(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, null);
    }

    private SQLiteDatabase getSQLiteDatabase() {
        return sqliteHelper.getWritableDatabase();
    }

    public Cursor getJournalDaysCount() {
        String q = "select count(*) as count"
                + " from ( select distinct " + SQLiteHelper.JOURNAL_COLUMN_DATE + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + ")";
        q = "select 1";
        return getSQLiteDatabase().rawQuery(q, null);
    }

    public Cursor getJournalDateByIndex(int i) {
        final String q = "select " + SQLiteHelper.JOURNAL_COLUMN_DATE + "from ("
                + "select counter(0) as i, " + SQLiteHelper.JOURNAL_COLUMN_DATE
                + " from " + SQLiteHelper.JOURNAL_TABLE_NAME + " order by " + SQLiteHelper.JOURNAL_COLUMN_DATE
                + ") where i = " + String.valueOf(i);
        return getSQLiteDatabase().rawQuery(q, null);
    }

    // todo
    public class HandBookCursor {
        private final Cursor сursor;

        private HandBookCursor(@NonNull Cursor c) {
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
