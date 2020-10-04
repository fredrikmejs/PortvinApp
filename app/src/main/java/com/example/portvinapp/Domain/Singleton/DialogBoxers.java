package com.example.portvinapp.Domain.Singleton;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.portvinapp.Objekter.PortwineObj;

public class DialogBoxers extends DialogFragment {
    Singleton single = Singleton.getInstance();
    Context mContext;
    PortwineObj portwineObj;

    public DialogBoxers(Context context){
        this.mContext = context;
    }

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState){

        portwineObj = single.getPortwineObj();

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(portwineObj.getWinery()).setMessage(portwineObj.getNotes()).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


        return builder.create();
    }
}
