package com.example.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {

    }

    @OnClick({R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                if (!TextUtils.isEmpty(etFirstName.getText()) && !TextUtils.isEmpty(etLastName.getText())) {
                    firstName = etFirstName.getText().toString();
                    lastName = etLastName.getText().toString();

                    Intent intent = new Intent(this, SecondActivity.class);
                    intent.putExtra(Constants.FIRST_NAME, firstName);
                    intent.putExtra(Constants.LAST_NAME, lastName);
                    startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if(data != null) {
                    String fName = data.getStringExtra(Constants.F_NAME);
                    String lName = data.getStringExtra(Constants.L_NAME);
                    etFirstName.setText(fName);
                    etLastName.setText(lName);
                }
            }
        }
    }
}
