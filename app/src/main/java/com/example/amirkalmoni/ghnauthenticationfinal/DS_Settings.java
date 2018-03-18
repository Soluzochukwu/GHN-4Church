package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;

public class DS_Settings extends AppCompatActivity implements View.OnClickListener {

    private Switch switch1;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Data and Storage");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dssettings);

        switch1 = findViewById(R.id.cellularDataSwitch);
        switch1.setOnClickListener(this);

        listView = findViewById(R.id.usageListView);
        //Create list
        String[] options = new String[] {"Network Usage", "Storage Usage"};
        ArrayList<String> listOfOptions = new ArrayList<>();
        listOfOptions.addAll(Arrays.asList(options));

        //Create and Set ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfOptions);
        listView.setAdapter(adapter);

        //Set on click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String optionName = (String)listView.getItemAtPosition(i);
                click(optionName);
            }
        });

    }

    private void click(String optionSelected){

        if(optionSelected.equalsIgnoreCase("Storage Usage")){
            startActivity(new Intent(this, Account_Settings.class));
        }
        else if (optionSelected.equalsIgnoreCase("Network Usage")){
            startActivity(new Intent(this, Groups_Settings.class));
        }

    }


    @Override
    public void onClick(View view) {

        if (view.equals(switch1)){
            //

        }

    }
}
