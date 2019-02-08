package mariavv.fitnesspal.model.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mariavv.fitnesspal.FitnessPal;

class SQLiteHelper extends SQLiteOpenHelper {

    static final String FOOD_HANDBOOK_TABLE_NAME = "food_handbook";
    static final String JOURNAL_TABLE_NAME = "journal";

    static final String HB_COLUMN_ID = "id";
    static final String HB_COLUMN_NAME = "name";
    static final String HB_COLUMN_PROTEIN = "protein";
    static final String HB_COLUMN_FAT = "fat";
    static final String HB_COLUMN_CARB = "carb";

    private static final String JOURNAL_COLUMN_ID = "id";
    static final String JOURNAL_COLUMN_DATE = "date";
    static final String JOURNAL_COLUMN_HB_ID = "handbook_id";
    static final String JOURNAL_COLUMN_MASS = "mass";

    private static final String DB_NAME = "fitness_pal";
    private static final int DB_VERSION = 2;

    SQLiteHelper(String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(FitnessPal.appContext, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + FOOD_HANDBOOK_TABLE_NAME + " ( "
                + HB_COLUMN_ID + " integer primary key, "
                + HB_COLUMN_NAME + " text UNIQUE, "
                + HB_COLUMN_PROTEIN + " integer, "
                + HB_COLUMN_FAT + " integer, "
                + HB_COLUMN_CARB + " integer "
                + " ); "
        );
        db.execSQL(" create table " + JOURNAL_TABLE_NAME + " ( "
                + JOURNAL_COLUMN_ID + " integer primary key, "
                + JOURNAL_COLUMN_DATE + " timestamp, "
                + JOURNAL_COLUMN_HB_ID + " integer, "
                + JOURNAL_COLUMN_MASS + " integer, "
                + "FOREIGN KEY (" + JOURNAL_COLUMN_HB_ID + " ) REFERENCES " + FOOD_HANDBOOK_TABLE_NAME
                + " ( " + HB_COLUMN_ID + " ) ON DELETE CASCADE ON UPDATE CASCADE "
                + " ); "
        );
    }

    //todo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + JOURNAL_TABLE_NAME + ";");
        db.execSQL("drop table " + FOOD_HANDBOOK_TABLE_NAME + ";");
        onCreate(db);
    }
}
