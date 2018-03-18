package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditProfile_Account extends AppCompatActivity implements View.OnClickListener{

    private FirebaseUser user;
    private EditText usernameField;

    private Button cancel, done;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Edit Profile");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile__account);

        //Firebase authorization
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() ==null) {
            finish();
            startActivity(new Intent(this, loginactivicty.class));
        }

        user = firebaseAuth.getCurrentUser();


        //Initializing elements

        usernameField = findViewById(R.id.usernameEditText);
        cancel = findViewById(R.id.cancelButton);
        done = findViewById(R.id.doneButton);

        usernameField.setText(user.getEmail(), TextView.BufferType.EDITABLE);

        //Set on click listener
        cancel.setOnClickListener(this);
        done.setOnClickListener(this);
        usernameField.setOnClickListener(this);
        click();
    }

    private void click(){

        usernameField.addTextChangedListener(new TextWatcher() {
            String newUsername;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                newUsername = usernameField.getText().toString().trim();
                if(newUsername.length() == 0) done.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (usernameField.getText().toString().isEmpty()){
                    done.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if(view == done){
            cancel.setVisibility(View.INVISIBLE);
            done.setVisibility(View.INVISIBLE);
            //change username on database
        }

        if (view == cancel){
            cancel.setVisibility(View.INVISIBLE);
            done.setVisibility(View.INVISIBLE);
            usernameField.setText(user.getEmail(), TextView.BufferType.EDITABLE);
        }

        if(view == usernameField){
            cancel.setVisibility(View.VISIBLE);
            done.setVisibility(View.VISIBLE);

        }

    }
}
