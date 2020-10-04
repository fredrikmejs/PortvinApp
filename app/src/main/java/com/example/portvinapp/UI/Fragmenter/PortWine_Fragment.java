package com.example.portvinapp.UI.Fragmenter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.portvinapp.Adapter.RecylerView_Config;
import com.example.portvinapp.Data.FirebaseDatabaseHelper;
import com.example.portvinapp.Domain.Singleton.Portwine_enum;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PortWine_Fragment extends Fragment implements View.OnClickListener {

    Button addWine, back;
    RecyclerView mRecyclerView;
    TextView numberOfWine;
    Singleton singleton = Singleton.getInstance();
    List<PortwineObj> arr = new ArrayList<>(singleton.getPortWineArr());

    public PortWine_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_config, container, false);


        numberOfWine = view.findViewById(R.id.textView_numberofPort);


        addWine = view.findViewById(R.id.button_addWine);
        addWine.setOnClickListener(this);

        mRecyclerView = view.findViewById(R.id.portwine_recylervview);

        back = view.findViewById(R.id.back_recyklerview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton singleton = Singleton.getInstance();
                singleton.clearUsedIndexx();
                Fragment fragment = new ChoosePortwine_fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
                transaction.commit();
            }
        });

        Spinner spinner_sortBy = view.findViewById(R.id.spinner_sortby);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner_sortby));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_sortBy.setSelection(0,false);
        spinner_sortBy.setAdapter(myAdapter);
        spinner_sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Singleton singleton = Singleton.getInstance();
                List<PortwineObj> portwineArr = new ArrayList<>(singleton.getPortWineArr());

                if (portwineArr.size() > 0) {
                    switch (position) {
                        case 1:
                            Collections.sort(portwineArr, new Comparator<PortwineObj>() {
                                @Override
                                public int compare(PortwineObj o1, PortwineObj o2) {
                                    return Integer.compare(o1.getGrade(), o2.getGrade());
                                }
                            });
                            singleton.setPortWineArr(portwineArr);
                            break;
                        case 2:
                            Collections.sort(portwineArr, new Comparator<PortwineObj>() {
                                @Override
                                public int compare(PortwineObj o1, PortwineObj o2) {
                                    return Integer.compare(o1.getQty(), o2.getQty());
                                }
                            });
                            Collections.reverse(portwineArr);
                            singleton.setPortWineArr(portwineArr);
                            break;
                        case 3:
                            Collections.sort(portwineArr, new Comparator<PortwineObj>() {
                                @Override
                                public int compare(PortwineObj o1, PortwineObj o2) {
                                    return Integer.compare(o1.getVintage(), o2.getVintage());
                                }
                            });
                            Collections.reverse(portwineArr);
                            singleton.setPortWineArr(portwineArr);
                            break;
                        case 4:
                            Collections.sort(portwineArr, new Comparator<PortwineObj>() {
                                @Override
                                public int compare(PortwineObj o1, PortwineObj o2) {
                                    return Integer.compare(o1.getBottleYear(), o2.getBottleYear());
                                }
                            });
                            Collections.reverse(portwineArr);
                            singleton.setPortWineArr(portwineArr);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


            new FirebaseDatabaseHelper().readPortwine(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<String> keys) {
                Log.d("TEST", "DataIsLoaded: 2 ");
                new RecylerView_Config().setConfig(mRecyclerView,getContext(), keys);

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
        if (arr.size() > 0) {
            numberOfWine.setText("Amount:  " + totalPort());
         }

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

    public int totalPort(){
        int totalNum = 0;

        String name = "" + Portwine_enum.forValue(singleton.getPortType());

        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getType().equals(name)) {
                totalNum += arr.get(i).getQty();
            }
        }
        return totalNum;
    }
}