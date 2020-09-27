package com.example.portvinapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portvinapp.Domain.Singleton.Portwine_enum;
import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;
import com.example.portvinapp.UI.Fragmenter.EditPort;

import java.util.List;

public class RecylerView_Config {

    private Singleton singleton = Singleton.getInstance();
    private Context mContext;
    private PortwineAdaptor mPortAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<PortwineObj> portwineArr, List<String> keys){
        mContext = context;
        mPortAdapter = new PortwineAdaptor(portwineArr,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mPortAdapter);
    }

    class PortWineItemView extends RecyclerView.ViewHolder {

        private TextView portwineType;
        private TextView vintage;
        private TextView bottleYear;
        private TextView grade;
        private Button edit;
        private ImageView imageView_wine;
        private TextView winery;
        private String key;
        private String name;

        public PortWineItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.portwine_list_element, parent, false));

            portwineType = itemView.findViewById(R.id.portwine_type);

            vintage = itemView.findViewById(R.id.vintage_portwine);

            bottleYear = itemView.findViewById(R.id.bottle_year_portwine);

            grade = itemView.findViewById(R.id.grade_portwine);

            edit = itemView.findViewById(R.id.edit_portwine);

            imageView_wine = itemView.findViewById(R.id.image_wine);

            winery = itemView.findViewById(R.id.winery);

        }

        public void bind(final PortwineObj portwineObjFinal, PortwineObj portwineObj, final String key){

            name = ""+Portwine_enum.forValue(singleton.getPortType());
            //correctPort(name);

            String name1 = portwineObj.getType();
            if (name1.equals(name)) {


            correctPort();
            portwineType.setText("Port type: " + name);


            if (portwineObj.getVintage() != -1) {
                vintage.setText("Vintage year: " + portwineObj.getVintage());
            } else {
                vintage.setVisibility(View.INVISIBLE);
            }

            if (portwineObj.getBottleYear() != -1) {
                bottleYear.setText("" + portwineObj.getBottleYear());
            } else {
                bottleYear.setVisibility(View.INVISIBLE);
            }

            if (portwineObj.getGrade() != -1) {
                grade.setText("" + portwineObj.getGrade());
            } else {
                grade.setVisibility(View.INVISIBLE);
            }

            if (portwineObj.getPortImage() != null) {
                //imageView_wine.setImageBitmap(portwineObj.getPortImage());
            } else {
                imageView_wine.setVisibility(View.INVISIBLE);
            }

            winery.setText("" + portwineObj.getWinery());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton = Singleton.getInstance();
                    singleton.setPortwineObj(portwineObjFinal);
                    singleton.setKey(key);
                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container, new EditPort()).addToBackStack(null).commit();

                }
            });

       }
        this.key = key;

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

        private List<PortwineObj> portwinArr;
        private List<String> mKeys;

        public PortwineAdaptor(List<PortwineObj> portwinArr, List<String> mKeys){
            this.portwinArr = portwinArr;
            this.mKeys = mKeys;
        }


        @NonNull
        @Override
        public PortWineItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PortWineItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PortWineItemView holder, int position) {
            holder.bind(portwinArr.get(position), portwinArr.get(position), mKeys.get(position));
            singleton.setPortWineArr(portwinArr);
        }

        @Override
        public int getItemCount() {
            return singleton.getSizeOfRecyler();
        }
    }

}
