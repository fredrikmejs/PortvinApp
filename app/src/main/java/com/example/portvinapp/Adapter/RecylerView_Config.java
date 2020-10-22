package com.example.portvinapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portvinapp.Domain.Singleton.DialogBoxers;
import com.example.portvinapp.Domain.Singleton.Portwine_enum;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;
import com.example.portvinapp.UI.Fragmenter.EditPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecylerView_Config {

    private Singleton singleton = Singleton.getInstance();
    private Context mContext;
    private List<PortwineObj> portwineArr = new ArrayList<>();
    private PortwineObj portwineObj;




    public void setConfig(RecyclerView recyclerView, Context context, List<String> keys){
        mContext = context;
        portwineArr.addAll(singleton.getPortWineArr());
        PortwineAdaptor mPortAdapter = new PortwineAdaptor(portwineArr, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mPortAdapter);
    }

    class PortWineItemView extends RecyclerView.ViewHolder {

        private TextView portwineType;
        private TextView qty;
        private TextView vintage;
        private TextView bottleYear;
        private TextView grade;
        private Button edit;
        private ImageView imageView_wine;
        private TextView winery;
        private String key;
        private String name;
        private Button notes;


        public PortWineItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.portwine_list_element, parent, false));

            portwineType = itemView.findViewById(R.id.portwine_type);

            vintage = itemView.findViewById(R.id.vintage_portwine);

            bottleYear = itemView.findViewById(R.id.bottle_year_portwine);

            grade = itemView.findViewById(R.id.grade_portwine);

            edit = itemView.findViewById(R.id.edit_portwine);

            imageView_wine = itemView.findViewById(R.id.image_wine);

            winery = itemView.findViewById(R.id.winery);

            qty = itemView.findViewById(R.id.QTY);

            notes = itemView.findViewById(R.id.button_notes);

        }

        public void bind(final PortwineObj portwineObjFinal1, PortwineObj objPort, final String key){

            name = ""+Portwine_enum.forValue(singleton.getPortType());
            portwineObj = portwineObjFinal1;

            String name1 = objPort.getType();
            if (name1.equals(name)) {

            correctPort();
            portwineType.setText("Port type: " + name);


            if (objPort.getVintage() != -1) {
                vintage.setText("Vintage year: " + objPort.getVintage());
            } else {
                vintage.setVisibility(View.INVISIBLE);
            }

            if (objPort.getBottleYear() != -1) {
                bottleYear.setText("Bottle year: " + objPort.getBottleYear());
            } else {
                bottleYear.setVisibility(View.INVISIBLE);
            }

            List<String> gradeString = new ArrayList(Arrays.asList(mContext.getResources().getStringArray(R.array.spinner_grade)));

            if (objPort.getGrade() != 10) {
                grade.setText("Grade: \n" +  gradeString.get(portwineObj.getGrade()));
            } else {
                grade.setVisibility(View.INVISIBLE);
            }
            qty.setText("Qty: " + objPort.getQty());


            if (objPort.getPortImage() != null) {
                Bitmap bitmap;
                byte[] b = Base64.decode(objPort.getPortImage(),Base64.DEFAULT);
                bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
                imageView_wine.setImageBitmap(bitmap);
            }

            winery.setText("" + objPort.getWinery());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton = Singleton.getInstance();
                    singleton.setPortwineObj(portwineObjFinal1);
                    singleton.setKey(key);
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container, new EditPort()).addToBackStack(null).commit();
                }
            });

            notes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog(portwineObjFinal1);
                }


            });
                //  Log.d("TEST", "bind:" + winery.getText().toString());
            }

        this.key = key;

    }

        private void openDialog(PortwineObj portwineObj) {
            singleton.setPortwineObj(portwineObj);
            DialogBoxers dialogbox = new DialogBoxers(mContext);
            dialogbox.show(((FragmentActivity) mContext).getSupportFragmentManager(),"Noter");
        }
    private void correctPort(){

        switch (name){
            case "LBV":
                name = "Late Bottle Vintage";
                break;
            case "age10":
                name = "10 Year";
                break;
            case "age20":
                name = "20 Year";
                break;
            case "age30":
                name = "30 Year";
                break;
            case "age40":
                name = "40 Year";
                break;
        }
    }
}

    class PortwineAdaptor extends RecyclerView.Adapter<PortWineItemView>{

        private List<PortwineObj> portwineArr;
        private List<String> mKeys;

        public PortwineAdaptor(List<PortwineObj> portwinArr, List<String> mKeys){
            this.portwineArr = portwinArr;
            this.mKeys = mKeys;
        }


        @NonNull
        @Override
        public PortWineItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PortWineItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PortWineItemView holder, int position) {
            //String name = "" + Portwine_enum.forValue(singleton.getPortType());
            singleton.setPosition(position);
            List<Integer> used = new ArrayList<>(singleton.getUsedIndex());
            if(position >= portwineArr.size()){
                singleton.clearUsedIndexx();
            }

            for (int i = 0; i < portwineArr.size(); i++) {
                // if (portwineArr.get(i).getType().equals(name)) {
                    if (!used.contains(i)) {
                        singleton.addUsedIndex(i);
                        holder.bind(portwineArr.get(i), portwineArr.get(i), mKeys.get(i));
                        break;
                  //  }
                }
            }
            singleton.setPortWineArr(portwineArr);
            }


        @Override
        public int getItemCount() {
            return singleton.getSizeOfRecyler();
        }


    }

}
