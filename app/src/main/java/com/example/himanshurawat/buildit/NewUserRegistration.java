package com.example.himanshurawat.buildit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.himanshurawat.buildit.PojoClass.User;
import com.example.himanshurawat.buildit.Utility.DatabaseUtility;
import com.example.himanshurawat.buildit.Utility.FirebaseAuthUtility;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class NewUserRegistration extends AppCompatActivity {
    //Current User Reference
    private FirebaseUser user;
    //Database Reference
    private DatabaseReference database;


    //Can be converted to Local will Handle this Later
    private Button registerAsLabourer;
    private Button registerAsContractor;
    private Button registerAsStakeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_registration);
        //Current User
        user = FirebaseAuthUtility.getAuth().getCurrentUser();
        //Real Time Database Object
        database = DatabaseUtility.getDatabase().getReference();


        registerAsLabourer = findViewById(R.id.activity_new_user_registration_labourer_login_button);
        registerAsContractor = findViewById(R.id.activity_new_user_registration_contractor_login_button);
        registerAsStakeholder = findViewById(R.id.activity_new_user_registration_stakeholder_login_button);

        registerAsLabourer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(user.getEmail(), "labourer");
                Intent i=new Intent(NewUserRegistration.this,Tutorial.class);
                i.putExtra("newUser",newUser);
                startActivityForResult(i,1);
            }
        });

        registerAsContractor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(user.getEmail(), "contractor");
                Intent i=new Intent(NewUserRegistration.this,Tutorial.class);
                i.putExtra("newUser",newUser);
                startActivityForResult(i,2);

            }
        });

        registerAsStakeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User(user.getEmail(), "stakeholder");
                Intent i=new Intent(NewUserRegistration.this,Tutorial.class);
                i.putExtra("newUser",newUser);
                startActivityForResult(i,3);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK){
            User newUser=(User)data.getSerializableExtra("newUser");
            database.child("users").push().setValue(newUser);
            Toast.makeText(NewUserRegistration.this, "Registration Success", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(requestCode==2&& resultCode==RESULT_OK){
            User newUser=(User)data.getSerializableExtra("newUser");
            database.child("users").push().setValue(newUser);
            Toast.makeText(NewUserRegistration.this, "Registration Success", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(requestCode==3 && resultCode==RESULT_OK){
            User newUser=(User)data.getSerializableExtra("newUser");
            database.child("users").push().setValue(newUser);
            Toast.makeText(NewUserRegistration.this, "Registration Success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(user==null){
            finish();
        }
    }
}
