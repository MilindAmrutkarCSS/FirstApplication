package com.example.firstapplication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class Values2Fragment extends Fragment {

    @BindView(R.id.fgFirstName)
    EditText fgFirstName;
    @BindView(R.id.fgLastName)
    EditText fgLastName;
    String firstName, lastName;


    Unbinder unbinder;
    @BindView(R.id.fgBtnSend)
    Button fgBtnSend;

    private IDataListener iDataListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_values2, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            firstName = getArguments().getString(Constants.FRAGMENT_FNAME);
            lastName = getArguments().getString(Constants.FRAGMENT_LNAME);
            fgFirstName.setText(firstName);
            fgLastName.setText(lastName);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.fgBtnSend)
    public void onViewClicked() {
        iDataListener.sendData(fgFirstName.getText().toString(), fgLastName.getText().toString());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iDataListener = (IDataListener) context;

    }
}
