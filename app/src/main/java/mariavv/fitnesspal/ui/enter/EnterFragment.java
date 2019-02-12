package mariavv.fitnesspal.ui.enter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;

public class EnterFragment extends MvpAppCompatFragment implements EnterView, DatePickerDialog.OnDateSetListener {

    View view;

    ImageView calendarIv;
    DatePickerDialog datePickerDialog;
    TextView dateTv;
    EditText mealNumEd;
    Spinner spinner;
    EditText weightEd;
    Button addBtn;

    @InjectPresenter
    EnterPresenter presenter;

    public static EnterFragment newInstance() {
        return new EnterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enter, container, false);

        configureViews();

        return view;
    }

    private void configureViews() {
        calendarIv = view.findViewById(R.id.calendarIv);
        calendarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDateChangeClick();
            }
        });
        dateTv = view.findViewById(R.id.dateTv);
        mealNumEd = view.findViewById(R.id.mealNumEd);
        spinner = view.findViewById(R.id.spinner);
        weightEd = view.findViewById(R.id.weightEd);

        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddClick(mealNumEd.getText(), weightEd.getText());
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        presenter.onDateChange(year, month, dayOfMonth);
    }

    @Override
    public void showSelectedDate(String date) {
        dateTv.setText(date);
    }

    @Override
    public void showDatePickerDialog() {
        datePickerDialog.show();
    }

    @Override
    public void initCalendarDialog(int year, int month, int day) {
        final Context context = this.getContext();
        if (context != null) {
            datePickerDialog = new DatePickerDialog(context, this, year, month, day);
        }
    }

    @Override
    public void configureSpinner(String[] foodList) {
        final Context context = this.getContext();
        if (context != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, foodList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    presenter.onFoodSelected(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }
}
