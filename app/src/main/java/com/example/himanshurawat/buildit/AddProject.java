package com.example.himanshurawat.buildit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.himanshurawat.buildit.PojoClass.NewProject;
import com.example.himanshurawat.buildit.PojoClass.Stakeholders;
import com.example.himanshurawat.buildit.Utility.DatabaseUtility;
import com.example.himanshurawat.buildit.Utility.FirebaseAuthUtility;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.UUID;

public class AddProject extends AppCompatActivity {

    FirebaseUser user;

    DatabaseReference database;

    EditText projectNameEditText,projectDateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        Intent i = getIntent();
        final Stakeholders existingUser=(Stakeholders)i.getSerializableExtra("existingUser");

        user= FirebaseAuthUtility.getAuth().getCurrentUser();
        database= DatabaseUtility.getDatabase().getReference();

        projectNameEditText=findViewById(R.id.activity_add_project_name_edit_text);
        projectDateEditText=findViewById(R.id.activity_add_project_date_edit_text);


        Button submitButton=findViewById(R.id.activity_add_project_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uniqueID = UUID.randomUUID().toString();
                String uID="";
                String arr[]=uniqueID.split("-");
                for(int i=0;i<arr.length;i++){
                    uID+=arr[i];
                }
                Log.i("Error",uID);
                String name=projectNameEditText.getText().toString();
                String date=projectDateEditText.getText().toString();
                NewProject newProject=new NewProject(uID,name,date,null,null,null);

                database.child("project").child(existingUser.getId()).push().setValue(newProject);
                Toast.makeText(AddProject.this,"Project Successfully Added",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
