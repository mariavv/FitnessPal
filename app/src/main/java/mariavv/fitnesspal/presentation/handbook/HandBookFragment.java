package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;

public class HandBookFragment extends MvpAppCompatFragment implements HandBookView {

    private View view;
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

        return view;
    }

    @Override
    public void updateFeed(Cursor c) {
        adapter.updateItems(c);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void configureViews() {
        recycler = view.findViewById(R.id.recycler);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        adapter = new FeedAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration decoration = new DividerItemDecoration(recycler.getContext(), DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(decoration);
    }
}
