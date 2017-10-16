package com.example.himanshurawat.buildit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.himanshurawat.buildit.PojoClass.Contractors;
import com.example.himanshurawat.buildit.PojoClass.Labour;
import com.example.himanshurawat.buildit.Utility.DatabaseUtility;
import com.example.himanshurawat.buildit.Utility.FirebaseAuthUtility;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class Contractor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference database;
    private FirebaseUser user;
    private String key;
    private Labour existingUser;


    //Profile Items
    ImageView contractorProfileImageView;
    TextView contractorNameTextView;
    TextView contractorEmailTextView;


    private String contractorEmail,contractorProfileURL,contractorName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user= FirebaseAuthUtility.getAuth().getCurrentUser();
        database= DatabaseUtility.getDatabase().getReference();

        database.child("contractor").orderByChild("email").equalTo(user.getEmail()).
                limitToFirst(1).addValueEventListener(contractorProfileEventListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View NavigationHeader = navigationView.getHeaderView(0);
        contractorNameTextView= NavigationHeader.findViewById(R.id.nav_header_contractor_name_text_view);
        contractorEmailTextView= NavigationHeader.findViewById(R.id.nav_header_contractor_email_text_view);
        contractorProfileImageView= NavigationHeader.findViewById(R.id.nav_header_contractor_profile_image_view);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contractor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ValueEventListener contractorProfileEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getChildrenCount()>0){
                for(DataSnapshot child : dataSnapshot.getChildren()) {

                    key=child.getKey();

                    database.child("contractor").child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            existingUser = dataSnapshot.getValue(Labour.class);
                            contractorEmail = existingUser.getEmail();
                            contractorProfileURL = existingUser.getProfileURL();
                            contractorName = existingUser.getName();
                            contractorNameTextView.setText(contractorName);
                            contractorEmailTextView.setText(contractorEmail);
                            Glide.with(Contractor.this).load(contractorProfileURL).apply(RequestOptions.circleCropTransform()).into(contractorProfileImageView);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
            else{
                Contractors newContractor = new Contractors(user.getUid(),user.getDisplayName(),user.getEmail(),user.getPhotoUrl().toString());
                database.child("contractor").push().setValue(newContractor);
            }



        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
