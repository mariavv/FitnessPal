package mariavv.fitnesspal.presentation.enter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.presentation.UiTools;

public class EnterFragment extends MvpAppCompatFragment implements EnterView, DatePickerDialog.OnDateSetListener {

    View view;

    ImageView calendarIv;
    DatePickerDialog datePickerDialog;
    TextView dateTv;
    Spinner mealNumSp;
    AutoCompleteTextView dishActv;
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
        mealNumSp = view.findViewById(R.id.mealNumSp);
        dishActv = view.findViewById(R.id.dishActv);
        weightEd = view.findViewById(R.id.weightEd);

        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddClick(dishActv.getEditableText(), weightEd.getText());
            }
        });

        weightEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    addBtn.callOnClick();
                }
                return false;
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
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, foodList);
            dishActv.setAdapter(adapter);
        }
    }

    @Override
    public void configureMealsSpinner(String[] meals) {
        final Context context = this.getContext();
        if (context != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, meals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mealNumSp.setAdapter(adapter);

            mealNumSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    presenter.onMealSelected(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }
    }

    @Override
    public void showMessage(String message) {
        UiTools.showToast(message);
    }
}