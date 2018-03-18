package com.example.amirkalmoni.ghnauthenticationfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;

public class Notification_Settings extends AppCompatActivity implements View.OnClickListener{

    private ListView listView1, listView2, listView3, listView4;
    private Switch switch1, switch2, switch3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Notifications");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        //Initialize

        listView1 = findViewById(R.id.msgListView);
        listView2 = findViewById(R.id.groupListView);
        listView3 = findViewById(R.id.notificationStyle);
        listView4 = findViewById(R.id.resetNotificationSettings);

        switch1 = findViewById(R.id.msgShowNotification);
        switch2 = findViewById(R.id.groupShowNotification);
        switch3 = findViewById(R.id.showPreview);

        switch1.setOnClickListener(this);
        switch2.setOnClickListener(this);
        switch3.setOnClickListener(this);

        //Create list
        String[] option1 = new String[] {"Sound"};
        setupAdapter(option1, listView1);

        String[] option2 = new String[] {"Sound"};
        setupAdapter(option2, listView2);

        String[] option3 = new String[] {"Message Notifications"};
        setupAdapter(option3, listView3);

        String[] option4 = new String[] {"Reset Notification Settings"};
        setupAdapter(option4, listView4);

    }

    public void setupAdapter(String[] strings, final ListView listView){
        ArrayList<String> listOfOptions = new ArrayList<>();
        listOfOptions.addAll(Arrays.asList(strings));

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

    }

    @Override
    public void onClick(View view) {
        if(view.equals(switch1)){

        }
        if (view.equals(switch2)){

        }
        if (view.equals(switch3)){

        }

    }
}
