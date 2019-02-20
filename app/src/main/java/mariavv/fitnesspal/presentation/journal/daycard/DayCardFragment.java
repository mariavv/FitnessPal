package mariavv.fitnesspal.presentation.journal.daycard;

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

import static mariavv.fitnesspal.other.BundleArg.ARG_DATE;

public class DayCardFragment extends MvpAppCompatFragment implements DayCardView {

    View view;
    RecyclerView recycler;

    @InjectPresenter
    DayCardPresenter presenter;

    private FeedAdapter adapter;

    public static DayCardFragment newInstance(String date) {
        final DayCardFragment fragment = new DayCardFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day_card, container, false);

        final Bundle args = getArguments();
        if (args != null) {
            configureViews();
            presenter.onGetDateArg((String) args.getSerializable(ARG_DATE));
        }

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateCard(Cursor data) {
        adapter.updateItems(data);
    }
}
