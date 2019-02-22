package mariavv.fitnesspal.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mariavv.fitnesspal.FitnessPal;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fitness_pal";
    private static final int DB_VERSION = 2;

    public SQLiteHelper(SQLiteDatabase.CursorFactory factory) {
        super(FitnessPal.appContext, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TName.FOODS + " ( "
                + CName.ID + " integer primary key, "
                + CName.NAME + " text UNIQUE NOT NULL, "
                + CName.PROTEIN + " integer NOT NULL, "
                + CName.FAT + " integer NOT NULL, "
                + CName.CARB + " integer NOT NULL, "
                + CName.SORTABLE_NAME + " text NOT NULL "
                + " ); "
        );
        db.execSQL(" create table " + TName.DISHES + " ( "
                + CName.ID + " integer primary key, "
                + CName.MEAL + " string NOT NULL, "
                + CName.DATE + " integer NOT NULL, "
                + CName.DISH_ID + " integer NOT NULL, "
                + CName.WEIGHT + " integer NOT NULL, "
                + "FOREIGN KEY (" + CName.DISH_ID + " ) REFERENCES " + TName.FOODS
                + " ( " + CName.ID + " ) ON DELETE CASCADE ON UPDATE CASCADE "
                + " ); "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
