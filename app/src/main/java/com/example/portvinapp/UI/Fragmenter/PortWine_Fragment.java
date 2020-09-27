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
    Singleton singleton = Singleton.getInstance();
    ArrayList<PortwineObj> portwinesArrCopy = new ArrayList<>();
    List<PortwineObj> portwinesArr = new ArrayList<>();
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

        //ListView list = view.findViewById(R.id.portwine_list);

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
            public void DataisUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        /*
        //Test data
        if (singleton.getPortWineArr().size() == 0) {
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortwineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
        }
         */


        whatPort();


        /*
        if(portwinesArr != null && portwinesArr.size() > 0) {
            Adaptor_wine adaptor = new Adaptor_wine(getContext(), portwinesArr);
            list.setAdapter(adaptor);
        }

         */

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


    private void whatPort(){
        String portType = "";
        int port = singleton.getPortType();
        switch (port){
            case 1:
                portType = "Tawny";
                break;
            case 2:
                portType = "White";
                break;
            case 3:
                portType = "Colheita";
                break;
            case 4:
                portType = "Vintage";
                break;
            case 5:
                portType = "LBV";
                break;
            case 6:
                portType = "10 years";
                break;
            case 7:
                portType = "20 years";
                break;
            case 8:
                portType = "30 years";
                break;
            case 9:
                portType = "40 years";
                break;
            case 10:
                portType = "other";
                break;
        }
        sort(portType);
    }

    private void sort(String portType){

        portwinesArrCopy.addAll(singleton.getPortWineArr());

        for (PortwineObj a:portwinesArrCopy) {

            String b = a.getWineType();
            if (b.equals(portType)){
                portwinesArr.add(a);
            }
        }
    }


}