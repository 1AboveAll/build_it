package com.example.himanshurawat.buildit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.himanshurawat.buildit.Adapters.AllProjectsAdapter;
import com.example.himanshurawat.buildit.PojoClass.Labour;
import com.example.himanshurawat.buildit.PojoClass.NewProject;
import com.example.himanshurawat.buildit.PojoClass.Stakeholders;
import com.example.himanshurawat.buildit.Utility.DatabaseUtility;
import com.example.himanshurawat.buildit.Utility.FirebaseAuthUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Stakeholder extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference database;
    private FirebaseUser user;
    private String key;
    private Stakeholders existingUser;


    //Profile Items
    ImageView stakeholderProfileImageView;
    TextView stakeholderNameTextView;
    TextView stakeholderEmailTextView;


    private String stakeholderEmail,stakeholderProfileURL,stakeholderName;


    private RecyclerView projectRecyclerView;
    private AllProjectsAdapter projectsAdapter;
    private List<NewProject> projectsList;

    @Override
    protected void onStart() {
        super.onStart();
        if(user==null){
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stakeholder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //RecyclerView Setup
        projectRecyclerView=findViewById(R.id.content_stakeholder_recycler_view);
        projectsList=new ArrayList<>();
        projectsAdapter=new AllProjectsAdapter(this,projectsList);

        projectRecyclerView.setAdapter(projectsAdapter);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        user= FirebaseAuthUtility.getAuth().getCurrentUser();
        database= DatabaseUtility.getDatabase().getReference();

        if(user!=null) {
            database.child("stakeholder").orderByChild("email").equalTo(user.getEmail()).
                    limitToFirst(1).addValueEventListener(stakeholderProfileEventListener);
        }

//        Button button= findViewById(R.id.content_stakeholder_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(Stakeholder.this,Project.class);
//                i.putExtra("existingUser",existingUser);
//                startActivity(i);
//            }
//        });






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Stakeholder.this,AddProject.class);
                i.putExtra("existingUser",existingUser);
                startActivity(i);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View NavigationHeader=navigationView.getHeaderView(0);
        stakeholderNameTextView=NavigationHeader.findViewById(R.id.nav_header_stakeholder_name_text_view);
        stakeholderEmailTextView=NavigationHeader.findViewById(R.id.nav_header_stakeholder_email_text_view);
        stakeholderProfileImageView= NavigationHeader.findViewById(R.id.nav_header_stakeholder_profile_image_view);



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
        getMenuInflater().inflate(R.menu.stakeholder, menu);
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

        if (id == R.id.activity_stakeholder_log_out) {

            FirebaseAuth.getInstance().signOut();
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    ValueEventListener stakeholderProfileEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.getChildrenCount()>0){
                for(DataSnapshot child : dataSnapshot.getChildren()) {

                    key=child.getKey();

                    database.child("stakeholder").child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            existingUser = dataSnapshot.getValue(Stakeholders.class);
                            stakeholderEmail = existingUser.getEmail();
                            stakeholderProfileURL = existingUser.getProfileURL();
                            stakeholderName = existingUser.getName();
                            stakeholderNameTextView.setText(stakeholderName);
                            stakeholderEmailTextView.setText(stakeholderEmail);
                            Glide.with(Stakeholder.this).load(stakeholderProfileURL).apply(RequestOptions.circleCropTransform()).into(stakeholderProfileImageView);

                           //Load All Projects
                            database.child("project").child(existingUser.getId()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    projectsList.clear();
                                    if(dataSnapshot.getChildrenCount()>0){
                                        for(DataSnapshot children: dataSnapshot.getChildren()){
                                            NewProject newProject=children.getValue(NewProject.class);
                                            projectsList.add(newProject);
                                        }
                                        projectsAdapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
            else{
                Stakeholders newStakeholder = new Stakeholders(user.getUid(),user.getDisplayName(),user.getEmail(),user.getPhotoUrl().toString());
                database.child("stakeholder").push().setValue(newStakeholder);

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
