package mariavv.fitnesspal.presentation.journal.daycard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import mariavv.fitnesspal.R;

import static mariavv.fitnesspal.other.Const.BundleArg.ARG_DATE;

public class DayCardFragment extends MvpAppCompatFragment implements DayCardView {

    View view;
    RecyclerView recycler;
    TextView proteinTv;
    TextView fatTv;
    TextView carbTv;
    TextView energyTv;

    @InjectPresenter
    DayCardPresenter presenter;

    private FeedAdapter adapter;

    public static DayCardFragment newInstance(long date) {
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
            presenter.onGetDateArg(args.getLong(ARG_DATE));
        }

        return view;
    }

    private void configureViews() {
        View headerView = view.findViewById(R.id.header_info);
        proteinTv = headerView.findViewById(R.id.protein_tv);
        fatTv = headerView.findViewById(R.id.fat_tv);
        carbTv = headerView.findViewById(R.id.carb_tv);
        energyTv = headerView.findViewById(R.id.energy_tv);
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
    public void updateCard(List<ItemType> dataSet, int dayProtein, int dayFat, int dayCarb, int dayEnergy) {
        String weightPostfx = getString(R.string.weight_postfix);

        proteinTv.setText(String.format("%s %s%s",
                getString(R.string.protein_full_prefix),
                String.valueOf(dayProtein),
                weightPostfx));

        fatTv.setText(String.format("%s %s%s",
                getString(R.string.fat_full_prefix),
                String.valueOf(dayFat),
                weightPostfx));

        carbTv.setText(String.format("%s %s%s",
                getString(R.string.carb_full_prefix),
                String.valueOf(dayCarb),
                weightPostfx));

        energyTv.setText(String.format("%s %s %s",
                getString(R.string.energy_prefix),
                String.valueOf(dayEnergy),
                getString(R.string.energy_postfix)));

        adapter.updateItems(dataSet);
    }
}
