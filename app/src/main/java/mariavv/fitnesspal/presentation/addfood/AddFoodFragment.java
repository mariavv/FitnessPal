package mariavv.fitnesspal.presentation.addfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import mariavv.fitnesspal.R;

public class AddFoodFragment extends MvpAppCompatFragment implements AddFoodView {

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

        foodEd = view.findViewById(R.id.foodEd);
        proteinEd = view.findViewById(R.id.proteinEd);
        fatEd = view.findViewById(R.id.fatEd);
        carbEd = view.findViewById(R.id.carbEd);

        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onAddClick(foodEd.getText(), proteinEd.getText(), fatEd.getText(), carbEd.getText());
            }
        });

        return view;
    }
}
