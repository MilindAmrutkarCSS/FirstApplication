package com.example.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    String firstName, lastName;
    @BindView(R.id.fName)
    EditText etFName;
    @BindView(R.id.lName)
    EditText etLName;
    @BindView(R.id.btnSendToFirstActivity)
    Button btnSendToFirstActivity;

    ValuesFragment valuesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        valuesFragment = new ValuesFragment();
        loadFragment(valuesFragment);

        Intent intent = getIntent();

        if (intent != null) {
            if ((getIntent().getStringExtra(Constants.FIRST_NAME) != null)&& (getIntent().getStringExtra(Constants.LAST_NAME) != null)) {
                firstName = getIntent().getStringExtra(Constants.FIRST_NAME);
                lastName = getIntent().getStringExtra(Constants.LAST_NAME);
            }
            etFName.setText(firstName);
            etLName.setText(lastName);
        }
    }

    @OnClick(R.id.btnSendToFirstActivity)
    public void onViewClicked() {
       /* firstName = etFName.getText().toString();
        lastName = etLName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Constants.F_NAME, etFName.getText().toString());
        intent.putExtra(Constants.L_NAME, etLName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();*/

       valuesFragment = new ValuesFragment();
       Bundle bundle = new Bundle();
       bundle.putString(Constants.FIRST_NAME, etFName.getText().toString());
       bundle.putString(Constants.LAST_NAME, etLName.getText().toString());
       valuesFragment.setArguments(bundle);
       loadFragment(valuesFragment);
    }

    public void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
