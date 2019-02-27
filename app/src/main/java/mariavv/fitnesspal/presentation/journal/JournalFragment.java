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

public class JournalFragment extends MvpAppCompatFragment implements JournalView {

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

        final FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.journal_title);
        }

        configureViews(view);

        presenter.onCreateView(getCurrentPage(), getPageCount());

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
    public void setPrevDayImageDrawable(int imageRes) {
        prevDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(imageRes));
    }

    @Override
    public void setNextDayImageDrawable(int imageRes) {
        nextDayIv.setImageDrawable(FitnessPal.appContext.getDrawable(imageRes));
    }

    @Override
    public void setDate(String date) {
        dateTv.setText(date);
    }
}
