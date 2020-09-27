package com.example.portvinapp.UI.Fragmenter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.portvinapp.Adapter.Adaptor_wine;
import com.example.portvinapp.Adapter.RecylerView_Config;
import com.example.portvinapp.Data.FirebaseDatabaseHelper;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;

import java.util.ArrayList;
import java.util.List;


public class PortWine_Fragment extends Fragment implements View.OnClickListener {

    Button addWine;
    RecyclerView mRecyclerView;


    public PortWine_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_config, container, false);



        addWine = view.findViewById(R.id.button_addWine);
        addWine.setOnClickListener(this);

        mRecyclerView = view.findViewById(R.id.portwine_recylervview);

        new FirebaseDatabaseHelper().readPortwine(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<PortwineObj> portwineArr, List<String> keys) {
                new RecylerView_Config().setConfig(mRecyclerView,getContext(),portwineArr, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == addWine){
            Fragment fragment = new AddPortwine_fragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}