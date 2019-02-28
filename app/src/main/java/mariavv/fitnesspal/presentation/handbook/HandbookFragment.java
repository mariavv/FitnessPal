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
import mariavv.fitnesspal.other.Const;
import mariavv.fitnesspal.other.FrmFabric;

public class HandbookFragment extends MvpAppCompatFragment implements HandBookView, FrmFabric.IFragment {

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
        configureViews();
        return view;
    }

    @Override
    public void updateFeed(Cursor c) {
        adapter.updateItems(c);
    }

    @Override
    public void setTitle() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.handbook_title);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void configureViews() {
        fab = view.findViewById(R.id.fab);
        recycler = view.findViewById(R.id.recycler);

        configureRecyclerView();

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

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public String getName() {
        return Const.Screen.HANDBOOK;
    }
}
