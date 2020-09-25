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
import android.widget.TextView;

import com.example.portvinapp.Adapter.Adaptor_wine;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortvineObj;
import com.example.portvinapp.R;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.ArrayList;


public class PortWine_Fragment extends Fragment implements View.OnClickListener {

    Button addWine;
    Singleton singleton = Singleton.getInstance();
    ArrayList<PortvineObj> portwinesArrCopy = new ArrayList<>();
    ArrayList<PortvineObj> portwinesArr = new ArrayList<>();

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
        View view = inflater.inflate(R.layout.fragment_wine, container, false);



        addWine = view.findViewById(R.id.button_addWine);
        addWine.setOnClickListener(this);

        ListView list = view.findViewById(R.id.portwine_list);


        //Test data
        if (singleton.getPortWineArr().size() == 0) {
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
            singleton.addPortwine(new PortvineObj(null, "Graham", 1999, 2000, 10, "Portwine", "Vintage"));
        }
        whatPort();

        if(portwinesArr != null && portwinesArr.size() > 0) {
            Adaptor_wine adaptor = new Adaptor_wine(getContext(), portwinesArr);
            list.setAdapter(adaptor);
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

        for (PortvineObj a:portwinesArrCopy) {

            String b = a.getWineType();
            if (b.equals(portType)){
                portwinesArr.add(a);
            }
        }
    }


}