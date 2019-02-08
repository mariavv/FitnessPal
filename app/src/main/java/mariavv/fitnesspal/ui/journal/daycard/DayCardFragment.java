package mariavv.fitnesspal.ui.journal.daycard;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.model.db.DbManager;

import static mariavv.fitnesspal.BundleArg.ARG_DATE;

public class DayCardFragment extends MvpAppCompatFragment implements DayCardView {

    View view;

    @InjectPresenter
    DayCardPresenter presenter;

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
            presenter.onGetDateArg((String) args.getSerializable(ARG_DATE));
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //????? presenter = null;
    }

    @Override
    public void updateCard(String date, Cursor c) {
        final TextView textView = view.findViewById(R.id.text);
        c.moveToFirst();
        StringBuilder s = new StringBuilder("");
        do {
            s = s.append(", ").append(c.getString(c.getColumnIndex(DbManager.FOOD_NAME)));
        } while (c.moveToNext());
        textView.setText(s.toString());
    }
}
