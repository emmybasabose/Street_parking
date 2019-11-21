package com.example.street_parking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivityss extends AppCompatActivity {
    DatabaseHelp db=new DatabaseHelp(this);
    //Staff staff;
    ListView listView;
    Button scan, load,importx;
        ArrayList<Staff> staffArrayList;
    listAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsss);
        scan=findViewById(R.id.scan);
        load=findViewById(R.id.load);
        importx=findViewById(R.id.importx);
        listView=findViewById(R.id.listView);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityss.this,scanner.class);

                startActivity(intent);

            }});
        importx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityss.this,MemoryStreet.class);

                startActivity(intent);

            }});
     load.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            load_list_view();
         }
     });
    }
    void load_list_view(){
        staffArrayList=db.allStaff();
        adapter=new listAdapter(this,staffArrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
