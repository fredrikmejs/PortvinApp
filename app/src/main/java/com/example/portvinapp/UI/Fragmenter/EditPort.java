package com.example.portvinapp.UI.Fragmenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditPort extends Fragment implements View.OnClickListener {
Singleton singleton = Singleton.getInstance();
ImageView imageView_port;
EditText editText_winery, editText_vintage, editText_bottleYear, editText_grade, editText_qty;
Button button_delete, button_save, button_back;
Spinner spinner_portType;
Bitmap bitmap;
String winery, type, wineType;
int vintage = -1, bottleYear = -1, grade = -1, qty = -1;
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


        if (portwineObj.getPortImage() != null){
            byte[] b = Base64.decode(portwineObj.getPortImage(),Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
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


        editText_qty = view.findViewById(R.id.editText_qty);

        if (portwineObj.getQty() != -1) {
            editText_qty.setText("" + portwineObj.getQty());
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

        button_back = view.findViewById(R.id.button_add_edit);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ChoosePortwine_fragment();
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
        Matrix matrix = new Matrix();

        matrix.postRotate(90);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getWidth(), true);

        bitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
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


                qty = Integer.parseInt(editText_qty.getText().toString());

                if (qty != -1 && !winery.equals("") && !type.equals("")) {

                    PortwineObj portwineObj = new PortwineObj();
                    portwineObj.setWinery(winery);
                    portwineObj.setGrade(grade);
                    portwineObj.setType(type);
                    portwineObj.setWineType(wineType);
                    portwineObj.setVintage(vintage);
                    portwineObj.setBottleYear(bottleYear);
                    portwineObj.setQty(qty);

                    if (bitmap != null) {
                        portwineObj.setPortImage(bitmapToByte());
                    } else {
                        portwineObj.setPortImage(null);
                    }

                    new FirebaseDatabaseHelper().updatePortwine(singleton.getKey(), portwineObj, new FirebaseDatabaseHelper.DataStatus() {
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

        if (v == button_delete){

            new FirebaseDatabaseHelper().deletePortwine(singleton.getKey(), new FirebaseDatabaseHelper.DataStatus() {
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