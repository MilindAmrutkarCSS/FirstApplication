package com.example.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final int SECOND_ACTIVITY_REQUEST_CODE = 200;

    @BindView(R.id.firstName)
    EditText etFirstName;
    @BindView(R.id.lastName)
    EditText etLastName;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    String firstName, lastName;

    ValuesFragment valuesFragment;
    Values2Fragment values2Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        valuesFragment = new ValuesFragment();
        values2Fragment = new Values2Fragment();
        loadFragment(valuesFragment);
        loadFragment2(values2Fragment);
    }

    @OnClick({R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
               /* if (!TextUtils.isEmpty(etFirstName.getText()) && !TextUtils.isEmpty(etLastName.getText())) {
                    firstName = etFirstName.getText().toString();
                    lastName = etLastName.getText().toString();

                    Intent intent = new Intent(this, SecondActivity.class);
                    intent.putExtra(Constants.FIRST_NAME, firstName);
                    intent.putExtra(Constants.LAST_NAME, lastName);
                    startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                }*/
                valuesFragment = new ValuesFragment();
                Bundle bundleFragment = new Bundle();
                bundleFragment.putString(Constants.FIRST_NAME, etFirstName.getText().toString());
                bundleFragment.putString(Constants.LAST_NAME, etLastName.getText().toString());
                valuesFragment.setArguments(bundleFragment);
                loadFragment(valuesFragment);

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if (data != null) {
                    String fName = data.getStringExtra(Constants.F_NAME);
                    String lName = data.getStringExtra(Constants.L_NAME);
                    etFirstName.setText(fName);
                    etLastName.setText(lName);
                }
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void loadFragment2(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }
}
