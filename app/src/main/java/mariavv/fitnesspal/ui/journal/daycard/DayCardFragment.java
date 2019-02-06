package mariavv.fitnesspal.ui.journal.daycard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Date;

import mariavv.fitnesspal.R;

public class DayCardFragment extends MvpAppCompatFragment implements DayCardView {

    private static final String ARG_DATE = "date";

    View view;

    @InjectPresenter
    DayCardPresenter presenter;

    public static DayCardFragment newInstance(Date date) {
        DayCardFragment fragment = new DayCardFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_day_card, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String date = (String) args.getSerializable(ARG_DATE);
            presenter.onGetDateArg(date);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter = null;
    }

    @Override
    public void updateCard() {

    }
}
