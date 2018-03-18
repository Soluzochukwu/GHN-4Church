package com.example.amirkalmoni.ghnauthenticationfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Help_Settings extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Help");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help__settings);
        listView = findViewById(R.id.helpListView);

        //Create list
        String[] options = new String[] {"Contact Us", "FAQ", "Terms and Privacy Policy", "Licenses"};
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

//        if(optionSelected.equalsIgnoreCase("Contact Us")){
//            startActivity(new Intent(this, Account_Settings.class));
//        }
//        else if (optionSelected.equalsIgnoreCase("FAQ")){
//            startActivity(new Intent(this, Groups_Settings.class));
//        }
//        else if (optionSelected.equalsIgnoreCase("Terms and Privacy Policy")){
//            startActivity(new Intent(this, Groups_Settings.class));
//        }else if (optionSelected.equalsIgnoreCase("Licenses")){
//            startActivity(new Intent(this, Groups_Settings.class));
//        }

    }
}
