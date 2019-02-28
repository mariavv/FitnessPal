package mariavv.fitnesspal.data.repository;

import android.database.Cursor;

import java.util.Date;

import mariavv.fitnesspal.domain.Dish;
import mariavv.fitnesspal.domain.Food;

public class Repo {

    private static Repo instance;

    private DbRepository db;

    private Repo() {
        db = new DbRepository();
    }

    public static synchronized Repo getInstance() {
        if (instance == null) {
            instance = new Repo();
        }
        return instance;
    }

    public Cursor getFoodsFromHandbook() {
        return db.getFoodsFromHandbook();
    }

    public Cursor getJournal(long date) {
        return db.getJournal(date);
    }

    public void insertFoodInHandbook(Food food) {
        db.insertFoodInHandbook(food);
    }

    public int getJournalDaysCount() {
        final Cursor c = db.getJournalDaysCount();
        c.moveToFirst();
        final int count = c.getInt(0);
        c.close();
        return count;
    }

    public long getDateByIndex(int i) {
        final Cursor c = db.getJournalDates();
        if (i < c.getCount()) {
            c.moveToPosition(i);
            final long date = c.getLong(0);
            c.close();
            return date;
        } else {
            c.close();
            return new Date().getTime();
        }
    }

    public long insertDishInJournal(Dish dish) {
        return db.insertDishInJournal(dish);
    }

    public void clearJournal() {
        db.clearJournal();
    }

    public Cursor getFoodList() {
        return db.getFoodNamesFromHandbook();
    }

    public long addDish(Date date, String meal, String dish, int weight) {
        try {
            return db.insertDishInJournal(new Dish(date, meal, db.getFoodIdByName(dish), weight));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public long addFood(Food food) {
        return db.insertFoodInHandbook(food);
    }

    public long[] getJournalDates() {
        final Cursor c = db.getJournalDates();
        final long[] dates = new long[c.getCount()];
        int i = 0;
        while (c.moveToNext()) {
            dates[i++] = c.getLong(0);
        }
        c.close();
        return dates;
    }
}
