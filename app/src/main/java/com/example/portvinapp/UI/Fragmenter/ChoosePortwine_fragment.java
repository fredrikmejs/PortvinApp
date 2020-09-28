package com.example.portvinapp.UI.Fragmenter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.R;


public class ChoosePortwine_fragment extends Fragment implements View.OnClickListener {

    Button tawny_button, white_button, colheita_button, vintage_button, lbv_button, year10_button, year20_button, year30_button, year40_button, otherPort_button, back_button;

    public ChoosePortwine_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_portwine_fragment, container, false);

        tawny_button = view.findViewById(R.id.button_tawny);
        tawny_button.setOnClickListener(this);
        white_button = view.findViewById(R.id.button_white);
        white_button.setOnClickListener(this);
        colheita_button = view.findViewById(R.id.button_colheita);
        colheita_button.setOnClickListener(this);
        vintage_button = view.findViewById(R.id.button_vintage);
        vintage_button.setOnClickListener(this);
        lbv_button = view.findViewById(R.id.button_LBV);
        lbv_button.setOnClickListener(this);
        year10_button = view.findViewById(R.id.button_10year);
        year10_button.setOnClickListener(this);
        year20_button = view.findViewById(R.id.button_20year);
        year20_button.setOnClickListener(this);
        year30_button = view.findViewById(R.id.button_30year);
        year30_button.setOnClickListener(this);
        year40_button = view.findViewById(R.id.button_40year);
        year40_button.setOnClickListener(this);
        otherPort_button = view.findViewById(R.id.button_OtherPort);
        otherPort_button.setOnClickListener(this);
        back_button = view.findViewById(R.id.button_backChoose);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomePage();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
                transaction.commit();
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        Singleton singleton = Singleton.getInstance();

        Fragment fragment = new PortWine_Fragment();
        if (v.getId() ==R.id.button_tawny){
            singleton.setPortType(0);
        } else if (v.getId()==R.id.button_white) {
            singleton.setPortType(1);
        } else if (v.getId()==R.id.button_colheita){
            singleton.setPortType(2);
        } else if (v.getId()==R.id.button_vintage){
            singleton.setPortType(3);
        } else if (v.getId()==R.id.button_LBV) {
            singleton.setPortType(4);
        } else if(v.getId()==R.id.button_10year){
            singleton.setPortType(5);
        } else if(v.getId()==R.id.button_20year){
            singleton.setPortType(6);
        } else if(v.getId()==R.id.button_30year){
            singleton.setPortType(7);
        } else if(v.getId()==R.id.button_40year){
            singleton.setPortType(8);
        } else {
            singleton.setPortType(9);
        }


        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.popBackStack();
        transaction.commit();


    }
}