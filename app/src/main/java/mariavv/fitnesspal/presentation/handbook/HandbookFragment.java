package mariavv.fitnesspal.presentation.handbook;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;

public class HandbookFragment extends MvpAppCompatFragment implements HandBookView {

    private View view;
    private RecyclerView recycler;
    FloatingActionButton fab;

    @InjectPresenter
    HandBookPresenter presenter;
    private FeedAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hand_book, container, false);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.handbook_title);
        }

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
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabClick();
            }
        });
    }

    private void configureRecyclerView() {
        adapter = new FeedAdapter();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        DividerItemDecoration decoration = new DividerItemDecoration(recycler.getContext(), DividerItemDecoration.VERTICAL);
        recycler.addItemDecoration(decoration);
    }
}
