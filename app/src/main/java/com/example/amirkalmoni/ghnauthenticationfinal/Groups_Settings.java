package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;

public class Groups_Settings extends AppCompatActivity {

    private ListView listView;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Groups");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_settings);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() ==null) {
            finish();
            startActivity(new Intent(this, loginactivicty.class));
        }
        user = firebaseAuth.getCurrentUser();

        //Initialise list view
        listView = findViewById(R.id.groupsListView);

        //Create list
        String[] options = new String[] {"Mute Groups", "Archive Groups", "Clear All Pre-Texts", "Delete Groups"};
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
//
//        if(optionSelected.equalsIgnoreCase("Mute Groups")){
//            startActivity(new Intent(this, Account_Settings.class));
//        }
//        else if (optionSelected.equalsIgnoreCase("Archive Groups")){
//            startActivity(new Intent(this, Groups_Settings.class));
//        }
//        else if (optionSelected.equalsIgnoreCase("Clear All Pre-Texts")){
//            startActivity(new Intent(this, Notification_Settings.class));
//        }
//        else if (optionSelected.equalsIgnoreCase("Delete Groups")){
//            startActivity(new Intent(this, Help_Settings.class));
//        }
//        else {
//            startActivity(new Intent(this, EditProfile_Account.class));
//        }

    }
}
