package mariavv.fitnesspal.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import mariavv.fitnesspal.model.entity.Food;

public class DBManager {

    private SQLiteDatabase db;

    public DBManager(Context context) {
        SQLiteHelper sqliteHelper = new SQLiteHelper(context, "", null, 1);
        db = sqliteHelper.getWritableDatabase();
    }

    public Cursor getFoodsFromHandbook() {
        String sqlQuery = "select distinct hb." + SQLiteHelper.HB_COLUMN_NAME
                + " , hb." + SQLiteHelper.HB_COLUMN_PROTEIN
                + " , hb." + SQLiteHelper.HB_COLUMN_FAT + " , hb." + SQLiteHelper.HB_COLUMN_CARB
                + " from " + SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME + " as hb "
                + " order by " + SQLiteHelper.HB_COLUMN_NAME;
        return db.rawQuery(sqlQuery, null);
    }

    public long insertFoodInHandbook(Food food) {
        ContentValues cv = new ContentValues();
        cv.put(SQLiteHelper.HB_COLUMN_NAME, food.getName());
        cv.put(SQLiteHelper.HB_COLUMN_PROTEIN, food.getProtein());
        cv.put(SQLiteHelper.HB_COLUMN_FAT, food.getFat());
        cv.put(SQLiteHelper.HB_COLUMN_CARB, food.getCarb());
        return db.insert(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, cv);
    }

    public void clearTables() {
        db.delete(SQLiteHelper.JOURNAL_TABLE_NAME, null, null);
        db.delete(SQLiteHelper.FOOD_HANDBOOK_TABLE_NAME, null, null);
    }
}
