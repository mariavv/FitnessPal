package mariavv.fitnesspal.model.repository;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import java.util.Date;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.entity.Dish;
import mariavv.fitnesspal.model.entity.Food;

public class Repo {

    private static Repo instance;

    private DbManager db;

    private Repo(Context context) {
        db = DbManager.getInstance(context);
    }

    @Nullable
    public static synchronized Repo getInstance() {
        return instance;
    }

    public static synchronized Repo getInstance(Context context) {
        if (instance == null) {
            instance = new Repo(context);
        }
        return instance;
    }

    public Cursor getFoodsFromHandbook() {
        return db.getFoodsFromHandbook();
    }

    public Cursor getJournal() {
        return db.getJournal();
    }

    public long insertFoodInHandbook(Food food) {
        return db.insertFoodInHandbook(food);
    }

    public int getJournalDaysCount() {
        Cursor c = db.getJournalDaysCount();
        c.moveToFirst();
        int i = c.getCount();
        int f = c.getPosition();
        return db.getJournalDaysCount().getInt(0);
    }

    public Date getDateByIndex(int i) {
        String date = db.getJournalDateByIndex(i).getString(0);
        return new Date();
    }

    public void insertDishInJournal(Dish dish) {
        db.insertDishInJournal(dish);
    }
}
