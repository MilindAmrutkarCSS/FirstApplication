package com.example.firstapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ValuesFragment extends Fragment {


    @BindView(R.id.fgFirstName)
    TextView fgFirstName;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_values, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {

            String firstName = getArguments().getString(Constants.FIRST_NAME);

            fgFirstName.setText(firstName);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
