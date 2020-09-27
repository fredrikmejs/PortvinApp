package com.example.portvinapp.UI.Fragmenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.portvinapp.Data.FirebaseDatabaseHelper;
import com.example.portvinapp.Domain.Singleton.Portwine_enum;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;

import java.util.ArrayList;
import java.util.List;

public class EditPort extends Fragment implements View.OnClickListener {
Singleton singleton = Singleton.getInstance();
ImageView imageView_port;
EditText editText_winery, editText_vintage, editText_bottleYear, editText_grade;
Button button_delete, button_save;
Spinner spinner_portType;
Bitmap bitmap;
String winery, type, wineType;
int vintage = -1, bottleYear = -1, grade = -1;
PortwineObj portwineObj;

    public EditPort() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_portwine_fragment, container, false);

        portwineObj = singleton.getPortwineObj();

        imageView_port = view.findViewById(R.id.imagebutton_picture);
        imageView_port.setOnClickListener(this);
        //bitmap = portwineObj.getPortImage();


        if (bitmap != null){
            imageView_port.setImageBitmap(bitmap);
        }

        editText_winery = view.findViewById(R.id.editText_winery);

        if (!portwineObj.getWinery().equals("")){
            editText_winery.setText(portwineObj.getWinery());
        }

        editText_vintage = view.findViewById(R.id.editText_vintage);

        if (portwineObj.getVintage() != -1){
            editText_vintage.setText("" + portwineObj.getVintage());
        }

        editText_bottleYear = view.findViewById(R.id.editText_bottleYear);

        if (portwineObj.getBottleYear() != -1){
            editText_bottleYear.setText("" + portwineObj.getBottleYear());
        }

        editText_grade = view.findViewById(R.id.editText_grade);

        if (portwineObj.getGrade() != -1){
            editText_grade.setText("" + portwineObj.getGrade());
        }

        button_delete = view.findViewById(R.id.button_deletePort);
        button_delete.setOnClickListener(this);

        button_save = view.findViewById(R.id.save_button);
        button_save.setOnClickListener(this);

        spinner_portType = view.findViewById(R.id.spinner_portType);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_portType.setAdapter(myAdapter);


        spinner_portType.setSelection(singleton.getPortType());

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        imageView_port.setImageBitmap(bitmap);
        singleton.setPortBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == button_save){

            try {
                winery = editText_winery.getText().toString();
                type = spinner_portType.getSelectedItem().toString();
                wineType = "Portwine";

                if (!editText_vintage.getText().toString().equals("")) {
                    vintage = Integer.parseInt(editText_vintage.getText().toString());
                }
                if (!editText_bottleYear.getText().toString().equals("")) {
                    bottleYear = Integer.parseInt(editText_bottleYear.getText().toString());
                }
                if (!editText_grade.getText().toString().equals("")) {
                    grade = Integer.parseInt(editText_grade.getText().toString());
                }

                //TODO use with database
                PortwineObj portwineObj = new PortwineObj();
                portwineObj.setWinery(winery);
                portwineObj.setGrade(grade);
                portwineObj.setType(type);
                portwineObj.setWineType(wineType);
                portwineObj.setVintage(vintage);
                portwineObj.setBottleYear(bottleYear);
                //TODO switch to bitmap later
                portwineObj.setPortImage(null);

                new FirebaseDatabaseHelper().updatePortwine(singleton.getKey(), portwineObj, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PortwineObj> portwineArr, List<String> keys) {

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

                Fragment fragment = new PortWine_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
                transaction.commit();

            }catch (Exception e){
                Toast.makeText(getContext(),"Wrong argument",Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        }
        if(v == imageView_port){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }

        if (v == button_delete){

            new FirebaseDatabaseHelper().deletePortwine(singleton.getKey(), new FirebaseDatabaseHelper.DataStatus() {
                @Override
                public void DataIsLoaded(List<PortwineObj> portwineArr, List<String> keys) {

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

            Fragment fragment = new PortWine_Fragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            FragmentManager manager = getActivity().getSupportFragmentManager();
            manager.popBackStack();
            transaction.commit();
        }
     }
}