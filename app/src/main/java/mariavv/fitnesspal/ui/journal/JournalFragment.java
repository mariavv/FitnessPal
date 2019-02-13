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

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.repository.Repo;

public class JournalFragment extends MvpAppCompatFragment implements JournalView {

    View view;

    TextView dateTv;
    ImageView prevDayIv;
    ImageView nextDayIv;

    ViewPager viewPager;

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
        viewPager = view.findViewById(R.id.viewPager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        dateTv = view.findViewById(R.id.date);
        showDate();

        prevDayIv = view.findViewById(R.id.left);
        prevDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPosition(viewPager.getCurrentItem() - 1);
            }
        });

        nextDayIv = view.findViewById(R.id.right);
        nextDayIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPosition(viewPager.getCurrentItem() + 1);
            }
        });
    }

    private void moveToPosition(int position) {
        viewPager.setCurrentItem(position);
        showDate();
    }

    private void showDate() {
        final String date = Repo.getInstance().getDateByIndex(viewPager.getCurrentItem());
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
