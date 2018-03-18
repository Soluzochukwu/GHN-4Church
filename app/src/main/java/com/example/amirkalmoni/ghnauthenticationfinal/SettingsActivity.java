package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    private ListView listView;
    private FirebaseUser user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Settings");
        Bundle extra = getIntent().getExtras();
        password = extra.getString("password");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //Initialise list view
        listView = findViewById(R.id.settingsListView);

        //Create list
        String[] options = new String[] {user.getEmail(), "Account", "Groups", "Notifications", "Data and Storage", "Help"};
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

        if(optionSelected.equalsIgnoreCase("Account")){
            Intent intent = new Intent(getApplicationContext(), Account_Settings.class);
            intent.putExtra("password", password);
            startActivity(intent);

            //startActivity(new Intent(this, Account_Settings.class));
        }
        else if (optionSelected.equalsIgnoreCase("Groups")){
            startActivity(new Intent(this, Groups_Settings.class));
        }
        else if (optionSelected.equalsIgnoreCase("Notifications")){
            startActivity(new Intent(this, Notification_Settings.class));
        }
        else if (optionSelected.equalsIgnoreCase("Data and Storage")){
            startActivity(new Intent(this, DS_Settings.class));
        }
        else if (optionSelected.equalsIgnoreCase("Help")){
            startActivity(new Intent(this, Help_Settings.class));
        }
        else {
            startActivity(new Intent(this, EditProfile_Account.class));
        }

    }

}
