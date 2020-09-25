package com.example.portvinapp.UI.Fragmenter;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortvineObj;
import com.example.portvinapp.R;


public class AddPortwine_fragment extends Fragment implements View.OnClickListener {

    ImageView imageView_port;
    EditText editText_winery, editText_vintage, editText_bottleYear, editText_grade;
    Button button_delete, button_save;
    Singleton singleton = Singleton.getInstance();
    Spinner spinner_portType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_portwine_fragment, container, false);

        imageView_port = view.findViewById(R.id.imagebutton_picture);
        imageView_port.setOnClickListener(this);

        editText_winery = view.findViewById(R.id.editText_winery);

        spinner_portType = view.findViewById(R.id.spinner_portType);

        editText_vintage = view.findViewById(R.id.editText_vintage);

        editText_bottleYear = view.findViewById(R.id.editText_bottleYear);

        editText_grade = view.findViewById(R.id.editText_grade);

        button_delete = view.findViewById(R.id.button_deletePort);
        button_delete.setVisibility(View.INVISIBLE);

        button_save = view.findViewById(R.id.save_button);
        button_save.setOnClickListener(this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_portType.setAdapter(myAdapter);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == button_save){
            String winery, type, wineType;
            int vintage = -1, bottleYear = -1, grade = -1;
            Bitmap portImage;

            try {
                winery = editText_winery.getText().toString();
                type = spinner_portType.getSelectedItem().toString();
                wineType = "Portwine";

                if (!editText_vintage.equals("")) {
                    vintage = Integer.parseInt(editText_vintage.getText().toString());
                }
                if (!editText_bottleYear.equals("")) {
                    bottleYear = Integer.parseInt(editText_bottleYear.getText().toString());
                }
                if (!editText_grade.equals("")) {
                    grade = Integer.parseInt(editText_grade.getText().toString());
                }

                //TODO change image
                singleton.addPortwine(new PortvineObj(null,winery,vintage,bottleYear,grade,wineType,type));

                Fragment fragment = new PortWine_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }catch (Exception e){
                Toast.makeText(getContext(),"Wrong argument",Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        }
    }
}