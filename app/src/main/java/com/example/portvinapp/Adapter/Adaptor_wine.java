
package com.example.portvinapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.portvinapp.Domain.Singleton.Singleton;
import com.example.portvinapp.Objekter.PortwineObj;
import com.example.portvinapp.R;
import com.example.portvinapp.UI.Fragmenter.EditPort;

import java.util.ArrayList;


/**
 * Inspiration taken from https://www.youtube.com/watch?v=q2XA0Pe2W04
 */

public class Adaptor_wine extends ArrayAdapter<String> {

    Context context;
    ArrayList<PortwineObj> portWineArr =  new ArrayList<>();

    public Adaptor_wine(Context context,ArrayList<PortwineObj> portWineArr) {
        super(context, R.layout.portwine_list_element);
        this.context = context;
        this.portWineArr = portWineArr;
    }

    @Override
    public int getCount() {
        return portWineArr.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.portwine_list_element, parent, false);

            viewHolder.editButton = convertView.findViewById(R.id.edit_portwine);
            viewHolder.imagePort = convertView.findViewById(R.id.image_wine);
            viewHolder.textView_bottleYear = convertView.findViewById(R.id.bottle_year_portwine);
            viewHolder.textView_grade = convertView.findViewById(R.id.grade_portwine);
            viewHolder.textView_winery = convertView.findViewById(R.id.winery);
            viewHolder.textView_portType = convertView.findViewById(R.id.portwine_type);
            viewHolder.textView_vintage = convertView.findViewById(R.id.vintage_portwine);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(portWineArr.get(position).getPortImage() != null) {
       //     viewHolder.imagePort.setImageBitmap(portWineArr.get(position).getPortImage());
        } else{
            viewHolder.imagePort.setVisibility(View.INVISIBLE);
        }

        if (portWineArr.get(position).getBottleYear() != -1) {
            viewHolder.textView_bottleYear.setText("Bottle year: " + portWineArr.get(position).getBottleYear());
        } else {
            viewHolder.textView_bottleYear.setVisibility(View.INVISIBLE);
        }
        if (portWineArr.get(position).getGrade() != -1){
            viewHolder.textView_grade.setText("Grade:\n" + portWineArr.get(position).getGrade());
        } else {
            viewHolder.textView_grade.setVisibility(View.INVISIBLE);
        }
        viewHolder.textView_winery.setText(portWineArr.get(position).getWinery());

        viewHolder.textView_portType.setText("Port type: " + portWineArr.get(position).getWineType());

        if (portWineArr.get(position).getVintage() != -1){
            viewHolder.textView_vintage.setText("Vintage year: " + portWineArr.get(position).getVintage());
        } else {
            viewHolder.textView_vintage.setVisibility(View.INVISIBLE);
        }

        viewHolder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container,new EditPort()).addToBackStack(null).commit();
            }
        });


        return convertView;
    }
    static class ViewHolder {
        ImageView imagePort;
        Button editButton;
        TextView textView_winery;
        TextView textView_portType;
        TextView textView_vintage;
        TextView textView_bottleYear;
        TextView textView_grade;


    }

}

