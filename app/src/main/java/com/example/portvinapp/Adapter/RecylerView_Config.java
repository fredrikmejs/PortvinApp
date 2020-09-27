package com.example.portvinapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;
import com.example.portvinapp.UI.Fragmenter.EditPort;

import java.util.ArrayList;
import java.util.List;

public class RecylerView_Config {

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

        public void bind(final PortwineObj portwineObj, String key){


            portwineType.setText("Port type: " + portwineObj.getWineType());

            vintage.setText("Vintage year: " + portwineObj.getVintage());


            if (portwineObj.getBottleYear() != -1){
                bottleYear.setText("" + portwineObj.getBottleYear());
            } else {
                bottleYear.setVisibility(View.INVISIBLE);
            }

            if (portwineObj.getGrade() != -1){
                grade.setText("" + portwineObj.getGrade());
            } else {
                grade.setVisibility(View.INVISIBLE);
            }

            if (portwineObj.getPortImage() != null) {
                //imageView_wine.setImageBitmap(portwineObj.getPortImage());
            } else {
                imageView_wine.setVisibility(View.INVISIBLE);
            }

            winery.setText("" +portwineObj.getWineType());

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Singleton singleton = Singleton.getInstance();
                    singleton.setPortwineObj(portwineObj);
                    ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container,new EditPort()).addToBackStack(null).commit();

                }
            });

            this.key = key;

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
            holder.bind(portwinArr.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return portwinArr.size();
        }
    }

}
