package mariavv.fitnesspal.presentation.journal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.other.Const;
import mariavv.fitnesspal.other.FrmFabric;

public class JournalFragment extends MvpAppCompatFragment implements JournalView, FrmFabric.IFragment {

    TextView dateTv;
    ImageView prevDayIv;
    ImageView nextDayIv;
    FloatingActionButton fab;

    ViewPager viewPager;

    PagerAdapter pagerAdapter;

    @InjectPresenter
    JournalPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_journal, container, false);

        configureViews(view);

        return view;
    }

    private void configureViews(View view) {
        initViews(view);

        viewPager.setAdapter(pagerAdapter);

        prevDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPrevDayClick(getCurrentPage(), getPageCount());
            }
        });
        nextDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onNextDayClick(getCurrentPage(), getPageCount());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabClick();
            }
        });
    }

    private void initViews(View view) {
        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.viewPager);

        dateTv = view.findViewById(R.id.date);
        prevDayIv = view.findViewById(R.id.left);
        nextDayIv = view.findViewById(R.id.right);

        fab = view.findViewById(R.id.fab);
    }

    private int getPageCount() {
        return pagerAdapter.getCount();
    }

    private int getCurrentPage() {
        return viewPager.getCurrentItem();
    }

    @Override
    public void moveToPosition(int position, String date) {
        viewPager.setCurrentItem(position);
        dateTv.setText(date);
    }

    @Override
    public void setAdapterItems(long[] journalDates) {
        pagerAdapter.setItems(journalDates);
    }

    @Override
    public void setPrevDayEnable() {
        prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_24dp));
    }

    @Override
    public void setNextDayEnable() {
        nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_24dp));
    }

    @Override
    public void setPrevDayDisable() {
        prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_inactive_24dp));
    }

    @Override
    public void setNextDayDisable() {
        nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp));
    }

    @Override
    public void setTitle() {
        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.journal_title);
        }
    }

    @Override
    public void setDate(String date) {
        dateTv.setText(date);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public String getName() {
        return Const.Screen.JOURNAL;
    }
}
