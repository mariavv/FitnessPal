package mariavv.fitnesspal.model.repository;

import android.database.Cursor;

import java.util.Date;

import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.model.Dish;
import mariavv.fitnesspal.model.model.Food;
import mariavv.fitnesspal.model.model.MealNum;
import mariavv.fitnesspal.model.model.Weight;

public class Repo {

    private static Repo instance;

    private DbManager db;

    private Repo() {
        db = DbManager.getInstance();
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

    public Cursor getJournal(String date) {
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

    public String getDateByIndex(int i) {
        final Cursor c = db.getJournalDates();
        c.moveToPosition(i);
        String date = c.getString(0);
        c.close();
        return date;
    }

    public void insertDishInJournal(Dish dish) {
        db.insertDishInJournal(dish);
    }

    public void clearJournal() {
        db.clearJournal();
    }

    public void clearHandbook() {
        db.clearHandbook();
    }

    public Cursor getFoofList() {
        return db.getFoodNamesFromHandbook();
    }

    public void addDish(Date date, int mealNum, String dish, int weight) {
        db.insertDishInJournal(new Dish(new MealNum(mealNum), date, db.getFoodIdByName(dish), new Weight(weight)));
    }
}
