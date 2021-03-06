package com.example.portvinapp.UI.Fragmenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.List;


public class AddPortwine_fragment extends Fragment implements View.OnClickListener {

    ImageView imageView_port;
    EditText editText_winery, editText_vintage, editText_bottleYear, editText_qty, editText_notes;
    Button button_delete, button_save, button_back;
    Singleton singleton = Singleton.getInstance();
    Spinner spinner_portType, spinner_grade;
    Bitmap bitmap;

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

        spinner_grade = view.findViewById(R.id.spinner_grade);

        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner_grade));
        gradeAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_grade.setAdapter(gradeAdapter);

        spinner_grade.setSelection(0,false);



        editText_qty = view.findViewById(R.id.editText_qty);

        button_delete = view.findViewById(R.id.button_deletePort);
        button_delete.setVisibility(View.INVISIBLE);

        editText_notes = view.findViewById(R.id.editText_notes);

        button_save = view.findViewById(R.id.save_button);
        button_save.setOnClickListener(this);
        button_save.setText("Add portwine");
        singleton.clearUsedIndexx();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(getContext(),R.layout.spinner_layout,getResources().getStringArray(R.array.spinner));
        myAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner_portType.setAdapter(myAdapter);

        spinner_portType.setSelection(singleton.getPortType());


        button_back = view.findViewById(R.id.button_add_edit);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new PortWine_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = (Bitmap) data.getExtras().get("data");
        imageView_port.setImageBitmap(bitmap);
    }

    @Override
    public void onClick(View v) {
        if (v == button_save){
            final PortwineObj portwineObj = new PortwineObj();
            String winery, type, wineType, notes = " ";
                int vintage = -1, bottleYear = -1, qty = -1, grade;

            try {
                winery = editText_winery.getText().toString();

                wineType = "Portwine";

                if (!editText_notes.getText().toString().equals("")){
                    notes = editText_notes.getText().toString();
                }

                if (!editText_vintage.getText().toString().equals("")) {
                    vintage = Integer.parseInt(editText_vintage.getText().toString());
                }
                if (!editText_bottleYear.getText().toString().equals("")) {
                    bottleYear = Integer.parseInt(editText_bottleYear.getText().toString());
                }
                grade = spinner_grade.getSelectedItemPosition();

                if (grade == 0){
                    grade = 10;
                }

                if (!editText_qty.getText().toString().equals("")){
                    qty = Integer.parseInt(editText_qty.getText().toString());
                }



                type = spinner_portType.getSelectedItem().toString();
                switch (type){
                    case "Late Bottle Vintage":
                        type = "LBV";
                        break;
                    case "10 Year":
                        type = "age10";
                        break;
                    case "20 Year":
                        type = "age20";
                        break;
                    case "30 Year":
                        type = "age30";
                        break;
                    case "40 Year":
                        type = "age40";
                        break;
                }

                if (qty != -1 && !winery.equals("") && !type.equals("")) {

                    portwineObj.setVintage(vintage);
                    portwineObj.setWinery(winery);
                    portwineObj.setType(type);
                    portwineObj.setBottleYear(bottleYear);
                    portwineObj.setGrade(grade);
                    portwineObj.setWineType(wineType);
                    portwineObj.setQty(qty);
                    portwineObj.setNotes(notes);

                    singleton.setPortType(Portwine_enum.valueOf(type).getValue());

                    if (bitmap != null) {
                        portwineObj.setPortImage(bitmapToByte());
                    } else portwineObj.setPortImage(null);


                    new FirebaseDatabaseHelper().addPortwine(portwineObj, new FirebaseDatabaseHelper.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<String> keys) {
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
                } else {
                    Toast.makeText(getContext(),"Wrong arguments",Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Toast.makeText(getContext(),"Wrong argument",Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
        }
        if(v == imageView_port){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,0);
        }
    }

    private String bitmapToByte(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap = Bitmap.createScaledBitmap(bitmap,60,60,true);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArr = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteArr,Base64.DEFAULT);

        return encodeImage;
    }
}