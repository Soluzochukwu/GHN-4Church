package com.example.amirkalmoni.ghnauthenticationfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Arrays;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class Account_Settings extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ListView listView;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTitle("Account");
        Bundle extra = getIntent().getExtras();
        password = extra.getString("password");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        //assigning firebase user
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        //Initialise list view
        listView = findViewById(R.id.accountListView);
        progressBar = new ProgressBar(this);
        progressDialog = new ProgressDialog(this);

        //Create list
        String[] options = new String[]{"Edit Account Information", "Log Out", "Delete Account"};
        ArrayList<String> listOfOptions = new ArrayList<>();
        listOfOptions.addAll(Arrays.asList(options));

        //Create and Set ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfOptions);
        listView.setAdapter(adapter);

        //Set on click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String optionName = (String) listView.getItemAtPosition(i);
                click(optionName);
            }
        });

    }

    private void click(String selectedOption) {
        if(selectedOption.equalsIgnoreCase("Edit Account Information")){
            startActivity(new Intent(this, EditProfile_Account.class));
        }
        else if (selectedOption.equalsIgnoreCase("Log Out")){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, loginactivicty.class));
        }
        else if (selectedOption.equalsIgnoreCase("Delete Account")){

            startActivity(new Intent(this, Delete_Account.class));

        }
    }

}
