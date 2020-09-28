package com.example.portvinapp.Data;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.example.portvinapp.Domain.Singleton.Portwine_enum;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private List<PortwineObj> portwineArr = new ArrayList<>();



    public interface DataStatus{
        void DataIsLoaded(List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        mRef = mDatabase.getReference(userID + "/Portwine");
    }

    public void readPortwine(final DataStatus dataStatus){

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                portwineArr.clear();
                Singleton singleton = Singleton.getInstance();
                int size = 0;
                ArrayList<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    PortwineObj portwineObj = keynode.getValue(PortwineObj.class);
                    String name = ""+ Portwine_enum.forValue(singleton.getPortType());

                    if (portwineObj.getType().equals(name)){
                        size++;
                        portwineArr.add(portwineObj);
                    }

                }
                singleton.setSizeOfRecyler(size);
                singleton.setPortWineArr(portwineArr);
                singleton.setKeys(keys);
                dataStatus.DataIsLoaded(keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addPortwine(PortwineObj portwineObj, final DataStatus dataStatus){
        String key = mRef.push().getKey();
        mRef.child(key).setValue(portwineObj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }


    public void updatePortwine(String key, PortwineObj portwineObj, final DataStatus dataStatus){
        mRef.child(key).setValue(portwineObj).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }

    public void deletePortwine(String key, final DataStatus dataStatus){
        mRef.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }




}
