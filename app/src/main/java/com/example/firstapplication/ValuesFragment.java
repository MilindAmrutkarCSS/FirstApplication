package com.example.firstapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ValuesFragment extends Fragment {

    @BindView(R.id.fgFirstName)
    TextView fgFirstName;

    Unbinder unbinder;

    Values2Fragment values2Fragment;

    @BindView(R.id.fgLastName)
    TextView fgLastName;

    @BindView(R.id.fgButton)
    Button fgButton;
    String firstName, lastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_values, container, false);

        unbinder = ButterKnife.bind(this, view);

        if (getArguments() != null) {
            firstName = getArguments().getString(Constants.FIRST_NAME);
            lastName = getArguments().getString(Constants.LAST_NAME);
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

    @OnClick(R.id.fgButton)
    public void onViewClicked() {
        values2Fragment = new Values2Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.FRAGMENT_FNAME, firstName);
        bundle.putString(Constants.FRAGMENT_LNAME, lastName);
        values2Fragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container2, values2Fragment);
        fragmentTransaction.commit();
    }
}
