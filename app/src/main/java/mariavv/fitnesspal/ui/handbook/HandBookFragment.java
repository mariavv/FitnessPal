package mariavv.fitnesspal.ui.handbook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.db.DbManager;
import mariavv.fitnesspal.model.entity.Food;

public class HandBookFragment extends MvpAppCompatFragment implements HandBookView {

    View view;
    DbManager db;
    private RecyclerView recycler;

    @InjectPresenter
    HandBookPresenter presenter;
    private FeedAdapter adapter;

    public static HandBookFragment newInstance() {
        return new HandBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hand_book, container, false);

        configureViews();

        db = DbManager.getInstance(this.getContext());
        db.insertFoodInHandbook(new Food("Омлет", 15, 18, 3));
        db.insertFoodInHandbook(new Food("Кофе с молоком", 1, 1, 3));
        db.insertFoodInHandbook(new Food("Мясной салат", 5, 5, 10));
        db.insertFoodInHandbook(new Food("Салат овощной", 1, 2, 5));
        db.insertFoodInHandbook(new Food("Котлеты", 15, 14, 8));
        db.insertFoodInHandbook(new Food("Пюре", 1, 13, 18));
        db.insertFoodInHandbook(new Food("Сырники", 5, 15, 25));
        db.insertFoodInHandbook(new Food("Малиновый чизкейк", 5, 15, 40));
        db.insertFoodInHandbook(new Food("Макароны с сыром", 3, 9, 22));
        db.insertFoodInHandbook(new Food("Гуляш", 18, 11, 2));
        Cursor c = db.getFoodsFromHandbook();
        adapter.updateItems(c);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }


    private void configureViews() {
        recycler = view.findViewById(R.id.recycler);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new FeedAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
