package com.example.himanshurawat.buildit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.himanshurawat.buildit.PojoClass.User;

public class Tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        Intent i= getIntent();
        final User newUser=(User)i.getSerializableExtra("newUser");
        ImageView imageView=findViewById(R.id.activity_tutorial_image_view);
        if(newUser.getUserType().equals("stakeholder")){
            imageView.setImageResource(R.drawable.stakeholder_tutorial);
        }
        if(newUser.getUserType().equals("contractor")){
            imageView.setImageResource(R.drawable.contractor_tutorial);
        }
        if(newUser.getUserType().equals("labourer")){
            imageView.setImageResource(R.drawable.labourer_tutorial);
        }


        Button continueButton= findViewById(R.id.activity_tutorial_continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent();
                it.putExtra("newUser",newUser);
                setResult(RESULT_OK,it);
                finish();
            }
        });

    }
}
