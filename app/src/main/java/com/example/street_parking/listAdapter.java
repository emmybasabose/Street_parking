package com.example.street_parking;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Staff> arrayList;

    listAdapter(Context context, ArrayList<Staff> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.customlv,null);
            TextView txt_id=convertView.findViewById(R.id.id_txt);
            TextView txt_name=convertView.findViewById(R.id.name_txt);
            TextView txt_tel=convertView.findViewById(R.id.tel_txt);

            Staff staff=arrayList.get(position);
            txt_id.setText(String.valueOf(staff.getId()));
            txt_name.setText(staff.getName());
            txt_tel.setText(staff.getTel());

        return convertView;
    }
}
