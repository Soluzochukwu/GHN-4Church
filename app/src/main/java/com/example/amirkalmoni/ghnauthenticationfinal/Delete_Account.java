package com.example.amirkalmoni.ghnauthenticationfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Delete_Account extends AppCompatActivity implements View.OnClickListener{

    private EditText emailText, passwordText;
    private TextView errorText;
    private ProgressDialog progressDialog;
    private Button confirm;
    private FirebaseUser user;
    String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__account);
        user = FirebaseAuth.getInstance().getCurrentUser();

        setTitle("Delete Account");


        emailText = findViewById(R.id.deleteEmail);
        passwordText = findViewById(R.id.deletePassword);
        errorText = findViewById(R.id.textView2);
        progressDialog = new ProgressDialog(this);
        errorText = findViewById(R.id.textView2);
        confirm = findViewById(R.id.confirmButton);
        confirm.setOnClickListener(this);


        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                errorText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailString = emailText.getText().toString();
            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                errorText.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordString = passwordText.getText().toString();
            }
        });
    }

    private void verify(){

        AuthCredential credential = EmailAuthProvider.getCredential(emailString, passwordString);

        assert user != null;
        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {

                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
                                }else{
                                    errorText.setText("User was verified successfully but GHN encountered an error. Please try again later ");
                                    errorText.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }else{
                        errorText.setText("EMAIL OR PASSWORD DOES NOT MATCH ACCOUNT RECORDS");
                        errorText.setVisibility(View.VISIBLE);
                    }

                }
            });

    }


    @Override
    public void onClick(View view) {
        if (view == confirm){
            verify();
        }

    }
}