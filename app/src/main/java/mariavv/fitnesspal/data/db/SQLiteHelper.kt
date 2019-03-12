package mariavv.fitnesspal.data.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import mariavv.fitnesspal.App

class SQLiteHelper() : SQLiteOpenHelper(App.context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(" create table " + TName.FOODS + " ( "
                + CName.ID + " integer primary key, "
                + CName.NAME + " text UNIQUE NOT NULL, "
                + CName.PROTEIN + " integer NOT NULL, "
                + CName.FAT + " integer NOT NULL, "
                + CName.CARB + " integer NOT NULL, "
                + CName.SORTABLE_NAME + " text NOT NULL "
                + " ); "
        )
        db.execSQL(" create table " + TName.DISHES + " ( "
                + CName.ID + " integer primary key, "
                + CName.MEAL + " string NOT NULL, "
                + CName.DATE + " integer NOT NULL, "
                + CName.DISH_ID + " integer NOT NULL, "
                + CName.WEIGHT + " integer NOT NULL, "
                + "FOREIGN KEY (" + CName.DISH_ID + " ) REFERENCES " + TName.FOODS
                + " ( " + CName.ID + " ) ON DELETE CASCADE ON UPDATE CASCADE "
                + " ); "
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        val DB_NAME = "fitness_pal"
        private val DB_VERSION = 2
    }
}
