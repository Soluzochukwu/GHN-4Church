package com.example.amirkalmoni.ghnauthenticationfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class ProfileActivicty extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button settingsButton;
    private String password;
    private Button  add_room;

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_rooms = new ArrayList<>();
    private String name, room_name, emergencyMessage, temp_key;
    private DatabaseReference root, roots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activicty);

        setTitle("Groups");

        root = FirebaseDatabase.getInstance().getReference().getRoot();

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() ==null)
        {
            finish();
            startActivity(new Intent(this, loginactivicty.class));

        }
        FirebaseUser user = firebaseAuth.getCurrentUser();


        textViewUserEmail = findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome:  " +user.getEmail());
        settingsButton = findViewById(R.id.buttonSettings);
        settingsButton.setOnClickListener(this);
        add_room =  findViewById(R.id.btn_add_room);
        add_room.setOnClickListener(this);
        listView =  findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list_of_rooms);
        request_user_name();
        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener((new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String group = (String) listView.getItemAtPosition(i);
                if(name.isEmpty()){
                    request_user_name();
                }else {
                    chatRoomClicked(group);
                }

//                Intent intent = new Intent(getApplicationContext(),ChatRoom.class);
//                //intent.putExtra("room_name",((TextView)view).getText().toString() );
//                intent.putExtra("user_name",name);
//                intent.putExtra("group name",room_name);
//                startActivity(intent);

            }
        }));

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                list_of_rooms.clear();
                list_of_rooms.addAll(set);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void chatRoomClicked (String string){
        Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
        //intent.putExtra("room_name",((TextView)view).getText().toString() );
        intent.putExtra("userName",name);
        intent.putExtra("groupName",string);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        if(view == settingsButton) {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            intent.putExtra("password", password);
            startActivity(intent);

            //startActivity(new Intent(this, SettingsActivity.class));
        }

        if (view == add_room){
            request_group_name();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profilemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.emergencyButton:
                //User chose emergency button
                emergencyprocedure();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }



    private void request_group_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Room name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                room_name = input_field.getText().toString();

                Map<String,Object> map = new HashMap<String, Object>();
                map.put(room_name,"");
                root.updateChildren(map);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                //request_group_name();
            }
        });

        builder.show();
    }

    private void emergencyprocedure(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Emergency Message:");
        final EditText input_field = new EditText(this);
        builder.setView(input_field);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                emergencyMessage = input_field.getText().toString();

                for (String group: list_of_rooms) {

                    roots = FirebaseDatabase.getInstance().getReference().child(group);

                    Map<String, Object> map4 = new HashMap<>();
                    temp_key = roots.push().getKey();
                    roots.updateChildren(map4);
                    DatabaseReference message_root = roots.child(temp_key);

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("name", name);
                    map2.put("msg", emergencyMessage);
                    message_root.updateChildren(map2);

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name = input_field.getText().toString();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name();
            }
        });

        builder.show();
    }



}
