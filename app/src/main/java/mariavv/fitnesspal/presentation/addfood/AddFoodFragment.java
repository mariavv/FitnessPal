package mariavv.fitnesspal.presentation.addfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;
import mariavv.fitnesspal.other.FrmFabric;

public class AddFoodFragment extends MvpAppCompatFragment implements AddFoodView, FrmFabric.IFragment {

    View view;

    EditText foodEd;
    EditText proteinEd;
    EditText fatEd;
    EditText carbEd;

    Button addBtn;

    @InjectPresenter
    AddFoodPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_food, container, false);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(R.string.add_food_title);
        }

        configureViews();

        return view;
    }

    private void configureViews() {
        initViews();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddClick(foodEd.getText(), proteinEd.getText(), fatEd.getText(), carbEd.getText());
            }
        });

        carbEd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    addBtn.callOnClick();
                }
                return false;
            }
        });
    }

    private void initViews() {
        foodEd = view.findViewById(R.id.foodEd);
        proteinEd = view.findViewById(R.id.proteinEd);
        fatEd = view.findViewById(R.id.fatEd);
        carbEd = view.findViewById(R.id.carbEd);
        addBtn = view.findViewById(R.id.addBtn);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }
}
