package com.example.portvinapp.UI.Fragmenter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.portvinapp.R;


public class HomePage extends Fragment implements View.OnClickListener {
    Button portWine_button, redWine_button, whiteWine_button, rose_button;

    public HomePage(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        portWine_button = view.findViewById(R.id.button_portwine);
        redWine_button = view.findViewById(R.id.button_redwine);
        whiteWine_button = view.findViewById(R.id.whitewine);
        rose_button = view.findViewById(R.id.button_rose);

        portWine_button.setOnClickListener(this);
        redWine_button.setOnClickListener(this);
        whiteWine_button.setOnClickListener(this);
        rose_button.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        if (v.getId() == R.id.button_portwine){
            fragment = new ChoosePortwine_fragment();

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}