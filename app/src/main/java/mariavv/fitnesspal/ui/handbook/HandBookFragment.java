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
        ///db.clearTables();
        db.insertFoodInHandbook(new Food("омлет", 15, 18, 3));
        db.insertFoodInHandbook(new Food("кофе с молоком", 1, 1, 3));
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
