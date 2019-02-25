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

public class JournalFragment extends MvpAppCompatFragment implements JournalView {

    View view;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journal, container, false);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.journal_title);
        }

        if (savedInstanceState != null) {
            viewPager.setCurrentItem(savedInstanceState.getInt(Const.BundleArg.JOURNAL_PAGE_ARG));
        }

        configure();

        return view;
    }

    private void configure() {
        initViews();
        configureViews();
        configureInitState();
    }

    private void configureViews() {
        viewPager.setAdapter(pagerAdapter);

        prevDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.onPageMove(getCurrentPage() - 1);
                moveToPosition(getCurrentPage() - 1);
                //todo
                if (getCurrentPage() == 0) {
                    prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_inactive_24dp));
                } else if (getPageCount() > 2 && getCurrentPage() == getPageCount() - 2) {
                    nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_24dp));
                }
            }
        });
        nextDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.onPageMove(getCurrentPage() + 1);
                moveToPosition(getCurrentPage() + 1);
                if (getCurrentPage() == getPageCount() - 1) {
                    nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp));
                } else if (getCurrentPage() == 1) {
                    prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_24dp));
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onFabClick();
            }
        });
    }

    private void configureInitState() {
        setNavigationButtonsState();
        presenter.onPageMove(getCurrentPage());
    }

    private int getPageCount() {
        return pagerAdapter.getCount();
    }

    private void initViews() {
        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.viewPager);

        dateTv = view.findViewById(R.id.date);
        prevDayIv = view.findViewById(R.id.left);
        nextDayIv = view.findViewById(R.id.right);

        fab = view.findViewById(R.id.fab);
    }

    private int getCurrentPage() {
        return viewPager.getCurrentItem();
    }

    private void moveToPosition(int position) {
        viewPager.setCurrentItem(position);
        presenter.onPageMove(getCurrentPage());
    }

    private void setNavigationButtonsState() {
        if (getCurrentPage() == 0) {
            prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_inactive_24dp));
        } else if (getCurrentPage() == getPageCount() - 1) {
            nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp));
        }
    }

    @Override
    public void setDate(String date) {
        dateTv.setText(date);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(Const.BundleArg.JOURNAL_PAGE_ARG, viewPager.getCurrentItem());
    }
}
