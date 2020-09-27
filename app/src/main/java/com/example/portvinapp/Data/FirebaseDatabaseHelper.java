package com.example.portvinapp.Data;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.example.portvinapp.Objekter.PortwineObj;
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
        void DataIsLoaded(List<PortwineObj> portwineArr, List<String> keys);
        void DataIsInserted();
        void DataisUpdated();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Portwine");
    }

    public void readPortwine(final DataStatus dataStatus){

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                portwineArr.clear();
                ArrayList<String> keys = new ArrayList<>();
                for(DataSnapshot keynode : dataSnapshot.getChildren()){
                    keys.add(keynode.getKey());
                    PortwineObj portwineObj = keynode.getValue(PortwineObj.class);

                    portwineArr.add(portwineObj);
                }
                dataStatus.DataIsLoaded(portwineArr,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
