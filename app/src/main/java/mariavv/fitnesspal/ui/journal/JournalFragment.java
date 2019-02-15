package mariavv.fitnesspal.ui.journal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import mariavv.fitnesspal.FitnessPal;
import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.repository.Repo;

public class JournalFragment extends MvpAppCompatFragment implements JournalView {

    View view;
    TextView dateTv;
    ImageView prevDayIv;
    ImageView nextDayIv;

    ViewPager viewPager;

    PagerAdapter pagerAdapter;

    @InjectPresenter
    JournalPresenter presenter;

    public static JournalFragment newInstance() {
        return new JournalFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journal, container, false);

        configureViews();

        return view;
    }

    private void configureViews() {
        initViews();

        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        showDate();
        setNavigationButtonsState();

        prevDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                moveToPosition(getCurrentPage() + 1);
                if (getCurrentPage() == getPageCount() - 1) {
                    nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp));
                } else if (getCurrentPage() == 1) {
                    prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_24dp));
                }
            }
        });
    }

    private int getPageCount() {
        return pagerAdapter.getCount();
    }

    private void initViews() {
        viewPager = view.findViewById(R.id.viewPager);
        dateTv = view.findViewById(R.id.date);
        prevDayIv = view.findViewById(R.id.left);
        nextDayIv = view.findViewById(R.id.right);
    }

    private int getCurrentPage() {
        return viewPager.getCurrentItem();
    }

    private void moveToPosition(int position) {
        viewPager.setCurrentItem(position);
        showDate();
        //setNavigationButtonsState();
    }

    private void setNavigationButtonsState() {
        if (getCurrentPage() == 0) {
            prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_left_black_inactive_24dp));
        } else if (getCurrentPage() == getPageCount() - 1) {
            nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(R.drawable.ic_chevron_right_black_inactive_24dp));
        }
    }

    private void showDate() {
        final String date = Repo.getInstance().getDateByIndex(getCurrentPage());
        //todo
        try {
            final DateFormat to = new SimpleDateFormat(getString(R.string.date_format_display), Locale.getDefault()); // wanted format
            final DateFormat from = new SimpleDateFormat(getString(R.string.date_pattern), Locale.getDefault()); // current format
            final String toStr = to.format(from.parse(date));
            dateTv.setText(toStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
